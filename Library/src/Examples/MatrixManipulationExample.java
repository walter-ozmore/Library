package Examples;

import static Misc.SDL.*;
import static Misc.MatrixManipulation2.*;

public class MatrixManipulationExample {
	public static void main(String args[]) {
		double[][] matrix = {
				{-4,  7, -2, 2},
				{ 1, -2, 1, 3},
				{-3, 5, -1, -4}
		};
		
		addMatrix(matrix, 1, 2, 4);
		swap(matrix, 1, 2);
		multMatrix(matrix, 2, -1);
		addMatrix(matrix, 3, 1, 3);
		addMatrix(matrix, 3, 2, 1);
		
		printMatrix( matrix );
	}
}
