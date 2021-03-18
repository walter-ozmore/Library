package Encription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import Misc.MatrixManipulation;
import Misc.TimeConverter;

public class EncryptFile2 {
	static int key;
	
	public static void createKey() {
		for(int z=0;z<5;z++)
			key += (int)(Math.random()*10) * Math.pow(10, z);
		System.out.println( key );
	}
	
	public static byte[] codeAlgorithm(byte[] data, boolean flag) {
		long startTime = System.nanoTime();
		// flag = true; Encrypt | flag = false; Decrypt
		int gridSize = 10, loc = 0, writeLoc = 0;
		while(data.length - loc > Math.pow(gridSize, 2) ) {
			byte[][] grid = new byte[gridSize][gridSize];
			
			for(int x=0;x<gridSize;x++)
				for(int y=0;y<gridSize;y++) {
					grid[x][y] = data[loc++];
				}
			
			//Shift all top row right
			if(flag)
				grid = MatrixManipulation.shiftRowRight(grid, 0);
			else
				grid = MatrixManipulation.shiftRowLeft(grid, 0);
			
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
		decryptFile(file, (file.getParent() + "\\DECRYPT ") );
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
