package Misc;

public class ArrayUtils {
/*Changing the size of a list*/
	//String
	public static String[] expand(String[] list, int amount) { return setSize(list,amount+list.length);}
	public static String[] setSize(String[] list, int size) {
		String[] newList = new String[size];
		for(int x=0; x<Math.min(list.length,newList.length); x++)
			newList[x] = list[x];
		return newList;
	}
/*Easy List Printing*/
	public static void print(byte[] list   ) { for(byte item:list)    System.out.println(item); }
	public static void print(short[] list  ) { for(short item:list)   System.out.println(item); }
	public static void print(int[] list    ) { for(int item:list)     System.out.println(item); }
	public static void print(long[] list   ) { for(long item:list)    System.out.println(item); }
	public static void print(float[] list  ) { for(float item:list)   System.out.println(item); }
	public static void print(double[] list ) { for(double item:list)  System.out.println(item); }
	public static void print(boolean[] list) { for(boolean item:list) System.out.println(item); }
	public static void print(char[] list   ) { for(char item:list)    System.out.println(item); }
	public static void print(String[] list ) { for(String item:list)  System.out.println(item); }
}
