package Misc;

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
	public static void sendString(OutputStream out, String str) {
		try {
			byte[] aName = new byte[str.length()];
			for(int z=0;z<str.length();z++) 
				aName[z] = (byte) str.charAt(z);
			out.write( BaseConverter.longToByteArray( aName.length ) );
			out.write( aName );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
