package dao;

import entities.Souvenir;
import layouts.GraphInterface;
import layouts.InterfaceController;

import java.util.List;
import java.util.Optional;

import static data.SouvenirData.getSouvenirs;

public class SouvenirDao {

    public static Optional<Souvenir> get(int id) {
        return getSouvenirs().stream().filter(souvenir -> souvenir.getId()==id).findFirst();
    }

    public static List<Souvenir> getAll() {
        return getSouvenirs();
    }

    public static void save(Souvenir souvenir) {
        if (!get(souvenir.getId()).isPresent()) {
            getSouvenirs().add(souvenir);
            Souvenir.incrementId();
            InterfaceController.appendLog("ADDED: " + souvenir);
        }
        else {
            update(souvenir);
        }
    }

    public static void update(Souvenir souvenir) {
        Souvenir toBeUpdated = get(souvenir.getId()).get(); // isPresent() checked at save()
        toBeUpdated.setSouvenirType(souvenir.getSouvenirType());
        toBeUpdated.setProducerId(souvenir.getProducerId());
        toBeUpdated.setCreatedAt(souvenir.getCreatedAt());
        toBeUpdated.setPrice(souvenir.getPrice());
        InterfaceController.appendLog("UPDATED: " + souvenir);
    }

    public static void delete(int id) {
        if (get(id).isPresent()) {
            Souvenir toBeDeleted = get(id).get();
            getSouvenirs().remove(toBeDeleted);
            InterfaceController.appendLog("DELETED: " + toBeDeleted);
        } else InterfaceController.appendLog("SOUVENIR NOT FOUND ID: " + id);
    }
}
