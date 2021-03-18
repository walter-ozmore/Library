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

public class EncryptFile {
	static int key;
	
	public static void createKey() {
		for(int z=0;z<5;z++)
			key += (int)(Math.random()*10) * Math.pow(10, z);
		System.out.println( key );
	}
	
	public static boolean hasTrailingZero(File file) throws IOException {
		try {
			byte[] fileData = Files.readAllBytes( Paths.get(file.getPath()) );
			
			//Convert file data to int array to encode
			if(fileData[fileData.length-1] == 0) {
				//remove trailing zeros
				while(fileData[fileData.length-1] == 0) {
					byte[] fileDataNew = new byte[fileData.length-2];
					for(int z=0;z<fileDataNew.length;z++)
						fileDataNew[z] = fileData[z];
					fileData = fileDataNew;
				}
				try (FileOutputStream fos = new FileOutputStream( "FILE.FILE"  )) {
					fos.write(fileData);
					fos.close();
				}
				System.exit(0);
			}
		}catch(Exception e) { System.out.println("FAIL"); }
		return false;
	}
	
	public static int[] codeAlgorithm(int[] data, boolean flag) {
		long startTime = System.nanoTime();
		// flag = true; Encrypt | flag = false; Decrypt
		int gridSize = 10, loc = 0, writeLoc = 0;
		while(data.length - loc > Math.pow(gridSize, 2) ) {
			int[][] grid = new int[gridSize][gridSize];
			
			for(int x=0;x<gridSize;x++)
				for(int y=0;y<gridSize;y++) {
					grid[x][y] = data[loc++];
				}
			
			if(flag)
				for(int x=0;x<grid.length;x++)
					for(int y=0;y<grid[x].length;y++)
						grid[x][y] += x + y;
			
			//Shift all top row right
			if(flag)
				grid = MatrixManipulation.shiftRowRight(grid, 0);
			else
				grid = MatrixManipulation.shiftRowLeft(grid, 0);
			
			if(!flag)
				for(int x=0;x<grid.length;x++)
					for(int y=0;y<grid[x].length;y++)
						grid[x][y] -= x + y;
			
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
		String name = file.getName().substring(0, file.getName().indexOf("."));
		String outputLoc = file.getParentFile() + "/" + name + ".zen";
		encryptFile(file, new File(outputLoc));
	}
	public static void encryptFile(File file, File writingFile) throws IOException {
		System.out.println("Encripting " + file.getName());
		byte[] fileData = Files.readAllBytes( Paths.get(file.getPath()) );
		
		//Convert file data to int array to encode
		int[] data = new int[fileData.length];
		for(int z=0;z<fileData.length;z++)
			data[z] = fileData[z];
		
		data = codeAlgorithm(data, true);
		
		//Write To file
		writingFile.createNewFile();
		FileWriter myWriter = new FileWriter( writingFile );
		myWriter.write( file.getName() +"\n" + data.length + "\n" );
		
		for(int z=0;z<data.length;z++)
			myWriter.write( data[z] + " " );
		
		myWriter.close();
	}
	
	
	public static void decryptFile(File file) throws IOException { decryptFile(file, (file.getParent() + "\\DECRYPT ") ); }
	public static void decryptFile(File file, String outputLocation) throws IOException {
		System.out.println("Decripting " + file.getName());
		Scanner scnr = new Scanner( file );
		String name = scnr.nextLine();
		int size = scnr.nextInt();
		
		int[] enFileData = new int[size];
		int z=0;
		while (scnr.hasNextInt()) {
			enFileData[z++] = scnr.nextInt();
		}
		
		enFileData = codeAlgorithm(enFileData, false);
		
		//Convert file data to byte array to create file
		byte[] data = new byte[enFileData.length];
		for(z=0;z<enFileData.length;z++)
			data[z] = (byte) (enFileData[z]);
		
		try (FileOutputStream fos = new FileOutputStream( outputLocation + name  )) {
			fos.write(data);
			fos.close();
		}
		scnr.close();
	}
}
