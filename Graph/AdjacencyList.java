/**
	Author	: Tom Choi
	Date	: 08/22/2016
	
	Implemenetaion of BFS, DFS and Topological Sort
	for the graph represented by the adjacency matrix
	which uses Java built-in hash map.
	
	NOTE
	There exist redundant codes in order to pratice
	coding by keep writing codes over again.
*/

import java.util.*;
import java.io.*;

public class AdjacencyList extends AbstractGraph{
	
	/** Constructor */
	public AdjacencyList(Scanner scan){
		super(scan);
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
			LinkedList<Edge> adj = map.get(next);
			if(adj != null){
				for(Edge e : adj){
					if(visited[e.getDest()] == false){
						visited[e.getDest()] = true;
						queue.add(e.getDest());
					}
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
			LinkedList<Edge> adj = map.get(next);
			if(adj != null){
				for(int i = adj.size()-1; i >= 0; i--){
					Edge e = adj.get(i);
					if(visited[e.getDest()] == false){
						visited[e.getDest()] = true;
						stack.push(e.getDest());
					}
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
	public ArrayList<Integer> topologicalOrder(){
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
				
				LinkedList<Edge> adj = map.get(node);
				if(adj != null){
					for(Edge e : adj){
						edgeCopy[e.getDest()] -= 1;
					}	
				}
			}
		}
		return topoOrder;
	}
	
	/**
	* Find the shortest path from the source node
	* in a weight graph using Dijkstra's Algorithm
	* It also prints out path to from start to destination
	*
	* @param	start	the start node
	* @return	dijkstra's shortest paths
	*/
	public double[] dijkstra(int start, int dest){
		if(!checkValidInput(start, dest)){
			System.err.println("Check for valid start and destination nodes!");
			return null;
		}
		
		/** Initailize Predecessor(P set) */
		int[] P = new int[numNodes];
		for(int i = 0; i < P.length; i++){
			P[i] = start;
		}
		
		/** Initialize V-S set */
		HashSet<Integer> VS = new HashSet<Integer>();
		for(int i = 0; i < numNodes; i++){
			if(i != start){
				VS.add(i);
			}
		}
		
		/** Initialize weighted array with max integers */
		double[] weight = new double[numNodes];
		for(int i = 0; i < weight.length; i++){
			weight[i] = MAX;
		}
		
		/** Set start to 0 and initialize its adjacent nodes */
		weight[start] = 0;
		LinkedList<Edge> startAdj = map.get(start);
		for(Edge e : startAdj){
			weight[e.getDest()] = e.getWeight();
		}
		
		/** While V-S not empty */
		while(!VS.isEmpty()){
			
			/** Find the node with the smallest weight */
			int minNode = findMinNode(VS, weight);
			VS.remove(minNode);		// remove from V-S
			
			/** For each node adjcaent to minNode */
			LinkedList<Edge> adj = map.get(minNode);
			if(adj != null){
				for(Edge e : adj){
					int adjNode = e.getDest();
					
					/** If new weight is greater than the old one, replace it*/
					if(weight[adjNode] > weight[minNode] + e.getWeight()){
						weight[adjNode] = weight[minNode] + e.getWeight();
						P[adjNode] = minNode;
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

		/** Check for valid input */
		if(start < 0 || start >= numNodes){
			System.err.println("The start node is not valid!");
			return null;
		}
		
		/** Initialize P set */
		int[] P = new int[numNodes];
		for(int i = 0; i < P.length; i++){
			P[i] = start;
		}
		
		/** Initialize S set*/
		HashSet<Integer> S = new HashSet<Integer>();
		S.add(start);
		
		/** Initialize V-S set */
		HashSet<Integer> VS = new HashSet<Integer>();
		for(int i = 0; i < numNodes; i++){
			if(i != start){
				VS.add(i);
			}
		}
		
		/** Initialize the set of weights */
		double[] weight = new double[numNodes];
		for(int i = 0; i < weight.length; i++){
			weight[i] = MAX;
		}
		
		/** Set the weights of start and its adjacent nodes */
		weight[start] = 0;
		LinkedList<Edge> adj = map.get(start);
		for(Edge e : adj){
			weight[e.getDest()] = e.getWeight();
		}
		
		/** While V-S set is not empty*/
		ArrayList<Edge> mst = new ArrayList<Edge>();
		while(!VS.isEmpty()){
			int minNode = findMinNode(VS, weight);
			VS.remove(minNode);
			S.add(minNode);
			
			/** insert the edge into mst */
			LinkedList<Edge> edges = map.get(P[minNode]);
			if(edges != null){
				for(Edge e : edges){
					if(e.getSrc() == P[minNode] && e.getDest() == minNode){
						mst.add(e);
					}
				}
			}
			
			/** update edge values from S to V-S set */
			for(int node : S){
				LinkedList<Edge> adjEdges = map.get(node);
				if(adjEdges != null){
					for(Edge e : adjEdges){
						if(weight[e.getDest()] > e.getWeight()){
							weight[e.getDest()] = e.getWeight();
							P[e.getDest()] = node;
						}
					}
				}
			}
		}
		return mst;
	}
	
	
	/** Test Code */
	public static void main(String[] args) throws FileNotFoundException{
		Scanner s = new Scanner(new File("WeightGraph.txt"));
		AbstractGraph graph = new AdjacencyList(s);
		ArrayList<Edge> mst = graph.primMST(0);
		for(int i = 0; i < mst.size(); i++){
			System.out.print(mst.get(i).toString() + " ");
		}System.out.println();
		testGraph(graph);
	}
	
	private static void testGraph(AbstractGraph graph){
		double[] weightPath = graph.dijkstra(0, 4);
		print(graph.BFS(0));
		print(graph.DFS(0));
		print(graph.topologicalOrder());
	}
	
	private static void print(ArrayList<Integer> arr){
		for(int i = 0; i < arr.size(); i++){
			System.out.print(arr.get(i) + " ");
		}System.out.println();
	}
}