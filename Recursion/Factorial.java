/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Simple recursion exercise
	(factorial and its tail recursion implementaion)
*/

public class Factorial{
	public int factorial(int n){
		// 0! = 1, 1! = 1
		if(n == 0 || n == 1){
			return 1;
		}else{
			return n * factorial(n - 1);
		}
	}
	
	public int factorialTail(int n, int i){
		if(n == 0 || n == 1){
			return i;
		}else{
			return factorialTail(n-1, n * i);
		}
	}
	
	public static void main(String[] args){
		Factorial f = new Factorial();
		
		int f1 = f.factorial(10);
		int f2 = f.factorialTail(11, 1);
		
		System.out.println(f1);
		System.out.println(f2);
	}
}