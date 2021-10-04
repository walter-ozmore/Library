package Misc;

import java.util.Scanner;
import static Misc.Mat.convertDecimalToFraction;

public class MatrixManipulation2 {
	
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
	
	public static double[][] shiftRowRight(double[][] array, int y) {
		double[] newArray = new double[array.length];
		double stored = array[y][array.length-1];
		for(int z=1;z<newArray.length;z++)
			newArray[z] = array[y][z-1];
		newArray[0] = stored;
		array[y] = newArray;
		return array;
	}
	public static double[][] shiftRowLeft(double[][] array, int y) {
		double[] newArray = new double[array.length];
		double stored = array[y][0];
		for(int z=newArray.length-2;z>=0;z--)
			newArray[z] = array[y][z+1];
		newArray[newArray.length-1] = stored;
		array[y] = newArray;
		return array;
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
	//Add row2 to row with the multiple of mult
	public static void addMatrix(double[][] matrix, int row, int row2) { addMatrix(matrix, row, row2, 1); }
	public static void addMatrix(double[][] matrix, int row, int row2, double mult) {
		String o = "R"+row+" = R"+row+" + "+convertDecimalToFraction(mult)+"*R"+row2+" ";
		matrix[row-1] = addArray(matrix[row-1], multArray(matrix[row2-1], mult));
		if(printSteps) printMatrixMod(matrix, o);
	}
	//Multiplies
	public static double[][] multMatrix(double[][] matrix, int row, double mult) {
		matrix[row-1] = multArray(matrix[row-1], mult);
		
		String o = "R"+row+" = "+convertDecimalToFraction(mult)+" * R"+row+" ";
		if(printSteps) printMatrixMod(matrix, o);
		
		return matrix;
	}
	public static void swap(double[][] matrix, int row1, int row2) {
		row1--; row2--;
		double[] stored = matrix[row2];
		matrix[row2] = matrix[row1];
		matrix[row1] = stored;
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
	//Printing junk
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
	public static void printMatrix(double[][] grid) {
		for(String s:printStringMatrix(grid))
			System.out.println(s);
	}
}
