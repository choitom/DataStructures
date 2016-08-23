/**
	Author	: Tom Choi
	Date	: 08/22/2016
	
	Implementation of BFS, DFS and Topological Sort
	for the graph represented by the adjacency matrix
*/

import java.util.*;
import java.io.*;

public class AdjacencyMatrix extends AbstractGraph{
	
	/** Matrix where each index represents the weight between nodes */
	private double[][] matrix;
	
	/** Constructor */
	public AdjacencyMatrix(Scanner scan){
		super(scan);
		initMatrix();
	}
	
	/**
	* Print out the adjacency matrix
	*/
	public void printMatrix(){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				System.out.print("[" + matrix[i][j] + "] ");
			}System.out.println();
		}
	}
	
	/**
	* Search the graph using the BFS algorithm
	*
	* @return	BFS order
	*/
	public ArrayList<Integer> BFS(int start){
		ArrayList<Integer> searched = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visited[start] = true;
		while(!queue.isEmpty()){
			int next = queue.poll();
			searched.add(next);
			for(int i = 0; i < matrix[next].length; i++){
				if(matrix[next][i] != 0 && visited[i] == false){
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		resetVisited();
		return searched;
	}
	
	/**
	* Search the graph using the DFS algorithm
	*
	* @return	DFS order
	*/
	public ArrayList<Integer> DFS(int start){
		ArrayList<Integer> searched = new ArrayList<Integer>();
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.push(start);
		visited[start] = true;
		while(!stack.isEmpty()){
			int next = stack.pop();
			searched.add(next);
			for(int i = matrix[next].length-1; i >= 0; i--){
				if(matrix[next][i] != 0 && visited[i] == false){
					visited[i] = true;
					stack.push(i);
				}
			}
		}
		resetVisited();
		return searched;
	}
	
	/**
	* Find the topological order in the graph
	*
	* @return	topological order
	*/
	public  ArrayList<Integer> topologicalOrder(){
	
		/** Check for any cycle in the graph */
		if(!isAcyclic()){
			System.err.println("The graph is not acyclic!");
			return null;
		}
		
		/** copy incoming edges */
		int[] edgeCopy = new int[numNodes];
		System.arraycopy(incomingEdges, 0, edgeCopy, 0, edgeCopy.length);
		
		ArrayList<Integer> topoOrder = new ArrayList<Integer>();
		
		while(topoOrder.size() != numNodes){
			ArrayList<Integer> start = noIncoming(edgeCopy);
			for(int i = 0; i < start.size(); i++){
				int node = start.get(i);
				topoOrder.add(node);
				edgeCopy[node] -= 1;
				
				for(int j = 0; j < matrix[node].length; j++){
					if(matrix[node][j] > 0){
						edgeCopy[j] -= 1;
					}
				}
			}
		}
		return topoOrder;
	}
	
	/**
	* Initialize the adjacency matrix using the hash map
	*/
	private void initMatrix(){
		matrix = new double[numNodes][numNodes];
		
		/** for each node */
		for(int node : map.keySet()){
			
			/** find its adjacent nodes */
			LinkedList<Edge> adj = map.get(node);
			if(adj != null){
				
				/** for each adjacent node */
				for(Edge e : adj){
					
					/** store the weight of the edge */
					matrix[node][e.getDest()] = e.getWeight();
				}
			}
		}
	}
	
	
	/** Test Code using Carleton College CS Courses */
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File("graph1.txt"));
		AdjacencyMatrix g = new AdjacencyMatrix(scan);
		
		/** Topological Ordering of Carleton College CS Courses*/
		String[] courses = {"Intro To CS", "Data Structures", "Math of CS",
							"Comp Org&Arc", "Software Design", "Programming Languages",
							"Natural Language Processing", "Computational Biology",
							"Parallel Dist. Comp.", "OS", "Comp Models of Cogn",
							"Diginal Electornics", "Computer Networks",
							"Algorithm", "Computing&Complexity"};
		
		/** Breath First Search */
		System.out.println("****Breath First Search****");
		ArrayList<Integer> bfs = g.BFS(0);
		for(int i = 0; i < bfs.size(); i++){
			System.out.print("[" + courses[bfs.get(i)] + "] ");
		}System.out.println("\n--------------------------------------------------------------------------");
		
		/** Depth First Search */
		System.out.println("****Depth First Search****");
		ArrayList<Integer> dfs = g.DFS(0);
		for(int i = 0; i < dfs.size(); i++){
			System.out.print("[" + courses[dfs.get(i)] + "] ");
		}System.out.println("\n--------------------------------------------------------------------------");
		
		/** Topological ordering */
		System.out.println("****Topological Ordering****");
		ArrayList<Integer> topo = g.topologicalOrder();
		for(int i = 0; i < topo.size(); i++){
			System.out.print("[" + courses[topo.get(i)] + "] ");
		}System.out.println("\n--------------------------------------------------------------------------");
	}
}