/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Selection Sort
*/

public class SelectionSort{
	
	/**
	* Sorts the array using selection sort algorithm
	*
	* @param	arr		array to be sorted
	* @return	array sorted
	*/
	public static Comparable[] sort(Comparable[] arr){
		Comparable min;
		int minIndex;
		for(int i = 0; i < arr.length - 1; i++){
			min = arr[i];
			minIndex = i;
			for(int j = i+1; j < arr.length; j++){
				if(min.compareTo(arr[j]) > 0){
					min = arr[j];
					minIndex = j;
				}
			}
			// swap the minimum item
			if(i != minIndex){
				Comparable temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
		return arr;
	}
	
	/** Test Code */
	public static void main(String[] args){
		Comparable[] arr = {6,5,4,3,2};
		arr = sort(arr);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
}