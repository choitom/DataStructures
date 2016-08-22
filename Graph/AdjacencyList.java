/**
	Author	: Tom Choi
	Date	: 08/21/2016
	
	Implementation of Adjacency List
*/

import java.util.*;
import java.io.*;

public class AdjacencyList extends AbstractGraph{
	
	/** Constructor */
	public AdjacencyList(Scanner scan){
		super(scan);
	}
	
	/**
	* Search the graph using the breath-first search algorithm
	*
	* @return	the order of nodes searched in the graph using BFS
	*/
	public int[] BFS(int start){
		if(!checkStartNode(start)){
			System.err.println("Check for valid start node!!!");
			return null;
		}
		
		/** initialize a queue and add the start item */
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		
		/** store search results */
		int[] searched = new int[numNodes];
		int index = 0;
		visited[start] = 1;			// set the start visited
		
		int next;			// next node visited
		int adjacentNode;	// adjacent node
		
		while(!q.isEmpty()){
			next = q.poll();
			searched[index++] = next;
			
			/** retrieved nodes that are adjacent to the next */
			LinkedList<Edge> adjacentEdges = map.get(next);
			
			/** for each adjacent node */
			for(Edge e : adjacentEdges){
				adjacentNode = e.getDest();
				
				/** if not visited */
				if(visited[adjacentNode] == 0){
					visited[adjacentNode] = 1;	// mark visited
					q.add(adjacentNode);
				}
			}
		}
		
		return searched;
	}
	
	/**
	* Search the graph using the depth-first search algorithm
	*
	* @return	the order of nodes searched in the graph using DFS
	*/
	public int[] DFS(int start){
		int[] searched = new int[numNodes];
		return searched;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner s = new Scanner(new File("graph1.txt"));
		AdjacencyList g = new AdjacencyList(s);
		int[] bfs = g.BFS(0);
		for(int i = 0; i < bfs.length; i++){
			System.out.print(bfs[i] + " ");
		}System.out.println();
	}
}