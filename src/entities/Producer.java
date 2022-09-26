package entities;

import java.util.Arrays;

/**
 * Created by Serhx4 on 9/17/2022.
 */
public class Producer {

    public enum Country {
        JAVA_ISLAND, USA, UKRAINE, UNKNOWN;

        public static Country findCountry(String name) {
            return Arrays.stream(Country.values())
                    .filter(type -> type.name().equals(name.toUpperCase()))
                    .findFirst().orElse(UNKNOWN);
        }
    }

    public static final Producer UNKNOWN_PRODUCER = new Producer(-1, "unknown", Country.UNKNOWN);

    private static int idAutoIncrement = 0;

    private int id;
    private String name;
    private Country country;

    public Producer(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
    public static Producer newProducer(String[] array) {
        if (array.length == 3) {
            return new Producer(
                    Integer.parseInt(array[0]),
                    array[1],
                    Country.findCountry(array[2]));
        } else return null;
    }
    @Override
    public String toString() {
        return String.format("%-7s %-20s",
                "# " + id,
                name + ", " + country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public static int getIdAutoIncrement() {
        return idAutoIncrement;
    }
    public static void incrementId() {
        idAutoIncrement++;
    }
}
