package Misc;

public class SDL {
	public static <T> void print(T str) { System.out.print(str+""); }
	public static void println() { System.out.println(); }
	public static <T> void println(T str) { System.out.println(str+""); }
	public static void sleep(long time) { try { Thread.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); } }
}
