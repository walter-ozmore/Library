package Misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetSystemInfo {
	
	public static void installPipPackage(String str) {
		if(!checkPipPackage(str)) {
			runCommand("pip install "+str);
		}
	}
	
	public static boolean checkPipPackage(String str) {
		for(String s:runCommand(getPip()+" list"))
			if(s.contains(str)) return true;
		return false;
	}
	
	static String pip = "";
	public static String getPip() {
		if(pip.length() > 0) return pip;
		String pi[] = new String[3];
		pi[0] = runCommandSubGetPython("pip --version");
		pi[1] = runCommandSubGetPython("pip2 --version");
		pi[2] = runCommandSubGetPython("pip3 --version");
		for(int z=0;z<pi.length;z++) {
			if(pi[z] != null && pi[z].contains("python 3"))
				if(z==0) {
					pip = "pip";
					return pip;
				} else {
					pip = "pip"+(z+1);
					return pip;
				}
		}
		System.err.println( "Pip not found!" );
		System.exit(0);
		return null;
	}
	/* This will check the version of python installed and return the correct syntax for running python ie. python, python2, or python3
	 * The String python is used to store the syntax so the system only has to check the version once
	 * Why is "python" spelled wrong?
	 */
	static String python = "";
	public static String getPython() {
		if(python.length() > 0) return python;
		String py[] = new String[3];
		py[0] = runCommandSubGetPython("python --version");
		py[1] = runCommandSubGetPython("python2 --version");
		py[2] = runCommandSubGetPython("python3 --version");
		for(int z=0;z<py.length;z++) {
			if(py[z] != null && py[z].startsWith("3"))
				if(z==0) {
					python = "python";
					return python;
				} else {
					python = "python"+(z+1);
					return python;
				}
		}
		if(isWindows())
			System.err.println( "Python 3 not found! Install python at https://www.python.org/downloads/" );
		else
			System.err.println( "Python 3 not found!" );
		System.exit(0);
		return null;
	}
	public static String runCommandSubGetPython(String com) {
		Process p;
		try {
			p = Runtime.getRuntime().exec( com );
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
	
			while ((line = input.readLine()) != null) {
				return line.substring("Python ".length());
			}
			p.waitFor();
		} catch (Exception e) {}
		return null;
	}
	public static String[] runCommand(String com) {
		String sp = "THIS WILL NEVER SHOW UP EVER";
		String re = "";
		Process p;
		try {
			p = Runtime.getRuntime().exec( com );
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
	
			while ((line = input.readLine()) != null)
				re += line + sp;
			re = re.substring(0, re.length() - sp.length());
			p.waitFor();
		} catch (Exception e) {}
		return re.split(sp);
	}
	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		if(os.contains("Windows"))
			return true;
		return false;
	}
}
