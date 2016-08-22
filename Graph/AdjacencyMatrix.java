/**
	Author	: Tom Choi
	Date	: 08/21/2016
	
	Implemenetation of Adjacency Matrix
	
	Using an adjacency matrix created by AbstractGraph,
	it stores the connectivity between nodes and weights to a 2-D matrix.
*/

import java.util.*;
import java.io.*;

public class AdjacencyMatrix extends AbstractGraph{
	
	/** 2-D matrix whose each index represents the connectivity and weight */
	private double[][] matrix;
	
	/** Constructor */
	public AdjacencyMatrix(Scanner scan){
		super(scan);
		generateMatrix();
	}
	
	/**
	* Search the graph using the breath-first search algorithm
	*
	* @return	the order of nodes searched in the graph using BFS
	*/
	public int[] BFS(int start){
		/** check for valid input */
		checkStartNode(start);
		
		/** initialize queue */
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		
		int[] searched = new int[numNodes];
		int index = 0;
		visited[start] = 1;		// set the start node visited
		
		int next;
		
		while(!queue.isEmpty()){
			next = queue.poll();
			searched[index++] = next;
			
			for(int i = 0; i < matrix[next].length; i++){
				if(visited[i] == 0 && matrix[next][i] != 0){
					visited[i] = 1;
					queue.add(i);
				}
			}
		}
		setUnvisited();
		return searched;
	}
	
	/**
	* Search the graph using the depth-first search algorithm
	*
	* @return	the order of nodes searched in the graph using DFS
	*/
	public int[] DFS(int start){
		/** check for valid input */
		checkStartNode(start);
		
		/** initialize stack */
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.push(start);
		
		int[] searched = new int[numNodes];
		int index = 0;
		visited[start] = 1;
		
		int next;
		
		while(!stack.isEmpty()){
			next = stack.pop();
			searched[index++] = next;
			
			for(int i = matrix[next].length-1; i >= 0; i--){
				if(visited[i] == 0 && matrix[next][i] != 0){
					visited[i] = 1;	// set node visited;
					stack.push(i);
				}
			}
		}
		setUnvisited();
		return searched;
	}
	
	/**
	* Creates an adjacency matrix based on
	* the adjacency matrix stored in the hash map
	*/
	private void generateMatrix(){
		matrix = new double[numNodes][numNodes];
		
		/** For each key(node) */
		for(int key : map.keySet()){
			
			/** Find its adjacent edges */
			LinkedList<Edge> adjacentEdges = map.get(key);
			
			/** For each adjacent edge */
			for(Edge e : adjacentEdges){
				matrix[key][e.getDest()] = e.getWeight();
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File("graph1.txt"));
		AdjacencyMatrix g = new AdjacencyMatrix(scan);
		
		int[] bfs = g.BFS(0);
		int[] dfs = g.DFS(0);
		
		for(int i = 0; i < bfs.length; i++){
			System.out.print(bfs[i] + " ");
		}System.out.println();
		
		for(int i = 0; i < dfs.length; i++){
			System.out.print(dfs[i] + " ");
		}System.out.println();
	}
}