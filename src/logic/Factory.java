package logic;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by Dennis on 15-11-26.
 * This is the factory class that produces Items to the storage class
 */
public class Factory implements Runnable {
    private final Storage storage;
    private final Semaphore semaphore;
    private final Controller controller;
    private FoodItem[] foodItem;

    public Factory(Storage storage, Semaphore semaphore, Controller controller) {
        this.storage = storage;
        this.semaphore = semaphore;
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

        while (!Thread.interrupted ( )) {
            Random rand = new Random ( );
            try {
                Thread.sleep (450 + rand.nextInt (100));
                rand = new Random ( );
                if (storage.getNbr ( ) < 40) {
                    storage.setGo (false);

                    int i = rand.nextInt (18);
                    storage.add (foodItem[i]);

storage.setGo (false);

                    if (controller.isT1 ( )) {
                        controller.factoryResting (2);
                    }
                    if (controller.isT2 ( )) {
                        controller.factoryResting (4);
                    }
                } else if (storage.getNbr ( ) == 40) {


                    if (controller.isT1 ( )) {
                            controller.factoryResting (1);
                }
                    if (controller.isT2 ( )) {
                        controller.factoryResting (3);
                    }
                    storage.setGo (true);
               semaphore.acquire ();
                        }
            }

                 catch(InterruptedException e) {
                break; }
        }

    }
}
