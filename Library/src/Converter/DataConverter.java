package Converter;

public class DataConverter {
	/* Type
	 * 0 - Bytes
	 * 1 - Kilobyte
	 * 2 - Megabyte
	 * 3 - Gigabyte
	 * 4 - Terabyte
	 * 5 - Petabyte
	 * 6 - Exabyte
	 */
	public static double convert(long size, int type) {
		return (double)size/(Math.pow(1024,type));
	}
}
