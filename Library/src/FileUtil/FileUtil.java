package FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil {
	
	public static void delete(File file){
		if(file.isDirectory()) {
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
	public static long getDirFileSize(File file) {
		if(file.isDirectory()) {
			long re = 0;
			File[] listFiles = file.listFiles();
			if(listFiles!=null	)
				for (File f:listFiles)
					re += getDirFileSize(f);
			return re;
		}else
			return file.length();
	}
	/* Type
	 * 0 - Bytes
	 * 1 - Kilobyte
	 * 2 - Megabyte
	 * 3 - Gigabyte
	 * 4 - Terabyte
	 * 5 - Petabyte
	 * 6 - Exabyte
	 */
	public static double getFileSizeIn(long size, int type) {
		return size/(Math.pow(1024,type));
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
	public static void copy(File original, File copy) {
		if(!original.exists() | !copy.getParentFile().exists()) return;
		if(copy.exists()) delete(copy);
		try {
			Files.copy(original.toPath(), copy.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(original.isDirectory()) {
			for(File file:original.listFiles()) {
				File newOriginal = new File( original.getAbsolutePath() + "\\" + file.getName() );
				File newCopy = new File( copy.getAbsolutePath() + "\\" + file.getName() );
				copy(newOriginal,newCopy);
			}
		}
	}
}
