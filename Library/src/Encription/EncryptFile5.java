package Encription;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import Converter.TimeConverter;

public class EncryptFile5 {
	public static void codeAlgorithm(File file, boolean flag) {
		codeAlgorithm(file, flag);
	}
	public static void codeAlgorithm(File file, boolean flag, String key, int bit) {
		File outputFile = new File(bit+file.getName());
		long startTime = System.nanoTime();
		
		FileInputStream fileInputStream = null;
		byte[] buffer;
		int bufferSize = 100;
		
		try (FileOutputStream fos = new FileOutputStream( outputFile )) {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			long sizeLeft = file.length();
			while(sizeLeft > 0) {
				//When buffer is less than sizeLeft, buffer = sizeLeft
				if(sizeLeft < bufferSize) bufferSize = (int)sizeLeft;
				buffer = new byte[bufferSize];
				sizeLeft -= bufferSize;
				
				//Reads buffer
				fileInputStream.read(buffer);
				
				//Do stuff here
//				for (int i = 0; i < buffer.length; i++) System.out.print( buffer[i] + " " );
				for(int z=0;z < key.length();z++) {
					switch(key.charAt(z)) {
						case 'A':swap(buffer,0,buffer.length-1);break;
						case 'B':slide(buffer);break;
					}
				}
				
				fos.write(buffer);
			}
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long endTime = System.nanoTime();
		System.out.println( 
				"Time taken:" + TimeConverter.nanoToAutomatic(endTime-startTime) + 
				" Time per byte:" + ((double)(endTime-startTime)/file.length())
			);
		
		Scanner scr;
		try {
			scr = new Scanner(outputFile);
			while(scr.hasNextLine())
				System.out.println(scr.nextLine());
			scr.close();
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		
		bit -=1 ;
		if(bit>0) {
			codeAlgorithm(outputFile, flag, key, bit);
			outputFile.delete();
		}
	}
	public static void slide(byte[] list) {
		byte stored = list[0];
		for(int z=1;z<list.length;z++)
			list[z-1] = list[z];
		list[list.length-1] = stored;
	}
	public static void swap(byte[] list, int v1, int v2) {
		byte stored = list[v1];
		list[v1] = list[v2];
		list[v2] = stored;
	}
	
}
