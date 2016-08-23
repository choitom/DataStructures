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
	
	/**
	* Find the shortest path from the source node
	* in a weight graph using Dijkstra's Algorithm
	*
	* @param	start	the start node
	* @return	dijkstra's shortest paths
	*/
	public double[] dijkstra(int start, int dest){
		if(!checkValidInput(start, dest)){
			System.err.println("Check for valid start and destination node!");
			return null;
		}
		
		/** Initialize Predecessor(P set) */
		int[] P = new int[numNodes];
		for(int i = 0; i < P.length; i++){
			P[i] = start;
		}
		
		/** Initialize V-S set*/
		HashSet<Integer> VS = new HashSet<Integer>();
		for(int i = 0; i < numNodes; i++){
			if(i != start){
				VS.add(i);
			}
		}
		
		/** Initialize weight array with max integers */
		double[] weight = new double[numNodes];
		for(int i = 0; i < weight.length; i++){
			weight[i] = MAX;
		}
		
		/** Set the weights of start and its adjacent nodes */
		weight[start] = 0;
		for(int i = 0; i < matrix[start].length; i++){
			if(matrix[start][i] > 0){
				weight[i] = matrix[start][i];
			}
		}
		
		while(!VS.isEmpty()){
			int minNode = findMinNode(VS, weight);
			VS.remove(minNode);		// remove from V-S set
			
			/** for each adjacent node */
			for(int i = 0; i < matrix[minNode].length; i++){
				if(matrix[minNode][i] > 0){
					if(weight[i] > weight[minNode] + matrix[minNode][i]){
						weight[i] = weight[minNode] + matrix[minNode][i];
						P[i] = minNode;		// store the parent node
					}
				}
			}
		}
		printPath(P, start, dest);
		return weight;
	}
	
	/**
	* Find the edges of the minimum spanning tree
	* found by Prim's Algorithm
	*
	* @param	start	the start node
	* @return	the set of edges in the MST
	*/
	public ArrayList<Edge> primMST(int start){
		/** P set */
		int[] P = new int[numNodes];
		for(int i = 0; i < P.length; i++){
			P[i] = start;
		}
			
		/** S set */
		HashSet<Integer> S = new HashSet<Integer>();
		S.add(start);
		
		/** VS set*/
		HashSet<Integer> VS = new HashSet<Integer>();
		for(int i = 0; i < numNodes; i++){
			if(i != start){
				VS.add(i);
			}
		}
			
		/** Weight set */
		double[] weight = new double[numNodes];
		for(int i = 0; i < numNodes; i++){
			weight[i] = MAX;
		}
			
		/** Set the weights of start and its adjcent nodes */
		weight[start] = 0;
		for(int i = 0; i < matrix[start].length; i++){
			if(matrix[start][i] > 0){
				weight[i] = matrix[start][i];
			}
		}
		
		/** While V-S set is not empty */
		ArrayList<Edge> mst = new ArrayList<Edge>();
		while(!VS.isEmpty()){
			int minNode = findMinNode(VS, weight);
			VS.remove(minNode);
			S.add(minNode);
			
			/** Edge the edge with the smallest weight */
			mst.add(new Edge(P[minNode], minNode, matrix[P[minNode]][minNode]));
			
			/** Update edge weights from S to V-S set*/
			for(int node : S){
				for(int i = 0; i < matrix[node].length; i++){
					if(weight[i] > matrix[node][i] && matrix[node][i] > 0){
						weight[i] = matrix[node][i];
						P[i] = node;
					}
				}
			}
		}
		return mst;
	}
	
	/** Test Code using Carleton College CS Courses */
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File("CSCourses.txt"));
		AdjacencyMatrix graph = new AdjacencyMatrix(scan);
		//CarletonCSCourses(graph);
		
		scan = new Scanner(new File("WeightGraph.txt"));
		graph = new AdjacencyMatrix(scan);
		
		ArrayList<Edge> mst = graph.primMST(0);
		for(int i = 0; i < mst.size(); i++){
			System.out.print(mst.get(i).toString() + " ");
		}System.out.println();
		
		//double[] dijkstra = graph.dijkstra(0,4);
	}
	
	private static void CarletonCSCourses(AbstractGraph graph){
		/** Topological Ordering of Carleton College CS Courses*/
		String[] courses = {"Intro To CS", "Data Structures", "Math of CS",
							"Comp Org&Arc", "Software Design", "Programming Languages",
							"Natural Language Processing", "Computational Biology",
							"Parallel Dist. Comp.", "OS", "Comp Models of Cogn",
							"Diginal Electornics", "Computer Networks",
							"Algorithm", "Computing&Complexity"};
		
		coursePrint(graph.BFS(0), courses);
		coursePrint(graph.DFS(0), courses);
		coursePrint(graph.topologicalOrder(), courses);
	}
	
	private static void coursePrint(ArrayList<Integer> arr, String[] courses){
		for(int i = 0; i < arr.size(); i++){
			System.out.print("[" + courses[arr.get(i)] + "] ");
		}System.out.println();
	}
}