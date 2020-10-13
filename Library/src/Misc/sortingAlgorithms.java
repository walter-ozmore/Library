package Misc;

public class sortingAlgorithms {
	//BubbleSort
	public static void bubbleSort(int[] a) {
		boolean sorted = false;
		int temp;
		while(!sorted) {
			sorted = true;
			for (int i = 0; i < a.length - 1; i++)
				if (a[i] > a[i+1]) {
					temp = a[i];
					a[i] = a[i+1];
					a[i+1] = temp;
					sorted = false;
				}
		}
	}
	
	
}
