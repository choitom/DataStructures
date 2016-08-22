import java.util.*;
import java.io.*;

public class AdjacencyMatrix extends AbstractGraph{
	public AdjacencyMatrix(Scanner scan){
		super(scan);
	}
	
	/**
	* Search the graph using the BFS algorithm
	*
	* @return	BFS order
	*/
	public ArrayList<Integer> BFS(int start){
		return null;
	}
	
	/**
	* Search the graph using the DFS algorithm
	*
	* @return	DFS order
	*/
	public ArrayList<Integer> DFS(int start){
		return null;
	}
	
	/**
	* Find the topological order in the graph
	*
	* @return	topological order
	*/
	public  ArrayList<Integer> topologicalOrder(){
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File("graph1.txt"));
		AdjacencyMatrix g = new AdjacencyMatrix(scan);
	}
}