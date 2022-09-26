package data;

import entities.Producer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhx4 on 9/17/2022.
 */
public class ProducerData {

    private static List<Producer> producers = new ArrayList<>();

    public static List<Producer> getProducers() {
        return producers;
    }
}
