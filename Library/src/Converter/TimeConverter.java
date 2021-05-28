package Converter;

import java.util.Date;

import Misc.Mat;

public class TimeConverter {
	static int round = 2;
	public static double nanoToMillisecond(double time) { return Mat.round( time / 1000000.0, round ); }
	public static double nanoToMillisecond(long time) { return Mat.round( time / 1000000.0, round ); }
	public static double nanoToSecond(long time) { return Mat.round( time / 1_000_000_000.0, round ); }
	public static double nanoToMinute(long time) { return Mat.round( nanoToSecond(time)/60.0, round ); }
	public static double nanoToHour(long time) { return Mat.round( nanoToMinute(time)/60.0, round ); }
	public static double nanoToDay(long time) { return Mat.round( nanoToHour(time)/24.0, round ); }
	
	public static String nanoToAutomatic(long time) {
		if( nanoToMillisecond(time) > 1 ) {
			if( nanoToSecond(time) > 1 ) {
				if( nanoToMinute(time) > 1 ) {
					if( nanoToHour(time) > 1 ) {
						if( nanoToDay(time) > 1 ) {
							return nanoToDay(time)+" Days";
						}
						return nanoToHour(time)+" Hours";
					}
					return nanoToMinute(time)+" Minutes";
				}
				return nanoToSecond(time)+" Seconds";
			}
			return nanoToMillisecond(time)+" Milliseconds";
		}
		return time+" Nanoseconds";
	}
	
	public static long getUnixTime() {
		return System.currentTimeMillis() / 1000L;
	}
	public static Date unixToDate(long time) {
		return new Date(time * 1000L);
	}
}
