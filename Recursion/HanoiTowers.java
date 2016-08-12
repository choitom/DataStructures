/*
	Author	: Tom Choi
	Date	: 
	
	Implementation of Towers of Hanoi
	
	Ideas
		- Suppose there are three disks
		- Then, the following processes must be taken
			1. Top two disks should be moved to the middle peg first (start -> middle)
			2. Move the bottom disk to the destination (start -> destination)
			3. Move the two disks in the middle to the destination (middle -> destination)
		- If a peg has only one disk, then always move it to the destination
		
	Important Point
		- Break the complicated problem into three separate cases in order
		- Then, implement each step
*/

public class HanoiTowers{
	public static void towersOfHanoi(int n, String start, String mid, String dest){
		if(n == 1){
			System.out.println("Move disk " + n + " from " + start + " to " + dest);
		}else{
			towersOfHanoi(n-1, start, dest, mid);
			System.out.println("Move disk " + n + " from " + start + " to " + dest);
			towersOfHanoi(n-1, mid, start, dest);
		}
	}
	
	public static void main(String[] args){
		towersOfHanoi(3, "S", "T", "D");
	}
}