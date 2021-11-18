package Encription;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import Misc.SDL;

public class EncryptFileTester {
	public static String stopValue = "THIS WILL (PROBABLY) NEVER SHOW UP BUT JUST INCASE slakjsd gnodpigja wngpa8929p*(#()$";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		char[] list = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' '};
		String key = "Test string that require nothing other than a fuckton of stuff in it";
		FileWriter w = new FileWriter("filename.txt");
//		String key = "";
		for(int z=0;z<key.length();z++) {
			for(int a=0;a<list.length;a++) {
				String s = key.substring(0,z)+list[a]+key.substring(z+1);
				if(s.equals(key)) continue;
				w.write(s+"\n");
				w.write( decrypt(s) + "\n\n");
			}
		}
		w.close();
	}
	public static String decrypt(String key) throws IOException {
		File sourceFile = new File("C:\\Users\\souls\\Downloads\\abc.txt");
		File outputFile = new File("C:\\Users\\souls\\OneDrive\\Desktop\\WorkingFiles");
		
		byte[] list;
//		list = combine( (sourceFile.getName()+stopValue).getBytes(), Files.readAllBytes(sourceFile.toPath()));
//		list = Encrypt.encrypt(list, key);
//		Files.write(Paths.get(outputFile.getAbsolutePath()+"\\"+sourceFile.getName().substring(0,sourceFile.getName().lastIndexOf("."))+".zen"), list);
		list = Encrypt.decrypt(Files.readAllBytes(Paths.get(outputFile.getAbsolutePath()+"\\"+sourceFile.getName().substring(0,sourceFile.getName().lastIndexOf("."))+".zen")), key);
		String s = new String(list);
		String name = "missingName.txt";
		if(s.contains(stopValue))
			name = s.substring(0,s.indexOf(stopValue));
		list = substring(list, (name + stopValue).getBytes().length);
		return new String(list);
	}
	public static void test2() throws IOException {
		String key = "Test string that require nothing other than a fuckton of stuff in it";
		File sourceFile = new File("C:\\Users\\souls\\Downloads\\abc.txt");
		File outputFile = new File("C:\\Users\\souls\\OneDrive\\Desktop\\WorkingFiles");
		
		byte[] list;
//		list = combine( (sourceFile.getName()+stopValue).getBytes(), Files.readAllBytes(sourceFile.toPath()));
//		list = Encrypt.encrypt(list, key);
//		Files.write(Paths.get(outputFile.getAbsolutePath()+"\\"+sourceFile.getName().substring(0,sourceFile.getName().lastIndexOf("."))+".zen"), list);
		list = Encrypt.decrypt(Files.readAllBytes(Paths.get(outputFile.getAbsolutePath()+"\\"+sourceFile.getName().substring(0,sourceFile.getName().lastIndexOf("."))+".zen")), key);
		String s = new String(list);
		String name = "missingName.txt";
		if(s.contains(stopValue))
			name = s.substring(0,s.indexOf(stopValue));
		list = substring(list, (name + stopValue).getBytes().length);
		
		Files.write(Paths.get(outputFile.getAbsolutePath()+"\\"+name), list);
	}
	public static void test() throws IOException {
		String key = "Test string that require nothing other than a fuckton of stuff in it";
		byte[] list;
		String workingLocation = "C:\\Users\\souls\\OneDrive\\Desktop\\WorkingFiles\\";
		String sourceFileName = "WireShark.txt";
		
		File sourceFile = new File(workingLocation+sourceFileName);
		list = combine( (sourceFile.getName()+stopValue).getBytes(), Files.readAllBytes(sourceFile.toPath()));
		list = Encrypt.encrypt(list, key);
		Files.write(Paths.get(workingLocation+"output.zen"), list);
		list = Encrypt.decrypt(list, key);
		String s = new String(list);
		String name = s.substring(0,s.indexOf(stopValue));
		list = substring(list, (name + stopValue).getBytes().length);
		
		Files.write(Paths.get(workingLocation+"OUTPUT"+name), list);
		
	}
	public static byte[] combine(byte[] one, byte[] two) {
		byte[] combined = new byte[one.length + two.length];

		for (int i = 0; i < combined.length; ++i)
			combined[i] = i < one.length ? one[i] : two[i - one.length];
		return combined;
	}
	public static byte[] substring(byte[] list, int start) {
		byte[] re = new byte[list.length - start];
		for(int z=0;start<list.length;start++)
			re[z++] = list[start];
		return re;
	}
	public static void println(byte list[]) {
		String s = "[";
		for(byte b:list)
			s += b + ",";
		System.out.println(s.substring(0, s.length()-1)+"]");
	}
}
