/**
 * GUI class. Multithreaded.
 * 
 * @authors Fei-Tzin Lee, Andrei Tapai
 *
 */
public class GUI extends Thread {
	private CAL_GUI gui;
	
	/**
	 * Public default constructor.
	 * 
	 * TODO: add parameters
	 */
	public GUI() {
		gui = new CAL_GUI();
	}
	
	/**
	 * Override from Thread superclass.
	 */
	public void run() {
		
	}
}
