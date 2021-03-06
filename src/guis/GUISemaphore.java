
package guis;

import logic.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI for assignment 3
 */
public class GUISemaphore implements ActionListener {
    /**
     * These are the components you need to handle.
     * You have to add listeners and/or code
     */
    private JFrame frame;                // The Main window
    private JProgressBar bufferStatus;    // The progressbar, showing content in buffer
    private JTextArea lstTruck;            // The truck cargo list
    private JLabel lblLimWeight;        // Label showing weight limit
    private JLabel lblLimVolume;        // Label showing volume limit
    private JLabel lblLimNrs;            // Label showing max nr of food
    private JLabel lblTruckStatus;        // Label showing truck waiting or loading
    private JButton btnDeliver;            // Button for starting deliverance
    private JLabel lblDeliver;            // Label showing one truck done, hides when next truck arrives
    private JLabel lblBStatus;            // Label showing factory B status: Hidden, working, waiting or stopped
    private JLabel lblAStatus;            // Label showing factory A status: Hidden, working, waiting or stopped
    private JButton btnStartB;            // Button start factory B
    private JButton btnStartA;            // Button start factory A
    private JButton btnStopB;            // Button stop factory B
    private JButton btnStopA;            // Button stop factory A
    private Controller controller;

    /**
     * Constructor, creates the window
     */
    public GUISemaphore() {
    }

    /**
     * Starts the application
     */
    public void Start(Controller controller) {
        this.controller = controller;
        frame = new JFrame ( );
        frame.setBounds (0, 0, 652, 459);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout (null);
        frame.setTitle ("ICA Item Delivery");
        InitializeGUI ( );                    // Fill in components
        frame.setVisible (true);
        frame.setResizable (false);            // Prevent user from change size
        frame.setLocationRelativeTo (null);    // Start middle screen
    }

