/**
	Author	: Tom Choi
	Date	: 08/22/2016
	
	Implemenetaion of BFS, DFS and Topological Sort
	for the graph represented by the adjacency matrix
	which uses Java built-in hash map.
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
			S.add(minNode);			// add to S
			
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
	* Prints the path from start to destination
	*/
	private void printPath(int[] P, int start, int dest){
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
	* Find the minimum node with the minimum weight from V-S set
	* 
	* @param	VS			V-S set
	* @param	weight		the array of weights
	* @return	minimum node
	*/
	private int findMinNode(HashSet<Integer> VS, double[] weight){
		double min = -1;
		int minNode = -1;
		for(int node : VS){
			if(min == -1 && minNode == -1){
				minNode = node;
				min = weight[minNode];
			}else if(weight[node] < min){
				minNode = node;
				min = weight[minNode];
			}
		}
		return minNode;
	}
	
	/**
	* Nicely prinout an array
	*
	* @param	array to print out
	*/
	public void printDijkstra(double[] weight){
		for(int i = 0; i < weight.length; i++){
			if(weight[i] != MAX){
				System.out.print("[" + weight[i] + "] ");
			}else{
				System.out.print("[MAX] ");
			}
		}System.out.println();
	}
	
	
	/**
	* Check for valid start and destination nodes
	*/
	private boolean checkValidInput(int start, int dest){
		if(!(start >= 0 && dest >=0 && start < numNodes && dest < numNodes)){
			return false;
		}return true;
	}
	
	
	/** Test Code */
	public static void main(String[] args) throws FileNotFoundException{
		Scanner s = new Scanner(new File("WeightGraph.txt"));
		AbstractGraph g = new AdjacencyList(s);
	}
	
	public static void testGraph(AbstractGraph graph){
		double[] weightPath = graph.dijkstra(0, 4);
		g.printDijkstra(weightPath);
	}
}