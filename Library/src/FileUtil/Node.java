package FileUtil;

import java.util.HashMap;
import java.util.Map;

public class Node {
	Node up = null;
	Map<String, Obj> map = new HashMap<String, Obj>();
	public Node(Node up) {
		this.up = up;
	}
}
