package logic;

import java.util.Random;

/**
 * Created by Dennis on 15-11-26.
 * This is the factory class that produces Items to the storage class
 */
public class Factory implements Runnable {
    private final Storage storage;
    private final Controller controller;
    private FoodItem[] foodItem;
    private Random rand;
    private boolean runnning;

    public Factory(Storage storage, Controller controller) {
        this.storage = storage;
        this.controller = controller;
        initFooditems ( );

    }

    /*
    * This method initialize my foodItems
    * */
    private void initFooditems() {
        foodItem = new FoodItem[18];
        foodItem[0] = new FoodItem (1.1, 2.5, "Milk");
        foodItem[1] = new FoodItem (2.1, 1.3, "Creme");
        foodItem[2] = new FoodItem (1.5, 1.4, "Yogurt");
        foodItem[3] = new FoodItem (1.1, 1.7, "Butter");
        foodItem[4] = new FoodItem (2.3, 1.3, "Sugar");
        foodItem[5] = new FoodItem (2.8, 2.7, "Orange");
        foodItem[6] = new FoodItem (1.9, 2.6, "Apple");
        foodItem[7] = new FoodItem (3.1, 3.6, "Pear");
        foodItem[8] = new FoodItem (1.4, 4.5, "Hot dogs");
        foodItem[9] = new FoodItem (1.9, 4.8, "Banana");
        foodItem[10] = new FoodItem (3.4, 3.6, "Cheese");
        foodItem[11] = new FoodItem (2.8, 2.7, "Ice cream");
        foodItem[12] = new FoodItem (3.4, 1.5, "Pizza");
        foodItem[13] = new FoodItem (1.4, 0.5, "Pineapple");
        foodItem[14] = new FoodItem (0.9, 2.8, "Egg");
        foodItem[15] = new FoodItem (4.4, 1.6, "Burger");
        foodItem[16] = new FoodItem (0.8, 2.5, "Candy");
        foodItem[17] = new FoodItem (2.4, 3.5, "Juice");


    }

    /*
     *This method make the factory running and produce items
     */


    @Override
    public void run() {

        try {
            while (runnning) {
                rand = new Random ( );
                Thread.sleep (500 + rand.nextInt (300));

                System.out.println (Thread.currentThread ( ));
                int i = rand.nextInt (18);
                storage.add (foodItem[i]);

                if (controller.isT1 ( )) {
                    controller.factoryResting (2);
                }
                if (controller.isT2 ( )) {
                    controller.factoryResting (4);

                }
                if (storage.getNbr ( ) >= 40) {

                    if (controller.isT1 ( )) {
                        controller.factoryResting (1);
                    }
                    if (controller.isT2 ( )) {
                        controller.factoryResting (3);
                    }
                }
            }

        } catch (InterruptedException e) {


        }
    }

    public boolean isRunnning() {
        return runnning;
    }

    public void setRunnning(boolean runnning) {
        this.runnning = runnning;



    }

}
