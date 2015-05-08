
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Test main goes in here.
 * 
 * @author Fei-Tzin
 *
 */

public class TestDriver {
	public static void main(String[] args) {
            Class cl = null;
            Object instance = null;
                if (args.length != 1)
                {
                    System.out.println("You must include the class name as an argument");
                    System.exit(-1);
                }
                else
                {
                    String className = args[0];
                    try {
                        cl = Class.forName(className);
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    
                try {
                    instance = cl.newInstance();
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }

                    GUI gui = new GUI(instance);
                    gui.start();
                }
            
		
	}
}
