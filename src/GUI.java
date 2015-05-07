/**
 * GUI class. Multithreaded.
 * 
 * @authors Fei-Tzin Lee, Andrei Tapai
 *
 */

import java.lang.reflect.*;

public class GUI extends Thread {
	private CAL_GUI gui;
	private Object obj;
	private Class<?> c;
	
	/**
	 * Public default constructor.
	 */
	public GUI(Object object) {
		//Field[] fields = getFields(classname);
		//Method[] methods = getMethods(classname);
		
		obj = object;
		c = obj.getClass();
		
		try {
			Field fgx = c.getField("gridgx");
			Field fgy = c.getField("gridgy");
			
			int gx = fgx.getInt(obj);
			int gy = fgy.getInt(obj);
			
			gui = new CAL_GUI(gx, gy);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets declared fields of the specified class.
	 * 
	 * @param name The full name of the class.
	 */
	public Field[] getDeclaredFields(String name) {
		try {
			Class c = Class.forName(name);
			return c.getDeclaredFields();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Gets inherited fields of the specified class.
	 * 
	 * @param name The full name of the class.
	 */
	public Field[] getInheritedFields(String name) {
		try {
			Class c = Class.forName(name);
			return c.getFields();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Gets all fields of the specified class.
	 * 
	 * @param name The full name of the class.
	 */
	public Field[] getFields(String name) {
		try {
			Class c = Class.forName(name);
			
			Field[] inherited = c.getFields();
			Field[] declared = c.getDeclaredFields();
			
			Field[] fields = new Field[inherited.length + declared.length];
			
			for(int i = 0; i < inherited.length; i ++) {
				fields[i] = inherited[i];
			}
			
			for(int i = 0; i < declared.length; i ++) {
				fields[i + inherited.length] = declared[i];
			}
			
			return fields;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Gets declared methods of the specified class.
	 * 
	 * @param name The full name of the class.
	 */
	public Method[] getDeclaredMethods(String name) {
		try {
			Class c = Class.forName(name);
			return c.getDeclaredMethods();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Gets inherited methods of the specified class.
	 * 
	 * @param name The full name of the class.
	 */
	public Method[] getInheritedMethods(String name) {
		try {
			Class c = Class.forName(name);
			return c.getMethods();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Gets all methods of the specified class.
	 * 
	 * @param name The full name of the class.
	 */
	public Method[] getMethods(String name) {
		try {
			Class c = Class.forName(name);
			
			Method[] inherited = c.getMethods();
			Method[] declared = c.getDeclaredMethods();
			
			Method[] methods = new Method[inherited.length + declared.length];
			
			for(int i = 0; i < inherited.length; i ++) {
				methods[i] = inherited[i];
			}
			
			for(int i = 0; i < declared.length; i ++) {
				methods[i + inherited.length] = declared[i];
			}
			
			return methods;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Override from Thread superclass.
	 */
	public void run() {
		
	}
}
