/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Insertion Sort
*/

public class InsertionSort{
	
	/**
	* Sorts an array using the insertion sort algorithm
	*/
	public static <T extends Comparable<T>> T[] insertionSort(T[] arr){
		T toSort = null;
		for(int i = 1; i < arr.length; i++){
			for(int j = i; j > 0; j--){
				if(arr[j].compareTo(arr[j-1]) > 0){
					break;
				}else{
					swap(arr, j, j-1);
				}
			}
		}
		return arr;
	}
	
	/**
	* Swaps two items in given index of an array
	*/
	private static <T extends Comparable<T>> T[] swap(T[] arr, int i, int j){
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
	
	public static void main(String[] args){
		String[] arr = new String[]{"Tom", "Choi", "Is", "An", "Awesome", "Googler"};
		arr = insertionSort(arr);
		for(int i = 0 ; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
}