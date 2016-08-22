import java.util.*;
import java.io.*;

public class AdjacencyList extends AbstractGraph{
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
	
	/** Test Code using Carleton College CS courses*/
	public static void main(String[] args) throws FileNotFoundException{
		Scanner s = new Scanner(new File("graph1.txt"));
		AdjacencyList g = new AdjacencyList(s);
		
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