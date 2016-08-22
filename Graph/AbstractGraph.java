/**
	Author	: Tom Choi
	Date	: 08/21/2016
	
	Abstract base class for Graph. A graph is a set of
	nodes and a set of edges. Nodes are represented by
	integers. Edges are ordered pairs of nodes with weight.
	
	It reads a graph text file and generate edge objects.
	Then, map each node and its adjacent edges to the map,
	thereby generating an adjacency matrix.
*/

import java.util.*;
import java.io.*;

public abstract class AbstractGraph{
	private Edge[] edges;
	
	/** The number of nodes and edges in the graph */
	protected int numEdges;
	protected int numNodes;
	
	/** Keeps track of each node visited or not */
	protected int[] visited;
	
	/** Hash map for storing node and its adjacent edges */
	protected HashMap<Integer, LinkedList<Edge>> map;
	
	public AbstractGraph(Scanner scan){
		readGraphFile(scan);
		generateGraph();
	}
	
	/**
	* Read a graph text file and create edge objects
	* The first line has the number of edges
	* The following lines have src, dest, and weight
	*/
	protected void readGraphFile(Scanner s){
		numEdges = s.nextInt();
		edges = new Edge[numEdges];
		
		int i = 0;
		int src;
		int dest;
		double weight;
		
		do{
			src = s.nextInt();
			dest = s.nextInt();
			weight = s.nextDouble();
			edges[i++] = new Edge(src, dest, weight);
		}while(s.hasNext());
	}
	
	/**
	* Generate a graph in accordance with the format of a graph
	* i.e. adjacency list or adjacency matrix
	*/
	protected void generateGraph(){
		map = new HashMap<Integer, LinkedList<Edge>>();
		for(int i = 0; i < edges.length; i++){
			Edge e = edges[i];
			LinkedList<Edge> lst;
			int key = e.getSrc();
			
			if(!map.containsKey(key)){
				lst = new LinkedList<Edge>();
				lst.add(e);
				map.put(key, lst);
			}else{
				lst = map.get(key);
				lst.add(e);
				map.put(key, lst);
			}
		}
		visited = new int[size()];
		numNodes = size();
	}
	
	/**
	* Returns the number of nodes
	*
	* @return	the number of nodes in the graph
	*/
	protected int size(){
		return map.keySet().size();
	}
	
	/**
	* Check if the start node is valid or not
	*/
	protected boolean checkStartNode(int start){
		return (start >= 0 && start <= numNodes) ? true : false;
	}
	
	/**
	* Set all nodes unvisited after traversal
	*/
	protected void setUnvisited(){
		for(int i = 0; i < visited.length; i++){
			visited[i] = 0;
		}
	}
	
	/**
	* Search the graph using the breath-first search algorithm
	*
	* @return	the order of nodes searched in the graph using BFS
	*/
	public abstract int[] BFS(int start);
	
	/**
	* Search the graph using the depth-first search algorithm
	*
	* @return	the order of nodes searched in the graph using DFS
	*/
	public abstract int[] DFS(int start);
}