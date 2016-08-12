/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of the recursive linear search that find
	the last occurrence of a target in an array
*/

public class ReverseLinearSearch{
	public static void main(String[] args){
		String[] arr = new String[100];
		arr[0] = "A";
		for(int i = 1; i < arr.length/2; i++){
			arr[i] = arr[i-1] + "A";
		}
		for(int i = arr.length/2; i < arr.length; i++){
			arr[i] = arr[i - arr.length/2];
		}
		String target = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		
		int lastIndex = reverseSearch(arr, target);
		System.out.println(lastIndex);
	}
	
	public static int reverseSearch(Object[] arr, Comparable target){
		if(arr == null || arr.length == 0){
			System.err.println("The array is empty");
			return -1;
		}
		return reverseSearchHelper(arr, target, arr.length);
	}
	
	private static int reverseSearchHelper(Object[] arr, Comparable target, int length){
		// base case
		//	1. target not found
		//	2. target found
		if(length < 1){
			System.err.println("The item does not exists in the array");
			return -1;
		}
		else if(target.compareTo(arr[length-1]) == 0){
			return length-1;
		}
		
		// recursive case
		else{
			return reverseSearchHelper(arr, target, length - 1);
		}
	}
}