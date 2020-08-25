package FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class SyncOutput {
	long onLoc = 0;
	File file;
	FileWriter writer;
	ArrayList<Output> output = new ArrayList<Output>();
	
	public SyncOutput(File file) {
		this.file = file;
		try { writer = new FileWriter(file); } catch (IOException e) { e.printStackTrace(); }
	}
	public void flush() {
		try {
			for(Output o:output) {
				if(o.loc==onLoc) {
					try { writer.write(o.value); } catch (IOException e) { e.printStackTrace(); }
					System.out.print(o.value);
				}
				onLoc++;
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(String str, long loc) {
		output.add(new Output(str,loc));
		for(Output o:output)
			if(o.loc==onLoc) {
				onLoc++;
				try { writer.write(o.value); } catch (IOException e) { e.printStackTrace(); }
				System.out.print(o.value);
			}
	}
	
}

class Output {
	long loc;
	String value;
	
	public Output(String value, long loc) {
		this.loc = loc;
		this.value = value;
	}
}