package Encription;

public class Encrypt {
	static int latestVersion = 1;
	
	public static byte[] encrypt(byte[] list, String key) { return encrypt(list,key,latestVersion); }
	public static byte[] encrypt(byte[] list, String key, int v) {
		switch(v) {
			case 1:
				for(int a=1;a<key.length();a++)
					for(int z=0;z<list.length;z++) {
						if(z % 2 == key.charAt(a-1)%3)
							list[z] += key.charAt(a);
						else
							list[z] -= key.charAt(a);
					}
				return list;
		}
		return list;
	}
	
	public static byte[] decrypt(byte[] list, String key) { return decrypt(list,key,latestVersion); }
	public static byte[] decrypt(byte[] list, String key, int v) {
		switch(v) {
			case 1:
				for(int a=1;a<key.length();a++)
					for(int z=0;z<list.length;z++){
						if(z % 2 == key.charAt(a-1)%3)
							list[z] -= key.charAt(a);
						else
							list[z] += key.charAt(a);
					}
				return list;
		}
		return list;
	}
	
	
	public static byte[] combine(byte[] one, byte[] two) {
		byte[] combined = new byte[one.length + two.length];

		for (int i = 0; i < combined.length; ++i)
			combined[i] = i < one.length ? one[i] : two[i - one.length];
		return combined;
	}
	public static byte[] substring(byte[] list, int start) {
		byte[] re = new byte[list.length - start];
		for(int z=0;start<list.length;start++)
			re[z++] = list[start];
		return re;
	}
}
