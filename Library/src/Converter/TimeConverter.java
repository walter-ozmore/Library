package Converter;

public class TimeConverter {
	public static double nanoToMillisecond(double time) { return time / 1000000.0; }
	public static double nanoToMillisecond(long time) { return time / 1000000.0; }
	public static double nanoToSecond(long time) { return time / 1000000000.0; }
	public static double nanoToMinute(long time) { return nanoToSecond(time)/60.0; }
	public static double nanoToHour(long time) { return nanoToMinute(time)/60.0; }
	public static double nanoToDay(long time) { return nanoToHour(time)/24.0; }
	
	public static String nanoToAutomatic(long time) {
		if( nanoToMillisecond(time) > 1 ) {
			if( nanoToSecond(time) > 1 ) {
				if( nanoToMinute(time) > 1 ) {
					return nanoToMinute(time)+" Minute";
				}
				return nanoToSecond(time)+" Second";
			}
			return nanoToMillisecond(time)+" Milliseconds";
		}
		return "UNKNOWN";
	}
}
