import java.util.Random;

/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of a recursive algorithm that
	find the largest value in an array of integers
	
*/

public class LargestValue{
	public static void main(String[] args){
		Random rand = new Random();
		
		// initialize an array with a random size
		int[] arr = new int[rand.nextInt(100) + 1];
		
		// assign random values to each index
		for(int i = 0; i < arr.length; i++){
			arr[i] = rand.nextInt(1000);
		}
		
		int recMax = largestValue(arr);
		int max = max(arr);
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
		
		System.out.println("Recursion: " + recMax);
		System.out.println("Linear: " + max);
	}
	
	// find max value recursively from the beginning
	public static int largestValue(int[] arr){
		if(arr == null || arr.length == 0){
			System.err.println("The array is empty");
			return -1;
		}
		return largestValueHelper(arr, 1, arr[0]);
	}
	
	// just a linear search method for checking
	public static int max(int[] arr){
		int max = arr[0];
		for(int i = 1; i < arr.length; i++){
			if(max < arr[i]){
				max = arr[i];
			}
		}
		return max;
	}
	
	private static int largestValueHelper(int[] arr, int index, int max){
		if(index >= arr.length){
			return max;
		}else if(max < arr[index]){
			return largestValueHelper(arr, index + 1, arr[index]);
		}else{
			return largestValueHelper(arr, index + 1, max);
		}
	}
}