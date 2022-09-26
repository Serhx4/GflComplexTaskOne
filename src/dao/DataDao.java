package dao;

import data.ProducerData;
import data.SouvenirData;
import date.Dates;
import entities.Producer;
import entities.Producer.Country;
import entities.Souvenir;
import layouts.InterfaceController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataDao {
    private static List<Souvenir> souvenirs = SouvenirData.getSouvenirs();
    private static List<Producer> producers = ProducerData.getProducers();

    public static void showAllSouvenirsMadeBy(int producer) {
        InterfaceController.clearLog();
        souvenirs.stream().filter(s -> s.getProducerId() == producer).forEach(InterfaceController::appendLog);
    }

    public static void showAllSouvenirsMadeIn (Country country) {
        InterfaceController.clearLog();
        souvenirs.stream().filter(s -> s.getProducer().getCountry() == country)
                .forEach(InterfaceController::appendLog);
    }

    public static void showProducersWithPricesLower(double price) {
        InterfaceController.clearLog();
        souvenirs.stream()
                .filter(s -> s.getPrice() < price)
                .collect(Collectors.toMap(Souvenir::getProducer, Function.identity(), (s1, s2) -> s1))
                .keySet()
                .forEach(InterfaceController::appendLog);

//        producers.stream()
//                .filter(producer ->
//                        souvenirs.stream()
//                        .filter(souvenir -> souvenir.getProducer().equals(producer))
//                        .anyMatch(souvenir -> souvenir.getPrice() < price)
//                ).forEach(System.out::println);
    }

    public static void showAllProducersAndSouvenirs() {
        InterfaceController.clearLog();

        souvenirs.stream()
            .collect(Collectors.groupingBy(Souvenir::getProducer))
            .entrySet()
            .forEach(DataDao::printEntry);
    }

    public static void showSouvenirProducersAtYear(Souvenir.SouvenirType type, String year) {
        InterfaceController.clearLog();
        souvenirs.stream()
                .filter(s -> s.getSouvenirType().equals(type))
                .filter(s -> Dates.isCurrentYear(s.getCreatedAt(), Dates.getDateByYear(year)))
                .collect(Collectors.toMap(Souvenir::getProducer, Function.identity(), (s1, s2) -> s1))
                .keySet()
                .forEach(InterfaceController::appendLog);
    }

    public static void showAllSouvenirsGroupByYear() {
        souvenirs.stream()
                .collect(Collectors.groupingBy(Souvenir::getCreatedAt))
                .entrySet()
                .forEach(DataDao::printEntry);
    }

    private static<T, V> void printEntry(Map.Entry<T, List<V>> entry) {
        InterfaceController.appendLog(entry.getKey());
        entry.getValue().forEach(InterfaceController::appendLog);
    }

    public static void deleteProducerAndSouvenirs(Producer producer) {
        souvenirs.removeIf(souvenir -> souvenir.getProducer().equals(producer));
        ProducerDao.delete(producer.getId());
    }

}
