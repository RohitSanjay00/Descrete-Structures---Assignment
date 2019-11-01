// A Java program to check if a given graph is Eulerian or not 
import java.io.*; 
import java.util.*; 
import java.util.LinkedList; 
	  
// This class represents an undirected graph using adjacency list 
// representation 
public class DSproject {
	
 
	    private int V;   // No. of vertices 
	  
	    // Array  of lists for Adjacency List Representation 
	    private LinkedList<Integer> adj[]; 
	  
	    // Constructor 
	    DSproject(int v) 
	    { 
	        V = v; 
	        adj = new LinkedList[v]; 
	        for (int i=0; i<v; ++i) 
	            adj[i] = new LinkedList(); 
	    } 
	  
	    //Function to add an edge into the graph 
	    void addEdge(int v, int w) 
	    { 
	        adj[v].add(w);// Add w to v's list. 
	        adj[w].add(v); //The graph is undirected 
	    } 
	  
	    // A function used by DFS 
	    void DFSUtil(int v,boolean visited[]) 
	    { 
	        // Mark the current node as visited 
	        visited[v] = true; 
	  
	        // Recur for all the vertices adjacent to this vertex 
	        Iterator<Integer> i = adj[v].listIterator(); 
	        while (i.hasNext()) 
	        { 
	            int n = i.next(); 
	            if (!visited[n]) 
	                DFSUtil(n, visited); 
	        } 
	    } 
	  
	    // Method to check if all non-zero degree vertices are 
	    // connected. It mainly does DFS traversal starting from 
	    boolean isConnected() 
	    { 
	        // Mark all the vertices as not visited 
	        boolean visited[] = new boolean[V]; 
	        int i; 
	        for (i = 0; i < V; i++) 
	            visited[i] = false; 
	  
	        // Find a vertex with non-zero degree 
	        for (i = 0; i < V; i++) 
	            if (adj[i].size() != 0) 
	                break; 
	  
	        // If there are no edges in the graph, return true 
	        if (i == V) 
	            return true; 
	  
	        // Start DFS traversal from a vertex with non-zero degree 
	        DFSUtil(i, visited); 
	  
	        // Check if all non-zero degree vertices are visited 
	        for (i = 0; i < V; i++) 
	           if (visited[i] == false && adj[i].size() > 0) 
	                return false; 
	  
	        return true; 
	    } 
	  
	    /* The function returns one of the following values 
	       0 --> If grpah is not Eulerian 
	       1 --> If graph has an Euler path (Semi-Eulerian) 
	       2 --> If graph has an Euler Circuit (Eulerian)  */
	    int isEulerian() 
	    { 
	        // Check if all non-zero degree vertices are connected 
	        if (isConnected() == false) 
	            return 0; 
	  
	        // Count vertices with odd degree 
	        int odd = 0; 
	        for (int i = 0; i < V; i++) 
	            if (adj[i].size()%2!=0) 
	                odd++; 
	  
	        // If count is more than 2, then graph is not Eulerian 
	        if (odd > 2) 
	            return 0; 
	  
	        // If odd count is 2, then semi-eulerian. 
	        // If odd count is 0, then eulerian 
	        // Note that odd count can never be 1 for undirected graph 
	        return (odd==2)? 1 : 2; 
	    } 
	    Boolean isCyclicUtil(int v, Boolean visited[], int parent) 
	    { 
	        // Mark the current node as visited 
	        visited[v] = true; 
	        Integer i; 
	  
	        // Recur for all the vertices adjacent to this vertex 
	        Iterator<Integer> it = adj[v].iterator(); 
	        while (it.hasNext()) 
	        { 
	            i = it.next(); 
	  
	            // If an adjacent is not visited, then recur for that 
	            // adjacent 
	            if (!visited[i]) 
	            { 
	                if (isCyclicUtil(i, visited, v)) 
	                    return true; 
	            } 
	  
	            // If an adjacent is visited and not parent of current 
	            // vertex, then there is a cycle. 
	            else if (i != parent) 
	                return true; 
	        } 
	        return false; 
	    } 
	  
	    // Returns true if the graph contains a cycle, else false. 
	    Boolean isCyclic() 
	    { 
	        // Mark all the vertices as not visited and not part of 
	        // recursion stack 
	        Boolean visited[] = new Boolean[V]; 
	        for (int i = 0; i < V; i++) 
	            visited[i] = false; 
	  
	        // Call the recursive helper function to detect cycle in 
	        // different DFS trees 
	        for (int u = 0; u < V; u++) 
	            if (!visited[u]) // Don't recur for u if already visited 
	                if (isCyclicUtil(u, visited, -1)) 
	                    return true; 
	  
	        return false; 
	    }
	  
	    // Function to run test cases 
	    void test() 
	    { 
	        int res = isEulerian(); 
	        
	        if (res == 1) 
	            System.out.println("graph has a  path"); 
	        else
	           System.out.println("graph has a  circuit"); 
	    } 
	  
	    // Driver method 
	    public static void main(String args[]) 
	    { 
	        // Let us create and test graphs shown in above figures 
	    	Scanner in=new Scanner(System.in);
	    	DSproject g1 = new DSproject(20); 
	    	int a=0,b=0;
	    	System.out.println("How many additions do you want ?");
	    	int n=in.nextInt();
	        for(int i=0;i<n;i++)
	        {
	        	System.out.println("enter A,b");
	        	a=in.nextInt();
	        	b=in.nextInt();
	        	g1.addEdge(a,b);
	        }
	        	
	        g1.test(); 
	        if (g1.isCyclic()) 
	            System.out.println("Graph contains cycle"); 
	       
	  
	        
	        
	    } 
	} 


