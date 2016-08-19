/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Bubble Sort
	
	Note
		I acknowledge that Bubble Sort is the most inefficient algorithm
		But, I have to know it very well in order not to resort this sorting algorithm
*/

public class BubbleSort{
	
	/**
	* Sorts an array using the bubble sort algorithm
	*/
	public static Comparable[] bubbleSort(Comparable[] arr){
		boolean sorted = true;
		
		while(sorted){
			sorted = false;
			for(int i = 0; i < arr.length - 1; i++){
				if(arr[i].compareTo(arr[i+1]) > 0){
					sorted = true;
					Comparable temp = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
				}
			}
		}
		return arr;
	}
	
	/** Test code */
	public static void main(String[] args){
		Comparable[] arr = {10,9,8,7,6,5,4,3,2,1,0};
		arr = bubbleSort(arr);
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}