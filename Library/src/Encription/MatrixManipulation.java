package Encription;

public class MatrixManipulation {
	public static int[][] shiftHorizontal(int [][] array, int row, int shiftAmount) {
		if(shiftAmount>0) for(int z=0;z<shiftAmount;z++) array = shiftRowRight(array, row);
		if(shiftAmount<0) for(int z=0;z<Math.abs(shiftAmount);z++) array = shiftRowRight(array, row);
		return array;
	}
	public static int[][] shiftRowRight(int[][] array, int y) {
		int[] newArray = new int[array.length];
		int stored = array[y][array.length-1];
		for(int z=1;z<newArray.length;z++)
			newArray[z] = array[y][z-1];
		newArray[0] = stored;
		array[y] = newArray;
		return array;
	}
	public static int[][] shiftRowLeft(int[][] array, int y) {
		int[] newArray = new int[array.length];
		int stored = array[y][0];
		for(int z=newArray.length-2;z>=0;z--)
			newArray[z] = array[y][z+1];
		newArray[newArray.length-1] = stored;
		array[y] = newArray;
		return array;
	}
	public static int[][] shiftRowUp(int[][] array, int y) { return null; }
	public static int[][] shiftRowDown(int[][] array, int y) { return null; }
	public static int[][] multiply(int[][] array, int mult) {
		for(int x=0;x<array.length;x++) {
			for(int y=0;y<array[x].length;y++) {
				array[x][y] *= mult;
			}
		}
		return array;
	}
	//Sums of Matrixs
	public static int sumOfRow(int[][] array, int row) {
		int sum = 0;
		for(int x=0;x<array.length;x++)
			sum += array[x][row];
		return sum;
	}
	public static int sumOfColumn(int[][] array, int column) {
		int sum = 0;
		for(int x=0;x<array[column].length;x++)
			sum += array[column][x];
		return sum;
	}
	
	public static int[] productOfArray(int[] array, int mult) {
		for(int z=0;z<array.length;z++)
			array[z] = array[z] * mult;
		return array;
	}
	public static int[] sumOfArrays(int[] arrayA, int[] arrayB) {
		int[] arraySum = new int[Math.max(arrayA.length, arrayB.length)];
		for(int z=0;z<arrayA.length;z++)
			arraySum[z] = arrayA[z] + arrayB[z];
		return arraySum;
	}
	
	public static void printMatrix(int[][] grid) {
		for(int x=0;x<grid.length;x++) {
			printArray(grid[x]);
		}
		System.out.println();
	}
	
	//Array
	
	public static void productOfMatrixAndArray(int[][] matrix, int[] array) {
		int[][] sumMatrix = new int[array.length][matrix.length];
		
		for(int x=0;x<array.length;x++) {
			int[] tempArray = new int[matrix.length];
			for(int z=0;z<matrix.length;z++)
				tempArray[z] = matrix[z][x];
			sumMatrix[x] = productOfArray(tempArray, array[x]);
		}
		
		int[] sumArray = new int[matrix.length];
		for(int x=0;x<sumMatrix.length;x++)
			for(int y=0;y<sumMatrix[x].length;y++)
				sumArray[y] += sumMatrix[x][y];
		printArray(sumArray);
	}
	
	public static void printArray(int[] array) {
		System.out.print("[");
		for(int x=0;x<array.length;x++) {
			System.out.print( array[x] );
			if(x<array.length-1)
				System.out.print(",");
		}
		System.out.println("]");
	}
}