    /**
     * Sets up the GUI with components
     */
    private void InitializeGUI() {
        // First create the four panels
        JPanel pnlBuffer = new JPanel ( );
        pnlBuffer.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder (Color.black), "Storage"));
        pnlBuffer.setBounds (27, 25, 298, 71);
        pnlBuffer.setLayout (null);
        frame.add (pnlBuffer);
        JPanel pnlICA = new JPanel ( );
        pnlICA.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder (Color.black), "Delivery to ICA"));
        pnlICA.setBounds (27, 115, 298, 281);
        pnlICA.setLayout (null);
        frame.add (pnlICA);
        JPanel pnlFoodb = new JPanel ( );
        pnlFoodb.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder (Color.black), "Food Factory B"));
        pnlFoodb.setBounds (381, 25, 246, 158);
        pnlFoodb.setLayout (null);
        frame.add (pnlFoodb);
        JPanel pnlFooda = new JPanel ( );
        pnlFooda.setBorder (BorderFactory.createTitledBorder (BorderFactory.createLineBorder (Color.black), "Food Factory A"));
        pnlFooda.setBounds (381, 238, 246, 158);
        pnlFooda.setLayout (null);
        frame.add (pnlFooda);

        // Then create the progressbar, only component in buffer panel
        bufferStatus = new JProgressBar ( );
        bufferStatus.setBounds (17, 31, 262, 23);
        bufferStatus.setBorder (BorderFactory.createLineBorder (Color.black));
        bufferStatus.setForeground (Color.GREEN);
        pnlBuffer.add (bufferStatus);

        // Next set up components for the ICA Delivery frame, first static fields
        JLabel lab1 = new JLabel ("Truck Cargo:");
        lab1.setBounds (75, 24, 79, 13);
        pnlICA.add (lab1);
        JLabel lab2 = new JLabel ("Truck Limits:");
        lab2.setBounds (17, 54, 77, 13);
        pnlICA.add (lab2);

        // Then variables used in program, add code/listeners
        lstTruck = new JTextArea ( );
        lstTruck.setEditable (false);
        lstTruck.setLineWrap (true);
        JScrollPane spane = new JScrollPane (lstTruck);
        spane.setBounds (159, 19, 120, 147);
        spane.setBorder (BorderFactory.createLineBorder (Color.black));
        pnlICA.add (spane);
        lblLimWeight = new JLabel ("Weight limit goes here");
        lblLimWeight.setBounds (20, 83, 200, 13);
        pnlICA.add (lblLimWeight);
        lblLimVolume = new JLabel ("Volume limit goes here");
        lblLimVolume.setBounds (20, 96, 200, 13);
        pnlICA.add (lblLimVolume);
        lblLimNrs = new JLabel ("Max items goes here");
        lblLimNrs.setBounds (20, 109, 200, 13);
        pnlICA.add (lblLimNrs);
        lblTruckStatus = new JLabel ("New Truck: No Items...");
        lblTruckStatus.setFont (new Font ("Dejavu Sans", Font.PLAIN, 20));
        lblTruckStatus.setBounds (20, 199, 200, 20);
        pnlICA.add (lblTruckStatus);
        btnDeliver = new JButton ("Start Deliver");
        btnDeliver.setBounds (17, 239, 109, 23);
        pnlICA.add (btnDeliver);
        lblDeliver = new JLabel ("Truck Delivering");
        lblDeliver.setFont (new Font ("Dejavu Sans", Font.BOLD, 20));
        lblDeliver.setForeground (Color.RED);
        lblDeliver.setBounds (133, 237, 200, 25);
        pnlICA.add (lblDeliver);

        // Static text in Food Factory B
        JLabel lab3 = new JLabel ("Status:");
        lab3.setBounds (35, 31, 60, 13);
        pnlFoodb.add (lab3);

        // and variables used in program, add code/listeners
        lblBStatus = new JLabel ("Producer Status here");
        lblBStatus.setFont (new Font ("Dejavu Sans", Font.PLAIN, 20));
        lblBStatus.setBounds (38, 65, 200, 25);
        pnlFoodb.add (lblBStatus);
        btnStartB = new JButton ("Start Working");
        btnStartB.setBounds (31, 114, 116, 23);
        pnlFoodb.add (btnStartB);
        btnStopB = new JButton ("Stop");
        btnStopB.setBounds (156, 114, 60, 23);
        pnlFoodb.add (btnStopB);

        // Static text in Food Factory A
        JLabel lab4 = new JLabel ("Status:");
        lab4.setBounds (35, 31, 60, 13);
        pnlFooda.add (lab4);

        // and variables used in program, add code/listeners
        lblAStatus = new JLabel ("Producer Status here");
        lblAStatus.setFont (new Font ("Dejavu Sans", Font.PLAIN, 20));
        lblAStatus.setBounds (38, 65, 200, 25);
        pnlFooda.add (lblAStatus);
        btnStartA = new JButton ("Start Working");
        btnStartA.setBounds (31, 114, 116, 23);
        pnlFooda.add (btnStartA);
        btnStopA = new JButton ("Stop");
        btnStopA.setBounds (156, 114, 60, 23);
        pnlFooda.add (btnStopA);

        btnStartA.addActionListener (this);
        btnStartB.addActionListener (this);
        btnStopA.addActionListener (this);
        btnStopB.addActionListener (this);
        btnDeliver.addActionListener (this);
        bufferStatus.setValue (0);
        bufferStatus.setMinimum (0);
        bufferStatus.setMaximum (40);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource ( ) == btnStartA) {
            controller.startProducerA ( );
            btnStartA.setEnabled (false);
            btnStopA.setEnabled (true);

        }
        if (e.getSource ( ) == btnStartB) {
            controller.startProducerB ( );
            btnStartB.setEnabled (false);
            btnStopB.setEnabled (true);
        }
        if (e.getSource ( ) == btnDeliver) {
            controller.startDeliver ( );
            btnDeliver.setEnabled (false);
        }
        if (e.getSource ( ) == btnStopA) {
            controller.stopProducerA ( );
            btnStartA.setEnabled (true);
            btnStopA.setEnabled (false);
        }
        if (e.getSource ( ) == btnStopB) {
            controller.stopProducerB ( );
            btnStartB.setEnabled (true);
            btnStopB.setEnabled (false);
        }
    }


    public void setBufferStatus(int value) {

        System.out.println (value);
        bufferStatus.setValue (value);
    }


    public void setLstTruck(String lstTruck1) {
        lstTruck.append (lstTruck1);
    }


    public void setLblLimWeight(String lblLimWeight1) {

        lblLimWeight.setText (lblLimWeight1);
    }

    public void setLblLimVolume(String lblLimVolume1) {
        lblLimVolume.setText (lblLimVolume1);
    }

    public void setLblLimNrs(String lblLimNrs1) {

        lblLimNrs.setText (lblLimNrs1);
    }


    public void setLblTruckStatus(String lblTruckStatus1) {
        lblTruckStatus.setText (lblTruckStatus1);
    }


    public void setLblBStatus(String lblBStatus1) {

        lblBStatus.setText (lblBStatus1);
    }


    public void setLblAStatus(String lblAStatus1) {
        lblAStatus.setText (lblAStatus1);
    }


    public void setLblDeliver(String lblDeliver1) {
        lblDeliver.setText (lblDeliver1);
    }

    public void empty() {

        lstTruck.setText ("");
        lblLimWeight.setText ("Weight limit goes here");
        lblLimVolume.setText ("Volume limit goes here");
        lblLimNrs.setText ("Max items goes here");
    }
}

