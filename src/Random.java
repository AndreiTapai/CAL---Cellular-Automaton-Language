/**
 * 
 * @author Fei-Tzin
 *
 */
public class Random {
	/**
	 * Boolean type random method.
	 * 
	 * (Assumption: there are no ranges for boolean random())
	 * 
	 * @param range
	 * @return
	 */
	public static boolean random(boolean[] range) {
		// no parameters
		if(range == null || range.length == 0) {
			return Math.random() < 0.5;
		}
		
		int i = (int) (Math.random() * range.length);
		return range[i];
	}
	
	/**
	 * Int type random method.
	 * 
	 * @param range
	 * @return
	 */
	public static int random(int[][] range) {
		// no parameters
		if(range == null || range.length == 0) {
			return randInt();
		}
		
		// find number of values in range (this works because int values are
		// discrete)
		int length = 0;
		
		for(int[] r : range) {
			if(r.length == 1) {
				length ++;
			}
			else {
				length += Math.abs(r[1] - r[0]) + 1;
			}
		}
		
		// create new one-dimensional array containing all values
		int[] all = new int[length];
		
		int index = 0;
		
		// initialize
		for(int[] r : range) {
			if(r.length == 1) {
				all[index] = r[0];
				index ++;
			}
			else {
				for(int i = Math.min(r[0], r[1]); i <= Math.max(r[0], r[1]);
						i ++) {
					all[index] = r[0] + i;
					index ++;
				}
			}
		}
		
		// pick random value
		int rand = (int) (Math.random() * all.length);
		return all[rand];
	}
	
	/**
	 * Float type random method.
	 * 
	 * @param range
	 * @return
	 */
	public static float random(float[] range) {
		// no parameters
		if(range == null || range.length == 0) {
			
		}
		
		int i = (int) (Math.random() * range.length);
		return range[i];
	}
	
	/**
	 * Float type random method.
	 * 
	 * @param range
	 * @return
	 */
	public static float random(float[][] range) {
		// no parameters
		if(range == null || range.length == 0) {
			
		}
	}
	
	/**
	 * Double type random method.
	 * 
	 * @param range
	 * @return
	 */
	public static double random(double[] range) {
		// no parameters
		if(range == null || range.length == 0) {
			
		}
		
		int i = (int) (Math.random() * range.length);
		return range[i];
	}
	
	/**
	 * Double type random method.
	 * 
	 * @param range
	 * @return
	 */
	public static double random(double[][] range) {
		// no parameters
		if(range == null || range.length == 0) {
			
		}
	}
	
	/**
	 * Char type random method.
	 * 
	 * @param range
	 * @return
	 */
	public static char random(char[][] range) {
		// no parameters
		if(range == null || range.length == 0) {
			return randChar();
		}

		// find number of values in range (this works because char values are
		// discrete)
		int length = 0;
		
		for(char[] r : range) {
			if(r.length == 1) {
				length ++;
			}
			else {
				length += Math.abs(r[1] - r[0]) + 1;
			}
		}
		
		// create new one-dimensional array containing all values
		char[] all = new char[length];
		
		int index = 0;
		
		// initialize
		for(char[] r : range) {
			if(r.length == 1) {
				all[index] = r[0];
				index ++;
			}
			else {
				for(int i = Math.min(r[0], r[1]); i <= Math.max(r[0], r[1]);
						i ++) {
					all[index] = (char) (r[0] + i);
					index ++;
				}
			}
		}
		
		// pick random value
		int rand = (int) (Math.random() * all.length);
		return all[rand];
	}
	
	/**
	 * String type random method.
	 * 
	 * @param range
	 * @return
	 */
	public static String random(String[] range) {
		// no parameters
		if(range == null || range.length == 0) {
			int length = (int) (Math.random() * 255) + 1;
			return random(length);
		}
		
		int i = (int) (Math.random() * range.length);
		return range[i];
	}
	
	/**
	 * String type random method.
	 * 
	 * @param length The length of the random string to be returned.
	 * @return A random string of the specified length.
	 */
	public static String random(int length) {
		String s = "";
		
		for(int i = 0; i < length; i ++) {
			s += randChar();
		}
		
		return s;
	}
	
	/**
	 * String type random method.
	 * 
	 * @param length
	 * @param strflag It's clumsy, but this is used to differentiate this
	 * method from the int type random().
	 * @return A random string with a length in the specified range.
	 */
	public static String random(int[][] length, boolean strflag) {
		
	}
	
	/**
	 * Helper function. Returns a random integer.
	 * 
	 * @return A random int.
	 */
	public static int randInt() {
		return (int) ((Math.random() - 0.5) * 2 * -1 * Integer.MIN_VALUE);
	}
	
	/**
	 * Helper function. Returns a single random character.
	 * 
	 * @return A random char.
	 */
	public static char randChar() {
		return (char) (int) (Math.random() *
			(Character.MAX_VALUE - Character.MIN_VALUE + 1) +
			Character.MIN_VALUE);
	}
}
