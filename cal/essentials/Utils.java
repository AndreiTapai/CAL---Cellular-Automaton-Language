package cal.essentials;

import java.io.*;

public class Utils {
	/**
	 * Writes a string to the specified file.
	 * 
	 * @param filename
	 *            The name of the file to write to.
	 * @param s
	 *            The string to write.
	 */
	public static void write(String filename, String s) {
		try {
			FileWriter w = new FileWriter(filename);

			w.write(s);

			w.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Appends a string to the specified file.
	 * 
	 * @param filename
	 *            The name of the file to write to.
	 * @param s
	 *            The string to write.
	 */
	public static void append(String filename, String s) {
		try {
			FileWriter w = new FileWriter(filename, true);

			w.write(s);

			w.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
