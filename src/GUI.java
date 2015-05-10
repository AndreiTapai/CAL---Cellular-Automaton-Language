/**
 * GUI class. Multithreaded.
 * 
 * @authors Fei-Tzin Lee, Andrei Tapai
 *
 */

package src;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI extends Thread {
	private CAL_GUI gui;
	private Object obj;
	private Class<?> oclass; // main class

	/**
	 * Public default constructor.
	 */
	public GUI(Object object, Class cell, int runFor) {
		// Field[] fields = getFields(classname);
		// Method[] methods = getMethods(classname);

		obj = object;
		oclass = obj.getClass();

		try {
			Field fgx = oclass.getField("gridgx");
			Field fgy = oclass.getField("gridgy");

			// constructor = c.getConstructor();
			// loadedObject = constructor.newInstance();
			// loadedObject.getClass().cast(obj);

			int gx = fgx.getInt(obj);
			int gy = fgy.getInt(obj);

			gui = new CAL_GUI(gx, gy, cell);
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
		}/*
		 * catch (InstantiationException ex) { ex.printStackTrace(); } catch
		 * (InvocationTargetException ex) { ex.printStackTrace(); } catch
		 * (NoSuchMethodException ex) { ex.printStackTrace(); }
		 */
	}

	/**
	 * Gets declared fields of the specified class.
	 * 
	 * @param name
	 *            The full name of the class.
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
	 * @param name
	 *            The full name of the class.
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
	 * @param name
	 *            The full name of the class.
	 */
	public Field[] getFields(String name) {
		try {
			Class c = Class.forName(name);

			Field[] inherited = c.getFields();
			Field[] declared = c.getDeclaredFields();

			Field[] fields = new Field[inherited.length + declared.length];

			for (int i = 0; i < inherited.length; i++) {
				fields[i] = inherited[i];
			}

			for (int i = 0; i < declared.length; i++) {
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
	 * @param name
	 *            The full name of the class.
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
	 * @param name
	 *            The full name of the class.
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
	 * @param name
	 *            The full name of the class.
	 */
	public Method[] getMethods(String name) {
		try {
			Class c = Class.forName(name);

			Method[] inherited = c.getMethods();
			Method[] declared = c.getDeclaredMethods();

			Method[] methods = new Method[inherited.length + declared.length];

			for (int i = 0; i < inherited.length; i++) {
				methods[i] = inherited[i];
			}

			for (int i = 0; i < declared.length; i++) {
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
		Method meth = null;
		Field fArrayList = null;
		ArrayList<?> arrayList;

		try {
			meth = oclass.getMethod("cal_it", null);
		} catch (NoSuchMethodException ex) {
		} catch (SecurityException ex) {
		}
		while (true) {
			try {

				fArrayList = oclass.getField("cells");
				arrayList = (ArrayList<?>) fArrayList.get(obj);

				gui.retrieveCellList(arrayList);
				gui.render();

				Thread.sleep(2000);

				meth.invoke(obj, null);

			} catch (IllegalAccessException ex) {
				System.out.println(ex.getMessage());
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			} catch (InvocationTargetException ex) {
				System.out.println(ex.getMessage());
			} catch (NoSuchFieldException ex) {
				System.out.println(ex.getMessage());
			} catch (SecurityException ex) {
				System.out.println(ex.getMessage());
			} catch (InterruptedException ex) {
				System.out.println("Interupted");
			}

		}
	}
}
