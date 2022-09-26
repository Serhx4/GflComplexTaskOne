package dao;

import entities.Producer;
import layouts.InterfaceController;

import java.util.List;
import java.util.Optional;

import static data.ProducerData.getProducers;

public class ProducerDao {

    public static Optional<Producer> get(int id) {
        return getProducers().stream().filter(producer -> producer.getId() == id).findFirst();
    }

    public static List<Producer> getAll() {
        return getProducers();
    }

    public static void save(Producer producer) {
        if (!get(producer.getId()).isPresent()) {
            getProducers().add(producer);
            Producer.incrementId();

            InterfaceController.appendLog("ADDED: " + producer);
        }
        else {
            update(producer);
        };
    }

    public static void update(Producer producer) {
            Producer toBeUpdated = get(producer.getId()).get(); // check in 'save()' method
            toBeUpdated.setName(producer.getName());
            toBeUpdated.setCountry(producer.getCountry());
            InterfaceController.appendLog("UPDATED: " + producer);
    }

    public static void delete(int id) {
        if (get(id).isPresent()) {
            Producer toBeDeleted = get(id).get();
            InterfaceController.appendLog("DELETED " + toBeDeleted + " AND ALL ITS SOUVENIRS");
            getProducers().remove(toBeDeleted);
        }
        else InterfaceController.appendLog("PRODUCER NOT FOUND ID: " + id);
    }

}
