package file;

import dao.ProducerDao;
import dao.SouvenirDao;
import entities.Producer;
import entities.Souvenir;
import layouts.GraphInterface;
import layouts.InterfaceController;

import java.io.IOException;
import java.nio.file.*;

public class Filesys {

    private final static String SOUVENIRS_PATH = "filesystem/products";
    private final static String PRODUCERS_PATH = "filesystem/producers";

    public static void readFilesystem() {
        findFiles(PRODUCERS_PATH);
        findFiles(SOUVENIRS_PATH);
        InterfaceController.clearLog();
        GraphInterface.revalidate();
    }

    private static void findFiles(String rootPath) {

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.{txt}");
        try {
            Files.walk(Paths.get(rootPath))
                    .filter(matcher::matches)
                    .forEach(p -> readFile(p.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(String path) {
        try {
            Path p = Paths.get(path);
            Files.lines(Paths.get(path))
                    .forEach(p.startsWith(SOUVENIRS_PATH) ? Filesys::createSouvenir : Filesys::createProducer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createSouvenir(String line) {
        SouvenirDao.save(
                Souvenir.newSouvenir(split(line)));
    }

    private static void createProducer(String line) {
        ProducerDao.save(
                Producer.newProducer(split(line)));
    }

    private static String[] split (String line) {
        return line.split(", ");
    }
}
