package logic;

/**
 * Created by Dennis on 15-11-26.
 * Makes it possible to get a food items name, volume and weight.
 */
public class FoodItem {

    private final String name;
    private final double volume;
    private final double weight;

    public FoodItem(double volume, double weight, String name) {
        this.name = name;
        this.volume = volume;
        this.weight = weight;

    }

    /*
     Get a foodItems name.
     */
    public String getName() {
        return name;
    }

    /*
     Get a foodItems volume.
     */
    public double getVolume() {
        return volume;
    }

    /*
     Get a foodItems weight.
     */
    public double getWeight() {
        return weight;
    }

}


