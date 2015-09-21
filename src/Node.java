import java.util.*;

// The following class is used for creating a Node object which is a point in the graph.

class Node {
	
    private String label;
    private HashMap<String,Node> adjacencyList;
    private HashMap<String,Integer> distances;
   
    
    Node(String l){
    	
    	if(!(l.isEmpty())&&l !=null){
    		
    		label=l;
    		adjacencyList=new HashMap<String,Node>();
    		distances=new HashMap<String,Integer>();
    		
    	}
    	
    	else{
    		label=null;
    		adjacencyList=null;
    		distances=null;
    	
    	}
    }
    
    
    public String getLabel(){
    	return label;
    }
    
    
    public Node getAdjacentNode(String identifier){
    	
    		return adjacencyList.get(identifier);
    }
    
    public HashMap<String,Node> getList(){
    	
    	return this.adjacencyList;
    }
    
    
    public int getDistanceToNode(String destination){
    	
    	if(distances.get(destination)!=null)
    		return distances.get(destination);
    	
    	else
    		return -1;
    }
    
    public boolean addinList(Node node,int distance,String identifier){
    	
    	if(node !=null && distance>0){
    		
    		this.adjacencyList.put(identifier,node);
    		this.distances.put(identifier,distance);
    		
    		return true;
    		
    	}
    	
    	else
    		return false;
    	
    }
    
    
    

    
}

