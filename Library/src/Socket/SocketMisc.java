package Socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import Misc.ConsoleColors;
import Misc.SDL;
import Misc.ServerMisc;

public class SocketMisc {
	// Set variables
	static int bufSize = 8192;
	public static final int lenOfControl = 1;
	public static final byte[] exit = intToBytes(0);
	public static final byte[] file = intToBytes(2);
	
	/**
	 * @parm OutputStream out
	 * @parm File file:  The file or folder that will be sent
	 * @parm String str: The destination of the file on the recipients device
	 */
	public static void sendFile(InputStream in, OutputStream out, File file, String str) throws IOException {
		// Wait for client ready
		int z = in.read();
		
		out.write( 2 ); // Alert recipient that a file is being sent
		System.out.println("Sending " + file.getAbsolutePath());
		
		out.write( new byte[]{(byte)((file.isFile())?1:0)} );// Send control byte to indicate folder/file
		
		// Send name
		String name = str + file.getName();
//		out.write( stringToBytes(name) );
		ServerMisc.sendString(out, name);
		
		/* Send file */
		if(file.isDirectory()) {
			if(file.listFiles() != null) {
				for(File f:file.listFiles()) {
					String s = str+"\\"+file.getName()+"\\";
					sendFile(in, out, f, s);
				}
			}
			return;
		}
		
		InputStream fileInput = new FileInputStream( file );
		out.write( longToBytes(file.length()) ); // Send length of file
		copy(fileInput, out); // Copy file from output stream
		fileInput.close(); // Close file stream
	}
	
	public static File getFile(InputStream in) throws IOException {
		// Get control byte
		boolean isFile = (in.read() == 1)? true: false;
		
		// Get name of file
		String name = ServerMisc.getString(in);
		
		File file = new File( name );
		System.out.println( ConsoleColors.GREEN+file.getAbsolutePath()+ConsoleColors.RESET );
		if(!isFile) {
			file.mkdirs();
			return file;
		}
		
		long fileLength = getLong( in );
		
		OutputStream out = new FileOutputStream( file.getAbsolutePath() );
		copy(in, out, fileLength);
		out.close();
		return file;
	}
	
	static void copy(InputStream in, OutputStream out) throws IOException { copy(in, out, -1); }
	static void copy(InputStream in, OutputStream out, long size) throws IOException {
		byte[] buf = new byte[bufSize];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
			size -= len;
			if(size != -1 && len < size) {
				bufSize = (int)size;
				buf = new byte[bufSize];
			}
			if(size == 0) break;
		}
	}
	
	static int getInt(InputStream in) throws IOException {
		byte[] data = new byte[4];
		in.read( data );
		return bytesToInt( data );
	}
	
	static long getLong(InputStream in) throws IOException {
		byte[] data = new byte[8];
		in.read( data );
		return bytesToLong( data );
	}
	
	static byte[] intToBytes(int var) { return ByteBuffer.allocate(4).putInt(var).array(); }
	static int bytesToInt(byte[] bytes) { return ByteBuffer.wrap(bytes).getInt(); }
	
	static byte[] longToBytes(long var) { return ByteBuffer.allocate(8).putLong(var).array(); }
	static long bytesToLong(byte[] bytes) { return ByteBuffer.wrap(bytes).getLong(); }
	
	static byte[] stringToBytes(String var) {
		byte[] bytes = var.getBytes(); // Convert string in to byte values
		byte[] len   = intToBytes( bytes.length ); // Get length of string byte values for the recipient to read
		
		// Create return bytes
		byte[] re = new byte[ bytes.length + len.length ];
		int pos = 0;
		for(int z=0;z<bytes.length;z++) re[pos++] = bytes[z];
		for(int z=0;z<len.length  ;z++) re[pos++] = len[z];
		return re;
	}
	static String byteToString(byte[] data) {
		return "EMPTY FUNCTION";
	}
}
