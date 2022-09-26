package entities;

import dao.ProducerDao;
import data.ProducerData;
import data.SouvenirData;
import date.Dates;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class Souvenir implements Serializable {

    public enum SouvenirType {
        CUP, BOTTLE, BAG, T_SHIRT, BASEBALL_CAP, PEN, FLASH_DRIVE, FLASHLIGHT, UNKNOWN;

        public static SouvenirType findType(String name) {
            return Arrays.stream(SouvenirType.values())
                    .filter(type -> type.name().equals(name.toUpperCase()))
                    .findFirst().orElse(UNKNOWN);
        }
    }

    public static final Souvenir EMPTY_SOUVENIR =
            new Souvenir(SouvenirType.UNKNOWN, -1, Dates.getDateByYear("1900"), 0);
    private static int idAutoIncrement = 0;
    private int id;

    private SouvenirType souvenirType;
    private int producerId;
    private LocalDate createdAt;
    private double price;

    public Souvenir(SouvenirType souvenirType, int producerId, LocalDate createdAt, double price) {
        this.id = idAutoIncrement;
        this.souvenirType = souvenirType;
        this.producerId = producerId;
        this.createdAt = createdAt;
        this.price = price;
    }

    public static Souvenir newSouvenir(String[] array) {
        if(array.length == 4) {
            return new Souvenir(
                    SouvenirType.findType(array[0]),
                    Integer.parseInt(array[1]),
                    Dates.getDateByYear(array[2]),
                    Double.parseDouble(array[3]));

        } else return EMPTY_SOUVENIR;
    }

    @Override
    public String toString() {
        return String.format("%-7s %-15s %-10s %-10s %-10s",
                "# " + id,
                souvenirType.name(),
                "by: " + getProducer(),
                "at: " + createdAt.getYear(),
                "price: " + price);
    }

    public Producer getProducer() {
        return ProducerDao.get(producerId).orElse(Producer.UNKNOWN_PRODUCER);
    }

    public SouvenirType getSouvenirType() {
        return souvenirType;
    }

    public void setSouvenirType(SouvenirType souvenirType) {
        this.souvenirType = souvenirType;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getIdAutoIncrement() {
        return idAutoIncrement;
    }
    public static void incrementId() {
        idAutoIncrement++;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
