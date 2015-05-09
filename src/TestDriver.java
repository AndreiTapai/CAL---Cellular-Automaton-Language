
/**
 * Test main goes in here.
 * 
 * @author Fei-Tzin
 *
 */

public class TestDriver {
	public static void main(String[] args) {
		Class mainClass = null;
		Class cellClass = null;
		Object instance = null;
		if (args.length != 3) {
			System.out
					.println("You must include the class name as an argument");
			System.exit(-1);
		} else {
			String mainClassName = args[0];
			String cellClassName = args[1];
			int runFor = Integer.parseInt(args[2]);

			try {
				mainClass = Class.forName(mainClassName);
				cellClass = Class.forName(cellClassName);
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}

			try {
				instance = mainClass.newInstance();
			} catch (InstantiationException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}

			GUI gui = new GUI(instance, cellClass, runFor);
			gui.start();
		}
	}
}
