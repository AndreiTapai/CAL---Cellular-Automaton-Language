package cal.essentials;

import java.io.*;
import java.util.Scanner;

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
	
	/**
	 * Autoindents the specified Java file.
	 * 
	 * @param filename The file to format.
	 */
	public static void format(String filename) {
		File file = new File(filename);
		try {
			FileInputStream s = new FileInputStream(file);
			Scanner in = new Scanner(s);
			
			int level = 0;
			
			String code = "";
			String line;
			
			while(in.hasNextLine()) {
				line = in.nextLine();
				level -= numOccurrences('}', line);
				
				for(int i = 0; i < level; i ++) {
					line = "\t" + line;
				}
				
				code += line + "\n";
				
				level += numOccurrences('{', line);
			}
			
			in.close();
			s.close();
			
			PrintWriter w = new PrintWriter(new FileOutputStream(filename, false));
			
			w.write(code);
			
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
	 * Finds the number of occurrences of the given character in the given
	 * string s.
	 * 
	 * @param c The char to search for.
	 * @param s The string to search in.
	 * @return The number of occurrences of c in s.
	 */
	public static int numOccurrences(char c, String s) {
		int occ = 0;
		
		for(int i = 0; i < s.length(); i ++) {
			if(s.charAt(i) == c) {
				occ ++;
			}
		}
		
		return occ;
	}
}
