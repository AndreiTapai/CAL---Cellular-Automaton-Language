package src;

/**
 * Test main goes in here.
 * 
 * @author Fei-Tzin
 *
 */

public class TestDriver {
	public static void main(String[] args) {
		Class<?> mainClass = null;
		Object instance = null;
		if (args.length != 2) {
			System.out
					.println("You must include the class name as an argument");
			System.exit(-1);
		} else {
			String mainClassName = args[0];
			int runFor = Integer.parseInt(args[1]);

			try {
				mainClass = Class.forName(mainClassName);
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

			GUI gui = new GUI(instance, runFor);
			gui.start();
		}
	}
}
