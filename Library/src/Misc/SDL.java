package Misc;

import java.util.ArrayList;

public class SDL {
	public static <T> void print(T var) {
		System.out.print(var + "");
	}

	public static <T> void print(ArrayList<T> var) {
		for (T t : var)
			print(t);
	}
	public static <T> void print(T[] var) {
		print("[");
		for (int z=0;z<var.length;z++) {
			print(var[z]);
			if(z < var.length-1)
				print(", ");
		}
		print("]");
	}

	public static void println() {
		System.out.println();
	}

	public static <T> void println(T var) {
		print(var);
		println();
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String strip(String str) {
		while (str.startsWith(" "))
			str = str.substring(1);
		while (str.endsWith(" "))
			str = str.substring(0, str.length() - 1);
		return str;
	}
	
	public static void errorAndExit(String str) {
		System.err.println();
		System.exit(0);
	}

	// Can't templetize it sadly
	public static String[] append(String str, String[] list) {
		int size = 1;
		if(list != null) size = list.length + 1;
		String[] nList = new String[size];
		for (int z = 0; z < list.length; z++)
			nList[z] = list[z];
		nList[size - 1] = str;
		return nList;
	}

	public static boolean isValidIP(String ip) {
		try {
			if (ip == null || ip.isEmpty())
				return false;

			String[] parts = ip.split("\\.");
			if (parts.length != 4)
				return false;

			for (String s : parts) {
				int i = Integer.parseInt(s);
				if ((i < 0) || (i > 255))
					return false;
			}
			if (ip.endsWith("."))
				return false;

			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	public static <T> boolean isIn(T str, T[] list) {
		if(list == null) return false;
		for(T s:list)
			if(str.equals(s))
				return true;
		return false;
	}
	public static boolean isInContains(String str, String[] list) {
		if(list == null) return false;
		for(String s:list)
			if(str.contains(s))
				return true;
		return false;
	}
//	public static <T> void print(T str) { System.out.print(str+""); }
//	public static void println() { System.out.println(); }
//	public static <T> void println(T str) { System.out.println(str+""); }
//	public static void sleep(long time) { try { Thread.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); } }
}
