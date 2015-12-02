package logic;

import java.util.concurrent.Semaphore;

/**
 * Created by Dennis on 15-11-26.
 * This method represent the truck that picks up items from the storage
 */
public class Truck implements Runnable {
    private final Storage storage;
    private final Controller controller;
    private final Semaphore semaphore;
    private double maxVolume;
    private double maxWeight;
    private int nbr = 0;


    public Truck(Storage storage, Controller controller, Semaphore semaphore) {
        this.storage = storage;
        this.controller = controller;
        this.semaphore = semaphore;
    }

    /*
     This method is the logic for the Truck
     */
    public void run() {
        while (!Thread.interrupted ( )) {

            if (storage.getGo ( )) {

                try {
                    Thread.sleep (500);
                    if (storage.isEmpty ( )) {
                        doing (2);
                    } else if (!storage.isEmpty ( )) {

                        checkMax ( );

                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    /*
     Runs until the truck is fully loaded.
     */
    private void checkMax() throws InterruptedException {
        if (maxVolume < 30 || maxWeight < 30 || nbr < 20) {
            doing (1);
            nbr++;
            setWeight ( );
            setVolume ( );
            setProductName ( );
            setNbrOfItems ( );
            storage.remove ( );
        }
        if (maxVolume >= 30 || maxWeight >= 30 || nbr >= 20) {
            pickUp ( );
        }
    }

    /*
     When the truck is loaded this method resets the trucks value and make the Thread sleep for 5sec.
     */
    private void pickUp() throws InterruptedException {

        maxVolume = 0;
        maxWeight = 0;
        nbr = 0;
        doing (2);
        controller.truckDone ( );
        doing (3);
        semaphore.release ( );
        Thread.sleep (5000);

    }

    /*
     Count the weight.
     */
    private void setWeight() {
        maxWeight += storage.element ( ).getWeight ( );
        controller.setWeight (maxWeight);
    }

    /*
     Count the volume.
     */
    private void setVolume() {
        maxVolume += storage.element ( ).getVolume ( );
        controller.setVolume (maxVolume);
    }

    /*
     Sends the name of the item to the controller class.
     */
    private void setProductName() {
        controller.setName (storage.element ( ).getName ( ));

    }

    /*
     Count the items.
     */
    private void setNbrOfItems() {
        controller.setNbrOfItems (nbr);
    }

    /*
     Call the method work that have a some switch statements in it.
     */
    private void doing(int work) {
        controller.work (work);
    }


}