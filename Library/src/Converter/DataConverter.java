package Converter;

import Misc.Mat;

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
	public static String toAutomatic(long size) {
		int z = 0;
		while(convert(size,z++) > 1 && z<10);
		z -= 2;
		double val = Mat.round(convert(size,z), 1);
		switch(z) {
			case 0:return val+" Bytes";
			case 1:return val+" Kilobytes";
			case 2:return val+" Megabytes";
			case 3:return val+" Gigabytes";
			case 4:return val+" Terabytes";
			case 5:return val+" Petabytes";
			case 6:return val+" Exabytes";
		}
		return size+" Bytes";
	}
}
