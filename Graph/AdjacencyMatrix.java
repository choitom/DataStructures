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
		int[] searched = new int[numNodes];
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
	}
}