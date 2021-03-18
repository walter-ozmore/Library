package Encription;

public class MatrixManipulation {
	public static int[][] shiftHorizontal(int [][] array, int row, int shiftAmount) {
		if(shiftAmount>0) for(int z=0;z<shiftAmount;z++) array = shiftRowRight(array, row);
		if(shiftAmount<0) for(int z=0;z<Math.abs(shiftAmount);z++) array = shiftRowRight(array, row);
		return array;
	}
	
	public static byte[][] shiftRowRight(byte[][] array, int y) {
		byte[] newArray = new byte[array.length];
		byte stored = array[y][array.length-1];
		for(int z=1;z<newArray.length;z++)
			newArray[z] = array[y][z-1];
		newArray[0] = stored;
		array[y] = newArray;
		return array;
	}
	public static byte[][] shiftRowLeft(byte[][] array, int y) {
		byte[] newArray = new byte[array.length];
		byte stored = array[y][0];
		for(int z=newArray.length-2;z>=0;z--)
			newArray[z] = array[y][z+1];
		newArray[newArray.length-1] = stored;
		array[y] = newArray;
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
	
	
	
	//Array
	
	public static void productOfMatrixAndArray(int[][] matrix, int[] array) {
		int[][] sumMatrix = new int[array.length][matrix.length];
		
		for(int x=0;x<array.length;x++) {
			int[] tempArray = new int[matrix.length];
			for(int z=0;z<matrix.length;z++)
				tempArray[z] = matrix[z][x];
			sumMatrix[x] = productOfArray(tempArray, array[x]);
		}
		
		Integer[] sumArray = new Integer[matrix.length];
		for(int x=0;x<sumMatrix.length;x++)
			for(int y=0;y<sumMatrix[x].length;y++)
				sumArray[y] += sumMatrix[x][y];
		printArray(sumArray);
	}
	
	//Printing
	public static <E> String printArray(E[] array) {
		String re = "[";
		for(int x=0;x<array.length;x++) {
			re += array[x];
			if(x<array.length-1)
				re += ",";
		}
		re += "]\n";
		return re;
	}
	public static String printArray(int[] array) {
		String re = "[";
		for(int x=0;x<array.length;x++) {
			re += array[x];
			if(x<array.length-1)
				re += ",";
		}
		re += "]\n";
		return re;
	}
	public static String printArray(double[] array) {
		String re = "[";
		for(int x=0;x<array.length;x++) {
			if ((array[x] == Math.floor(array[x])) && !Double.isInfinite(array[x]))
				re += (int)array[x];
			else
				re += convertDecimalToFraction(array[x]);
			if(x<array.length-1)
				re += ", ";
		}
		re += "]\n";
		return re;
	}
	public static String printMatrix(Integer[][] grid) {
		String re = "";
		for(int x=0;x<grid.length;x++) {
			re += printArray(grid[x]);
		}
		return re+"\n";
	}
	public static String printMatrix(int[][] grid) {
		String re = "";
		for(int x=0;x<grid.length;x++) {
			re += printArray(grid[x]);
		}
		return re+"\n";
	}
	public static String printMatrix(double[][] grid) {
		String re = "";
		for(int x=0;x<grid.length;x++) {
			re += printArray(grid[x]);
		}
		return re+"\n";
	}
	//NO CLUE
	public static double[] subArray(double[] a, double[] b) {
		if(a.length != b.length) {
			System.out.println("NOT THE SAME LENGTH");
			System.exit(0);
		}
		double[] re = new double[a.length];
		for(int z=0;z<a.length;z++)
			re[z] = a[z]-b[z];
		return re;
	}
	public static double[] addArray(double[] a, double[] b) {
		if(a.length != b.length) {
			System.out.println("NOT THE SAME LENGTH");
			System.exit(0);
		}
		double[] re = new double[a.length];
		for(int z=0;z<a.length;z++)
			re[z] = a[z]+b[z];
		return re;
	}
	public static double[] multArray(double[] a, double mult) {
		double[] re = new double[a.length];
		for(int z=0;z<a.length;z++)
			re[z] = a[z] * mult;
		return re;
	}
	public static double[][] addMatrix(double[][] matrix, int row, int row2, double mult) {
		System.out.println("R"+row+" = R"+row+" + "+mult+"*R"+row2);
		matrix[row-1] = addArray(matrix[row-1], multArray(matrix[row2-1], mult));
		return matrix;
	}
	public static double[][] subMatrix(double[][] matrix, int row, int row2, double mult) {
		System.out.println("R"+row+" = R"+row+" - "+mult+"*R"+row2);
		matrix[row-1] = subArray(matrix[row-1], multArray(matrix[row2-1], mult));
		return matrix;
	}
	public static double[][] multMatrix(double[][] matrix, int row, double mult) {
		System.out.println("R"+row+" = "+mult+"*R"+row);
		matrix[row-1] = multArray(matrix[row-1], mult);
		return matrix;
	}
	//Swapping
	public static <E> E[][] swapMatrix(E[][] matrix, int row, int row2) {
		E[] hold = matrix[row-1];
		matrix[row-1] = matrix[row2-1];
		matrix[row2-1] = hold;
		return matrix;
	}
	public static byte[][] swapMatrix(byte[][] matrix, int row, int row2) {
		byte[] hold = matrix[row-1];
		matrix[row-1] = matrix[row2-1];
		matrix[row2-1] = hold;
		return matrix;
	}
	public static double[][] swapMatrix(double[][] matrix, int row, int row2) {
		double[] hold = matrix[row-1];
		matrix[row-1] = matrix[row2-1];
		matrix[row2-1] = hold;
		return matrix;
	}
	
	static private String convertDecimalToFraction(double x){
		if (x < 0)
			return "-" + convertDecimalToFraction(-x);
		double tolerance = 1.0E-6;
		double h1=1; double h2=0;
		double k1=0; double k2=1;
		double b = x;
		do {
			double a = Math.floor(b);
			double aux = h1; h1 = a*h1+h2; h2 = aux;
			aux = k1; k1 = a*k1+k2; k2 = aux;
			b = 1/(b-a);
		} while (Math.abs(x-h1/k1) > x*tolerance);
		return (int)h1+"/"+(int)k1;
	}
	//Determinant
	public static int detGen(int[][] matrix) {
		return detGenRe(matrix, true);
	}
	public static int detGenRe(int[][] matrix, boolean pos) {
		//System.out.println("PASS "+pos+"\n" + printMatrix(matrix).strip());
		//Check if square
		if(matrix.length != matrix[0].length) {
			throw new java.lang.RuntimeException("Matrix is not square");
		}
		//Run
		int determinant = 0;
		int size = matrix.length;
		
		for(int z=0;z<matrix.length;z++) {
			//Stores the current location being edited on newMat
			int loc = 0;
			int slot = matrix[0][z];
			int[][] newMat = new int[size-1][size-1];
			
			for(int x=1;x<matrix.length;x++) {
				for(int y=0;y<matrix[x].length;y++) {
					if(y!=z) {
						newMat[loc/(size-1)][loc%(size-1)] = matrix[x][y];
						loc++;
					}
				}
			}
			int p = 0;
			if( pos) p = 1;
			if(!pos) p = -1;
			
			if(newMat.length == 2) determinant += p * slot * (newMat[0][0] * newMat[1][1] - newMat[1][0] * newMat[0][1]);
			else determinant += p * slot * detGenRe(newMat, true);
			
			pos = !pos;
		}
		
		return determinant;
	}
}
