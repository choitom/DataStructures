/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of Fibonacci Sequence using
		1. Normal inefficient recursion -> O(2^n)
		2. Tail recursion -> O(n)
		3. Memoization -> O(n)
*/

public class Fibonacci{
	private double[] memo;
	
	public Fibonacci(){}
	
	// each sequence begets two activation record
	// so that the overall runtime becomes exponential
	public double fib(int n){
		if(n < 0){
			System.err.println("Bad input: " + n);
			return -1;
		}else if(n <= 2 && n > 0){
			return 1;
		}else{
			return fib(n-1) + fib(n-2);
		}
	}
	
	public double fibTail(int n){
		return fibTailHelper(1, 0, n);
	}
	
	// keep adding and storing the sequence value
	// and returns it when it reaches the base case
	private double fibTailHelper(double current, double previous, int n){
		if(n < 0){
			System.err.println("Bad input: " + n);
			return -1;
		}else if(n == 1){
			return current;
		}else{
			return fibTailHelper(current + previous, current, n-1);
		}
	}
	
	// records each fibonacci sequence value to an array
	public double fibMemo(int n){
		if(n < 0){
			System.err.println("Bad input: " + n);
			return -1;
		}
		memo = new double[n];
		
		// base cases
		memo[0] = 1;
		memo[1] = 1;
		for(int i = 2; i < memo.length; i++){
			memo[i] = memo[i-1] + memo[i-2];
		}
		return memo[n-1];
	}
	
	public static void main(String[] args){
		Fibonacci fib = new Fibonacci();
		
		System.out.println(fib.fib(50));
		System.out.println(fib.fibTail(100));
		System.out.println(fib.fibMemo(100));
	}
}