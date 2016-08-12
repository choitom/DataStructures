/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of Binary Search
	
*/

public class BinarySearch{
	
	public static int search(Object[] items, Comparable target){
		return binarySearch(items, target, 0, items.length-1);
	}
	
	private static int binarySearch(Object[] items, Comparable target, int low, int high){
		if(items == null || items.length == 0){
			System.err.println("The list is empty");
			return -1;
		}else if(low > high){
			System.err.println("The item \'" + target.toString() + "\' is not found");
			return -1;
		}else{
			int mid = (low + high) / 2;
			int compResult = target.compareTo(items[mid]);
			if(compResult == 0){
				return mid;
			}else if(compResult < 0){
				return binarySearch(items, target, low, mid-1);
			}else{
				return binarySearch(items, target, mid+1, high);
			}
		}
	}
	
	public static void main(String[] args){
		String[] arr = {"Caryn", "Debbie", "Dustin", "Elliot", "Jacquie", "Jonathan", "Rich"};
		System.out.println(search(arr, "Caryn"));
		System.out.println(search(arr, "Tom"));
	}
}