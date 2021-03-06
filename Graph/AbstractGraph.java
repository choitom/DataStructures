/**
	Author	: Tom Choi
	Date	: 08/22/2016
	
	Base class for Adjacency List and Adjacency Matrix.
	Using built-in hash map, it maps adjacent nodes for
	each node in the graph.
	
	It also provides a method that determines if a given graph
	is acyclic or not by using the DFA and Pigeon Hole principle.
*/

import java.util.*;
import java.io.*;

public abstract class AbstractGraph{
	
	protected HashMap<Integer, LinkedList<Edge>> map;
	protected int numNodes;
	protected int[] incomingEdges;
	protected boolean[] visited;
	protected final double MAX = Double.MAX_VALUE;
	
	public AbstractGraph(Scanner scan){
		readGraphFile(scan);
		initIncomingEdges();
		visited = new boolean[numNodes];
	}
	
	/**
	* Print out the mapped graph
	*/
	protected void printMap(){
		for(int i = 0; i < numNodes; i++){
			LinkedList<Edge> edges = map.get(i);
			if(edges == null){
				System.out.println(i + ": null");
			}else{
				System.out.print(i + ": ");
				for(Edge e : edges){
					System.out.print(e.getDest() + " ");
				}System.out.println();
			}
		}
	}
	
	/**
	* Finds if there exists a cycle in the graph using the following method
	*
	* The graph has nC2 = n*(n-1)/2 number of edges at most.
	* So, if it iterates more than nC2 times while running DFS
	* then, there must an edge that is revisited
	*/
	protected boolean isAcyclic(){
		boolean acyclic = true;
		boolean checkIncomingEdges = false;
		ArrayList<Integer> noIncoming = new ArrayList<Integer>();
		
		for(int i = 0; i < incomingEdges.length; i++){
			if(incomingEdges[i] == 0){
				noIncoming.add(i);
			}
		}
		
		if(noIncoming.size() == 0){
			return false;
		}
		
		for(int i = 0; i < noIncoming.size(); i++){
			acyclic = isAcyclicHelper(noIncoming.get(i));
		}
		return acyclic;
	}
	
	/**
	* Reset all nodes unvisited
	*/
	protected void resetVisited(){
		for(int i = 0; i < visited.length; i++){
			visited[i] = false;
		}
	}
	
	/**
	* Retrieve nodes that has no incoming edges
	*/
	protected ArrayList<Integer> noIncoming(int[] arr){
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for(int i = 0; i < arr.length; i++){
			if(arr[i] == 0){
				lst.add(i);
			}
		}
		return lst;
	}
	
	/**
	* Find the node with the minimum weight in V-S set
	*
	* @param	VS			V-S set
	* @param	weight		weight array
	* @return	node with minimum weight
	*/
	protected int findMinNode(HashSet<Integer> VS, double[] weight){
		int minNode = Integer.MAX_VALUE;
		double min = MAX;
		for(int node : VS){
			if(weight[node] < min){
				min = weight[node];
				minNode = node;
			}
		}
		return minNode;
	}
	
	/**
	* Prints the path from start to destination
	*/
	protected void printPath(int[] P, int start, int dest){
		ArrayList<Integer> pathList = new ArrayList<Integer>();
		while(dest != start){
			pathList.add(dest);
			dest = P[dest];
		}
		pathList.add(start);
		for(int i = pathList.size()-1; i >= 0; i--){
			System.out.print(pathList.get(i));
			if(i != 0){
				System.out.print(" -> ");
			}
		}System.out.println();
	}
	
	/**
	* Check for valid start and destination nodes
	*/
	protected boolean checkValidInput(int start, int dest){
		if(!(start >= 0 && dest >=0 && start < numNodes && dest < numNodes)){
			return false;
		}return true;
	}
	
	/**
	* Run DFS on the start node
	*/
	private boolean isAcyclicHelper(int start){
		int max = numNodes*(numNodes-1)/2 + 1;
		Deque<Integer> stack = new ArrayDeque<Integer>();
		int counter = 0;
		stack.push(start);
		
		while(!stack.isEmpty() && counter <= max){
			int next = stack.pop();
			LinkedList<Edge> adj = map.get(next);
			if(adj != null){
				for(Edge e : adj){
					stack.push(e.getDest());
				}
			}		
		}
		if(counter > max){
			return false;
		}
		return true;
	}
	
	/**
	* Read a graph text file and create a graph
	*/
	private void readGraphFile(Scanner scan){
		Edge[] edges = new Edge[scan.nextInt()];
		int index = 0;
		
		while(scan.hasNext()){
			int src = scan.nextInt();
			int dest = scan.nextInt();
			double weight = scan.nextDouble();
			edges[index++] = new Edge(src, dest, weight);
		}
		generateAdjacencyList(edges);
	}
	
	/**
	* Generate the adjacency list using hash map
	* It maps each node and its adjacent edges
	*/
	private void generateAdjacencyList(Edge[] edge){
		findNodes(edge);
		map = new HashMap<Integer, LinkedList<Edge>>();
		
		for(Edge e : edge){
			LinkedList<Edge> lst;
			int src = e.getSrc();
			if(!map.containsKey(src)){
				lst = new LinkedList<Edge>();
				lst.add(e);
				map.put(src, lst);
			}else{
				map.get(src).add(e);
			}
		}
		for(int i = 0; i < numNodes; i++){
			if(!map.containsKey(i)){
				map.put(i, null);
			}
		}
	}
	
	/**
	* Find the number of distinct nodes
	*/
	private void findNodes(Edge[] edge){
		HashSet<Integer> set = new HashSet<Integer>();
		for(Edge e : edge){
			set.add(e.getSrc());
			set.add(e.getDest());
		}
		numNodes = set.size();
	}
	
	/**
	* Find the number of incoming edges for each node
	* Used for finding topological order in the graph
	*/
	private void initIncomingEdges(){
		incomingEdges = new int[numNodes];
		for(int node : map.keySet()){
			LinkedList<Edge> edges = map.get(node);
			if(edges != null){
				for(Edge e : edges){
					incomingEdges[e.getDest()]++;
				}	
			}	
		}
	}
	
	/**
	* Search the graph using the BFS algorithm
	*
	* @param	start	the start node
	* @return	BFS order
	*/
	public abstract ArrayList<Integer> BFS(int start);
	
	/**
	* Search the graph using the DFS algorithm
	*
	* @param	start	the start node
	* @return	DFS order
	*/
	public abstract ArrayList<Integer> DFS(int start);
	
	/**
	* Find the topological order in the graph
	*
	* @param	start	the start node
	* @return	topological order
	*/
	public abstract ArrayList<Integer> topologicalOrder();
	
	/**
	* Find the shortest path from the source node
	* in a weight graph using Dijkstra's Algorithm
	*
	* @param	start	the start node
	* @return	dijkstra's shortest paths
	*/
	public abstract double[] dijkstra(int start, int dest);
	
	/**
	* Find the edges of the minimum spanning tree
	* found by Prim's Algorithm
	*
	* @param	start	the start node
	* @return	the set of edges in the MST
	*/
	public abstract ArrayList<Edge> primMST(int start);
}