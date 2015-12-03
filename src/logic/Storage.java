package logic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Dennis on 15-11-26.
 * This class represent the storage
 */
public class Storage {
    private final Controller controller;
    private final Queue<FoodItem> foodItems = new LinkedList<> ( );
    private boolean go;
    private int nbr;

    public Storage(Controller controller) {
        this.controller = controller;


    }

    /*
     Add food items to the que
     */
    public void add(FoodItem foodItem) {

        foodItems.add (foodItem);
        nbr++;
        setBufferStatus (nbr);

        Random rand = new Random ( );

    }

    /*
     Remove food items to the que
     */
    public void remove() {
        nbr--;
        setBufferStatus (nbr);
        controller.work (1);
        foodItems.remove ( );
        setGo (true);

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



