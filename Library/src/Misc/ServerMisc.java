package Misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Converter.BaseConverter;

public class ServerMisc {
	
	public static String getString(InputStream in) throws IOException {
		byte[] temp = new byte[8];
		
		//getName Length
		temp = new byte[8];
		in.read(temp);
		int strLength = (int) BaseConverter.byteArrayToLong(temp);
		
		byte[] aStr = new byte[ strLength ];
		in.read( aStr );
		String str = "";
		for(int z=0;z<aStr.length;z++) str += (char)aStr[z];
		
		return str;
	}
	public static void sendString(OutputStream out, String str) throws IOException {
		
			byte[] aName = new byte[str.length()];
			for(int z=0;z<str.length();z++) 
				aName[z] = (byte) str.charAt(z);
			out.write( BaseConverter.longToByteArray( aName.length ) );
			out.write( aName );
	}
	//Files
	public static void sendFile(OutputStream out, File file, String str) throws IOException {
		//*
		InputStream in = new FileInputStream( file );
		String name = str + file.getName();
		byte[] aName = new byte[name.length()];
		for(int z=0;z<name.length();z++) aName[z] = (byte) name.charAt(z);
		
		
		//Send filename / path length
		out.write( BaseConverter.longToByteArray( aName.length ) );
		//Send filename / path
		out.write( aName );
		//*/
		//Send file length
		out.write( BaseConverter.longToByteArray(file.length()) );
			
		copy(new FileInputStream( file ),out);
		in.close();
	}
	static void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buf = new byte[8192];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
	}
	public static void getFile(InputStream in) throws IOException {
		byte[] temp = new byte[8];
		
		//getName Length
		temp = new byte[8];
		in.read(temp);
		int nameLength = (int) BaseConverter.byteArrayToLong(temp);
		
		//getName
		byte[] aName = new byte[ nameLength ];
		in.read( aName );
		String name = "";
		for(int z=0;z<aName.length;z++) name += (char)aName[z];
		
		//getFile Length
		temp = new byte[8];
		in.read(temp);
		long fileLength = BaseConverter.byteArrayToLong(temp);
		
		new File(name).getParentFile().mkdirs();
		copy(in, new FileOutputStream(name), fileLength );
	}
	static void copy(InputStream in, OutputStream out, long size) throws IOException {
		int bufSize = 8192;
		byte[] buf = new byte[bufSize];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
			size -= len;
			if(len < size) {
				bufSize = (int)size;
				buf = new byte[bufSize];
			}
			if(size == 0) break;
		}
	}
	
}
