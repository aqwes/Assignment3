package logic;

import guis.GUISemaphore;

/**
 * Created by Dennis on 15-11-27.
 * This class starts all the thread and handles some communication to the Gui from the other classes.
 */
public class Controller {
    private final GUISemaphore gui;
    private final Storage storage;
    private Thread thread1;
    private Thread thread2;
    private boolean t1 = false;
    private boolean t2 = false;
    private Factory factory1;
    private Factory factory2;

    /*
     Constructor that receives a gui item and makes a new Semaphore and a storage
     */
    public Controller(GUISemaphore gui) {
        this.gui = gui;
        storage = new Storage (this);
        
    }

    /*
    Start the producer thread A
     */
    public void startProducerA() {
        thread1 = new Thread (factory1 = new Factory (storage, this));
        thread1.start ( );
        factory1.setRunnning (true);
        t1 = true;
        factoryResting (2);
    }

    /*
    Start the producer thread B
     */
    public void startProducerB() {
        thread2 = new Thread (factory2 = new Factory (storage, this));
        thread2.start ( );
        factory2.setRunnning (true);
        t2 = true;
        factoryResting (4);

    }

    /*
    Start the delivery thread
     */
    public void startDeliver() {
        Thread thread3 = new Thread (new Truck (storage, this));
        thread3.start ( );
    }
    /*
    Stops the producer thread A
    */

    public void stopProducerA() {
        factory1.setRunnning (false);
        thread1.interrupt ( );
        factoryResting (1);
        t1 = false;
    }

    /*
    Start the producer thread B
     */
    public void stopProducerB() {
        factory2.setRunnning (false);
        factoryResting (3);
        t2 = false;
    }

    /*
    Sets number of items in the gui class
     */
    public void setNbrOfItems(int numberOfITEMS) {
        gui.setLblLimNrs ("Items " + numberOfITEMS);
    }

    /*
    Sets weight in the gui class
     */
    public void setWeight(double weight) {
        gui.setLblLimWeight ("Weight " + weight);
    }

    /*
    Sets volume in the gui class
     */
    public void setVolume(double volume) {
        gui.setLblLimVolume ("Volume " + volume);
    }

    /*
    Append names in the gui class
     */
    public void setName(String name) {
        gui.setLstTruck (name + "\n");
    }

    /*
    Changes the bufferStatusBar
     */
    public void setBufferStatus(int value) {
        gui.setBufferStatus (value);
    }

    /*
    Sets labels to their correct activity
     */
    public void work(int work) {
        switch (work) {

            case 1:
                gui.setLblTruckStatus ("Loading...");
                gui.setLblDeliver ("");
                break;

            case 2:
                gui.setLblTruckStatus ("Waiting...");
                break;

            case 3:
                gui.setLblTruckStatus ("Truck limited by weight...");
                gui.setLblDeliver ("Truck Delivering");
                break;


        }
    }

    /*
    Empty the items list with all the foodItems
     */
    public void truckDone() {
        gui.empty ( );


    }

    /*
    Changes the label for the factory to it´ correct activity
     */
    public void factoryResting(int b) {
        switch (b) {
            case 1:
                gui.setLblAStatus ("Producer: Resting");
                break;

            case 2:
                gui.setLblAStatus ("Producer: Working");
                break;

            case 3:
                gui.setLblBStatus ("Producer: Resting");
                break;

            case 4:
                gui.setLblBStatus ("Producer: Working");
                break;
        }
    }

    /*
    Changes the working and resting label for factory 1
     */
    public boolean isT1() {
        return t1;
    }

    /*
    Changes the working and resting label for factory 1
     */
    public boolean isT2() {
        return t2;
    }
}



