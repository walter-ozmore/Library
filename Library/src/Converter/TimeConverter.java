package Converter;

import java.util.Date;

import Misc.Mat;

public class TimeConverter {
	public static int round = 2;
	public static double nanoToMicrosecond(long time) { return Mat.round( time / 1000.0, round ); }
	public static double nanoToMillisecond(double time) { return Mat.round( time / 1000000.0, round ); }
	public static double nanoToMillisecond(long time) { return Mat.round( time / 1000000.0, round ); }
	public static double nanoToSecond(long time) { return Mat.round( time / 1_000_000_000.0, round ); }
	public static double nanoToMinute(long time) { return Mat.round( nanoToSecond(time)/60.0, round ); }
	public static double nanoToHour(long time) { return Mat.round( nanoToMinute(time)/60.0, round ); }
	public static double nanoToDay(long time) { return Mat.round( nanoToHour(time)/24.0, round ); }
	
	public static String nanoToAutomatic(long time) {
		if( nanoToMicrosecond(time) > 1 ) {
			if( nanoToMillisecond(time) > 1 ) {
				if( nanoToSecond(time) > 1 ) {
					if( nanoToMinute(time) > 1 ) {
						if( nanoToHour(time) > 1 ) {
							if( nanoToDay(time) > 1 ) {
								if(round == 0) return (int)nanoToDay(time)+" Days";
								return nanoToDay(time)+" Days";
							}
							if(round == 0) return (int)nanoToHour(time)+" Hours";
							return nanoToHour(time)+" Hours";
						}
						if(round == 0) return (int)nanoToMinute(time)+" Minutes";
						return nanoToMinute(time)+" Minutes";
					}
					if(round == 0) return (int)nanoToSecond(time)+" Seconds";
					return nanoToSecond(time)+" Seconds";
				}
				if(round == 0) return (int)nanoToMillisecond(time)+" Milliseconds";
				return nanoToMillisecond(time)+" Milliseconds";
			}
			if(round == 0) return (int)nanoToMillisecond(time)+" Microseconds";
			return nanoToMillisecond(time)+" Microseconds";
		}
		if(round == 0) return (int)time+" Nanoseconds";
		return time+" Nanoseconds";
	}
	
	public static long getUnixTime() {
		return System.currentTimeMillis() / 1000L;
	}
	public static Date unixToDate(long time) {
		return new Date(time * 1000L);
	}
}
