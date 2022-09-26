package data;

import entities.Souvenir;

import java.util.ArrayList;
import java.util.List;

public class SouvenirData {

    private static List<Souvenir> souvenirs = new ArrayList<>();

    public static List<Souvenir> getSouvenirs() {
        return souvenirs;
    }
}
