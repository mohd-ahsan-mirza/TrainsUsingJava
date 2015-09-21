import java.util.HashMap;


// This class is used for adding all the nodes there are in a list . For example A is one node, B is another node

public class Graph {

	private HashMap<String, Node> graph;

	Graph() {

		graph = new HashMap<String, Node>();
	}

	public boolean addNode(Node node) {

		if (node != null) {

			graph.put(node.getLabel(), node);
			return true;
		}

		else
			return false;

	}

	public HashMap<String, Node> getMap() {

		return graph;
	}

	public Node getNode(String key) {

		return graph.get(key);

	}
	
	public boolean containsNode(String key){
		
		if(graph.get(key)==null)
			return false;
		else
			return true;
	}

	
}
