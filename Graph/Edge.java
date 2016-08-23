/**
	Author	: Tom Choi
	Date	: 08/22/2016
	
	Implemenetation of Directed Edge
*/

public class Edge{
	private int src;
	private int dest;
	private double weight;
	
	public Edge(int src, int dest, double weight){
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
	
	public int getSrc(){
		return src;
	}
	
	public int getDest(){
		return dest;
	}
	
	public double getWeight(){
		return weight;
	}
	
	public String toString(){
		return "{" + src + "," + dest + "}";
	}
}