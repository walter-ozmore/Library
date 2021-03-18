package Encription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import Misc.TimeConverter;

public class EncryptFile3 {
	public static int key = 0;
	
	public static byte[] codeAlgorithm(byte[] data, boolean flag) {
		long startTime = System.nanoTime();
		// flag = true; Encrypt | flag = false; Decrypt
		int gridSize = 10, loc = 0, writeLoc = 0;
		
		int number = key;
		ArrayList<Integer> key = new ArrayList<Integer>();
		while (number > 0) {
			int remainder = number % 10;
			key.add(remainder);
			number = number / 10;
		}
		
		while(data.length - loc > Math.pow(gridSize, 2) ) {
			byte[][] grid = new byte[gridSize][gridSize];
			
			for(int x=0;x<gridSize;x++)
				for(int y=0;y<gridSize;y++) {
					grid[x][y] = data[loc++];
				}
			
			//System.out.print("("+value1+","+value2+") ");
			//Shift all top row right
			grid = MatrixManipulation.swapMatrix(grid, value1+1, value2+1);
			//System.out.println();
			
			//Output Grid
			for(int x=0;x<gridSize;x++)
				for(int y=0;y<gridSize;y++) {
					data[writeLoc++] = grid[x][y];
				}
		}
		long endTime = System.nanoTime();
		System.out.println( 
				"Time taken:" + TimeConverter.nanoToMillisecond(endTime-startTime) + 
				" Time per byte:" + ((double)(endTime-startTime)/data.length)
			);
		return data;
	}
	
	public static void encryptFile(File file) throws IOException {
		String outputLoc = file.getParentFile() + "/" + file.getName() + ".zen";
		encryptFile(file, new File(outputLoc));
	}
	public static void encryptFile(File file, File writingFile) throws IOException {
		System.out.println("Encripting " + file.getName());
		byte[] fileData = Files.readAllBytes( Paths.get(file.getPath()) );
		
		fileData = codeAlgorithm(fileData, true);
		
		//Write To file
		try (FileOutputStream fos = new FileOutputStream( writingFile.getAbsolutePath() )) {
			fos.write(fileData);
			fos.close();
		}
	}
	
	
	public static void decryptFile(File file) throws IOException { 
		decryptFile(file, (file.getParent() + "\\DECRYPT") );
	}
	public static void decryptFile(File file, String outputLocation) throws IOException {
		System.out.println("Decripting " + file.getName());
		Scanner scnr = new Scanner( file );
		
		byte[] fileData = Files.readAllBytes( Paths.get(file.getPath()) );
		
		fileData = codeAlgorithm(fileData, false);
		
		try (FileOutputStream fos = new FileOutputStream( outputLocation + file.getName().substring(0, file.getName().lastIndexOf('.')) )) {
			fos.write(fileData);
			fos.close();
		}
		scnr.close();
	}
}
