package Misc;

import java.util.Scanner;

public class MatrixManipulation {
	static Scanner userInput = null;
	public static boolean printSteps = true;
	
	/* setUserInput(Scanner scanner);  && setUserInput();
	 * This method is used to set the userInput scanner while also adding a shutDownHook to automatically 
	 * close userInput on the programs exit. userInput is necessary because closing and opening a System.in
	 * scanner multiply times causes problems, mostly failing to wait for input
	 */
	public static void setUserInput() {
		userInput = new Scanner(System.in);
		shutDownHook();
	}
	public static void setUserInput(Scanner scanner) {
		userInput = scanner;
		if(userInput != null) shutDownHook();
	}
	public static void shutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				if(userInput != null) userInput.close();
			}
		}, "Shutdown-thread"));
	}
	
	//Shifting matrixes
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
		//printArray(sumArray);
	}
	//Printing
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
	public static void printMatrix(double[][] grid) {
		for(String s:printStringMatrix(grid))
			System.out.println(s);
	}
	public static String[] printStringMatrix(double[][] grid) {
		String[] out = new String[grid.length];
		for(int z=0;z<out.length;z++) out[z] = "[";
		
		for(int row = 0;row<grid[0].length;row++) {
			//Space numbers out equally with number above & below them
			int largest = 0;
			for(int x=0;x<grid.length;x++) {
				if( convertDecimalToFraction(grid[x][row]).length() > largest)
					largest = convertDecimalToFraction(grid[x][row]).length();
			}
			for(int x=0;x<grid.length;x++) {
				String s = ""+convertDecimalToFraction(grid[x][row]);
				while(s.length()<largest) s = " "+s;
				out[x] += s;
				if(row<grid[0].length-1) out[x] += ", ";
				if(row==grid[0].length-1) out[x] += "]";
			}
		}
		
		return out;
	}
	public static void printMatrixMod(double[][] matrix, String str) {
		String[] out = printStringMatrix(matrix);
		out[0] = str + out[0];
		for(int x=1;x<out.length;x++) {
			String store = out[x];
			out[x] = "";
			for(int y=0;y<str.length();y++) out[x] += " ";
			out[x] += store;
		}
		for(String s:out) System.out.println(s);
	}
	//Matrix
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
		String o = "R"+row+" = R"+row+" + "+convertDecimalToFraction(mult)+"*R"+row2+" ";
		matrix[row-1] = addArray(matrix[row-1], multArray(matrix[row2-1], mult));
		if(printSteps) printMatrixMod(matrix, o);
		return matrix;
	}
	public static double[][] multMatrix(double[][] matrix, int row, double mult) {
		matrix[row-1] = multArray(matrix[row-1], mult);
		
		String o = "R"+row+" = "+convertDecimalToFraction(mult)+" * R"+row+" ";
		if(printSteps) printMatrixMod(matrix, o);
		
		return matrix;
	}
	public static double[][] add(double[][] a, double[][] b){
		double[][] z = new double[Math.max(a.length, b.length)][Math.max(a[0].length, b[0].length)];
		for(int x=0;x<Math.max(a[0].length, b[0].length);x++)
			for(int y=0;y<Math.max(a.length, b.length);y++) {
				z[x][y] = a[x][y] + b[x][y];
			}
		return z;
	}
	public static double[][] transpose(double[][] t){
		double[][] z = new double[t[0].length][t.length];
		for(int x=0;x<t[0].length;x++)
			for(int y=0;y<t.length;y++)
				z[x][y] = t[y][x];
		return z;
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
	//Converts decimals to more readable fraction, or an integer. Returns a String
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
		if((int)k1==1)
			return (int)h1+"";
		return (int)h1+"/"+(int)k1;
	}
	//Get user input
	public static double[][] getUserInputMatrix() {
		System.out.print("Enter the size of the matrix: ");
		int sizeX = -1, sizeY = -1;
		do {
			String line = userInput.nextLine(); //Get users line
			Scanner subScanner = new Scanner(line);
			try {
				sizeX = subScanner.nextInt();
				sizeY = subScanner.nextInt();
			}catch(Exception e) {
				System.out.println("Please enter a valid size.");
				sizeX = -1;
				sizeY = -1;
			}
			subScanner.close();
		}while(sizeX == -1 | sizeY == -1);
		return getUserInputMatrix(sizeX, sizeY);
	}
	public static double[][] getUserInputMatrix(int sizeX, int sizeY) {
		System.out.println("Input a "+sizeX+"x"+sizeY+" matrix:");
		
		double[][] matrix = new double[sizeX][sizeY];
		
		for(int y = 0;y<sizeY;y++) {
			System.out.print("Row "+(y+1)+": ");
			String line;
			if(userInput.hasNextLine())
				line = userInput.nextLine(); //Get users line
			else {
				y--;
				System.out.println("SDF");
				continue;
			}
			Scanner subScanner = new Scanner(line);
			for(int x = 0;x<sizeX;x++) {
				if(subScanner.hasNextDouble())
					matrix[y][x] = subScanner.nextDouble();
				else {
					System.out.println("Missing number for row "+(y--+1));
				}
			}
			subScanner.close();
		}
		return matrix;
	}
	//Determinant
	public static double detGen(double[][] matrix) {
		return detGenRe(matrix, true);
	}
	public static double detGenRe(double[][] matrix, boolean pos) {
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
			double slot = matrix[0][z];
			double[][] newMat = new double[size-1][size-1];
			
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
