package FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil {
	public static boolean printDebug = true;
	/* delete(file)
	 * Will delete a file or folder given to the function as well as all sub dir
	 */
	public static void delete(File file){
		if(file.isDirectory()) {
			if(file.listFiles() != null)
				for (File subFile : file.listFiles())
					if(subFile.isDirectory())
						delete(subFile);
					else 
						subFile.delete();
			file.delete();
		}
		file.delete();
	}
	public static void deleteOldFiles(long days, File file) {
		if (file.exists()) {
			File[] listFiles = file.listFiles();
			
			long eligibleForDeletion = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000 );
			
			for (File listFile: listFiles)
				if (listFile.lastModified() < eligibleForDeletion) {
					if (!listFile.delete()) System.out.println("Sorry Unable to Delete File: "+listFile.getAbsolutePath());
					//System.out.println(listFile.getName());
				}
		}
	}
	public static ArrayList<File> getOldFiles(long days, File file, Boolean checkSub) {
		if (file.exists()) {
			ArrayList<File> oldFiles = new ArrayList<File>();
			File[] listFiles = file.listFiles();
			
			long eligibleForDeletion = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000 );
			if(listFiles!=null)
				for (File listFile: listFiles) {
					if(listFile.isDirectory() & checkSub) {
						ArrayList<File> files = getOldFiles(days,listFile, checkSub);
						for(File f:files)
							oldFiles.add(f);
					} else
						if (listFile.lastModified() < eligibleForDeletion)
							oldFiles.add(listFile);
				}
			return oldFiles;
		}
		return null;
	}
	/* getLength(file)
	 * This function will return the length of a file or folder by use of recursion in bytes
	 */
	public static long getLength(File file) {
		if(file.isDirectory()) {
			long re = 0;
			File[] listFiles = file.listFiles();
			if(listFiles!=null	)
				for (File f:listFiles)
					re += getLength(f);
			return re;
		}else
			return file.length();
	}
	
	public static long countFiles(File file) {
		if(file.isDirectory()) {
			long re = 0;
			File[] listFiles = file.listFiles();
			if(listFiles!=null	)
				for (File f:listFiles)
					re += countFiles(f);
			return re;
		}else
			return 1;
	}
	
	public static void unzip(File zipFilePath, String destDir) {
		File dir = new File(destDir);
		// create output directory if it doesn't exist
		if(!dir.exists()) dir.mkdirs();
		FileInputStream fis;
		//buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while(ze != null){
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("Unzipping to "+newFile.getAbsolutePath());
				//create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				//close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			//close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean checkFlag(Map<String,Boolean> flags, String str) {
		if(flags == null) return false;
		if(flags.get(str) == null) return false;
		return flags.get(str);
	}
	
	/* copy(originalFile, copyFile)
	 * copies all files from original file folder to the dir of copy file
	 * if a copy of file exists already it will delete and replace it
	 */
	public static void copy(File original, File copy) {
		copy(original, copy, new HashMap<String, Boolean>());
	}
	public static void copy(File original, File copy, Map<String,Boolean> flags) {
		if(!original.exists()) {
			System.err.println("Original file does not exist");
			return;
		}
		if(copy.getParentFile() != null && !copy.getParentFile().exists())
			copy.getParentFile().mkdirs();
		if( flags != null && flags.get("deleteExisting") != null )
			if(flags.get("deleteExisting") == true && copy.exists()) {
	//			delete(copy);
				System.out.println("DELETEING "+copy.getAbsolutePath());
			}
		
		if(original.isDirectory()) {
			File[] list = original.listFiles();
			if(list != null)
				for(File file:list) {
					File newOriginal = new File( original.getAbsolutePath() + "\\" + file.getName() );
					File newCopy = new File( copy.getAbsolutePath() + "\\" + file.getName() );
					copy(newOriginal,newCopy);
				}
		} else if(original.isFile()) {
			if(copy.exists()) return;
			try {
				Files.copy(original.toPath(), copy.toPath(), StandardCopyOption.REPLACE_EXISTING);
				if(checkFlag(flags, "printOutput"))
					System.out.println(original.getAbsolutePath() + " - > "+copy.getAbsolutePath());
			} catch (Exception e) {
				System.out.println( original.getAbsolutePath() );
				System.out.println( copy.getAbsolutePath() );
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<String> readFile(File file) {
		ArrayList<String> list = null;
		
		if(file == null || !file.exists()) return null;
		try {
			list = new ArrayList<String>();
			Scanner scanner = new Scanner( file );
			while(scanner.hasNextLine())
				list.add(scanner.nextLine());
			scanner.close();
		} catch (FileNotFoundException e) {}
		return list;
	}
}
