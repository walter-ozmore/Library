package Encription;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class EncriptFile {
	
	public static int getNumber() {
		return 5;
	}
	
	public static void encriptFile(File file) throws IOException {
		String name = file.getName().substring(0, file.getName().indexOf("."));
		String outputLoc = file.getParentFile() + "/" + name + ".zen";
		encriptFile(file, new File(outputLoc));
	}
	public static void encriptFile(File file, File writingFile) throws IOException {
		System.out.println("Encripting " + file.getName());
		byte[] fileData = Files.readAllBytes( Paths.get(file.getPath()) );
		writingFile.createNewFile();
		FileWriter myWriter = new FileWriter( writingFile );
		
		myWriter.write( file.getName() +"\n" + fileData.length + "\n" );
		
		for(int z=0;z<fileData.length;z++)
			myWriter.write( (fileData[z] + getNumber()) + " " );
		
		myWriter.close();
	}
	
	
	public static void decriptFile(File file) throws IOException { decriptFile(file, (file.getParent() + "\\DECRYPT ") ); }
	public static void decriptFile(File file, String outputLocation) throws IOException {
		System.out.println("Decripting " + file.getName());
		Scanner scnr = new Scanner( file );
		String name = scnr.nextLine();
		int size = scnr.nextInt();
		
		byte[] enFileData = new byte[size];
		int z=0;
		while (scnr.hasNextInt()) {
			enFileData[z++] = (byte) ( scnr.nextInt() - getNumber() );
		}
		
		try (FileOutputStream fos = new FileOutputStream( outputLocation + name  )) {
			fos.write(enFileData);
			fos.close();
		}
		scnr.close();
	}
}
