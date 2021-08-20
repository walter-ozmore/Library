package Misc;

public class SDL {
	public static void print(String str) { System.out.print(str); }
	public static void println() { System.out.println(); }
	public static void println(String str) { System.out.println(str); }
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
