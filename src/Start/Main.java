
package Start;

import guis.GUISemaphore;
import logic.Controller;

/**
 * Created by Dennis on 15-11-26.
 * <p>
 * Start the Program.
 */
public class Main {
    public static void main(String[] args) {
        GUISemaphore gui = new GUISemaphore ( );
        Controller controller = new Controller (gui);
        gui.Start (controller);
    }

}
