/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of a recursive sum programming exercise problem in p.267
	
	I implemented using
		- normal recursion
		- tail recursion
*/

public class RecursiveSum{
	public static void main(String[] args){
		
		// initialize an array of integers
		int[] intArr = new int[100];
		for(int i = 0; i < 100; i++){
			intArr[i] = i + 1;
		}
		
		// use tail recursion
		int tSum = tailSum(intArr);
		System.out.println("Tail recursion sum: " + tSum);
		
		// use normal recursion
		int nSum = sum(intArr);
		System.out.println("Normal recursion sum: " + nSum);
	}
	
	// Uses tail recursion to find the recursive sum
	public static int tailSum(int[] arr){
		if(arr.length == 0){
			System.err.println("The array is empty");
			return -1;
		}
		return findTailSum(arr, 0, 0);
	}
	
	// Normal recursion
	public static int sum(int[] arr){
		if(arr.length == 0){
			System.err.println("The array is empty");
			return -1;
		}
		return findSum(arr, 0);
	}
	
	private static int findTailSum(int[] arr, int partialSum, int index){
		if(index == arr.length){
			return partialSum;
		}else{
			return findTailSum(arr, partialSum + arr[index], ++index);
		}
	}
	
	private static int findSum(int[] arr, int index){
		if(index == arr.length){
			return 0;
		}else{
			return arr[index] + findSum(arr, ++index);
		}
	}
}