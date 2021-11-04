package Examples;

import static Misc.SDL.*;
import static Misc.MatrixManipulation2.*;

public class MatrixManipulationExample {
	public static void main(String args[]) {
		double[][] matrix = {
				{-2,-8, 6},
				{ 8,-5, 8},
				{ 6, 9,-4}
		};
		
		println( detGen(matrix) );
		
		printMatrix( matrix );
	}
}
