package FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

// File format system
public class FFS {
	Node headNode = new Node(null);
	
	public FFS(File file) {
		loadFile( file );
		print();
	}
	public void loadFile(File file) {
		try {
			Scanner scan = new Scanner(file);
			Node currentNode = headNode;
			int currentOffSet = 0;
			while(scan.hasNextLine()) {
				String str = scan.nextLine();
				if(!str.startsWith(" ")) {
					currentNode = headNode;
					Node node = new Node(headNode);
					currentNode.map.put(str, new Obj(node));
					currentNode = node;
				} else {
					
//					Pattern.compile("^ {"+currentOffSet+"}[^ ]").matcher(str).start;
					
					String sameLen = "";
					for(int z=0;z<currentOffSet;z++) sameLen += " ";
					
					if(str.startsWith(sameLen)) {
						Node node = new Node(headNode);
						currentNode.map.put(str, new Obj(node));
						currentNode = node;
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) { e.printStackTrace(); }
	}
	public void example() {
//	set("wow.testInt",10);
//	set("wow.compDif",12);
//	set("wow.test.var",2);
//	print();
	}
	public Obj get(String loc) { return get(loc, headNode); }
	private Obj get(String loc, Node node) {
		String list[] = loc.split("[.]");
		if(list.length <= 1 ) {
			return node.map.get(loc);
		} else {
			for(String str:headNode.map.keySet())
				if(str.equals(list[0]))
					return get(loc.substring( loc.indexOf(".") + 1 ), node.map.get(str).node);
		}
		return null;
	}
	
	public void set(String loc, Obj var) { set(loc, var, headNode); }
	private void set(String loc, Obj var, Node currentNode) {
		String list[] = loc.split("[.]");
		if(list.length <= 1) {
			currentNode.map.put(loc, var);
		} else {
			for(String str:currentNode.map.keySet()) {
				if(str.equals(list[0])) {
					set(
							loc.substring(loc.indexOf(".")+1),
							var,
							currentNode.map.get(str).node );
					return;
				}
			}
			Node n = new Node(currentNode);
			currentNode.map.put(list[0], new Obj(n));
			set(loc.substring(loc.indexOf(".")+1), var, n );
		}
	}
	
	public void print() { print(headNode, 0); }
	private void print(Node node, int ind) {
		String ss = "";
		for(int z=0;z<ind;z++)
			ss += " ";
		
		
		for(String str:node.map.keySet()) {
			System.out.print(ss+str);
			String s = node.map.get(str).print();
			if(s != null && s.length() > 0)
				System.out.print(" = "+s);
			System.out.println();
			if( node.map.get(str).type == 2 ) print(node.map.get(str).node, ind);
		}
	}
	
	public static void main(String args[]) { new FFS( new File("test.ffs") ); }
}
