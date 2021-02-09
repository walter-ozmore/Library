package Misc;

public class TimeConverter {
	public static double nanoToMillisecond(double time) { return time / 1000000.0; }
	public static double nanoToMillisecond(long time) { return time / 1000000.0; }
	public static double nanoToSecond(long time) { return time / 1000000000.0; }
}
