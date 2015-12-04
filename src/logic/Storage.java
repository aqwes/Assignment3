package logic;

import sun.awt.Mutex;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Created by Dennis on 15-11-26.
 * This class represent the storage
 */
public class Storage {
    private final Controller controller;

    private final Queue<FoodItem> foodItems = new LinkedList<> ( );
    private boolean go;
    private int nbr;
    private Semaphore rDsemaphore;
    private Semaphore wRsemaphore;
    private Mutex lock;

    public Storage(Controller controller) {
        this.controller = controller;
        wRsemaphore = new Semaphore (40);
        rDsemaphore = new Semaphore (0);
        lock = new Mutex ( );

    }

    /*
     Add food items to the que
     */
    public void add(FoodItem foodItem) {
        try {
            wRsemaphore.acquire ( );
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }

        synchronized (lock) {
            foodItems.add (foodItem);
            nbr++;
            setBufferStatus (nbr);

        }
        rDsemaphore.release ( );
    }

    /*
     Remove food items to the que
     */
    public void remove() {
        try {
            rDsemaphore.acquire ( );
        } catch (InterruptedException e) {
            e.printStackTrace ( );
        }
        synchronized (lock) {
            nbr--;
            setBufferStatus (nbr);
            controller.work (1);
            foodItems.remove ( );
        }

        wRsemaphore.release ( );

    }

    /*
     Checks if the que is empty
     */
    public boolean isEmpty() {
        return foodItems.isEmpty ( );
    }
/*
 Return the current item for examine
 */

    public FoodItem element() {
        return foodItems.element ( );
    }

    /*
    Changes the bufferStatusbar
     */
    private void setBufferStatus(int nbr) {
        controller.setBufferStatus (nbr);
    }

    /*
    Return nbr so we can check in the factory if it is time to stop
     */
    public int getNbr() {
        return nbr;
    }

    public boolean getGo() {
        return go;
    }

    /*
    These method decides if the truck can pick up items or not
     */
    public void setGo(boolean go) {
        this.go = go;
    }


}



