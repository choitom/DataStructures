/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Quick Sort
	
*/

public class QuickSort<T extends Comparable<T>>{
	private T[] arr;
	
	public QuickSort(T[] arr){
		this.arr = arr;
	}
	
	public void quickSort(){
		quickSortHelper(0, arr.length-1);
	}
	
	/**
	* Recursively sorts the lower half and upper half of the array
	*/
	public void quickSortHelper(int low, int high){
		if(low < high){
			int p = partition(low, high);
			quickSortHelper(low, p-1);
			quickSortHelper(p+1, high);
		}
	}
	
	/**
	* Prints out an array
	*/
	public void print(){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
	
	/**
	* Move items less than the pivot to the left
	* Move items greater than the pivot to the right
	* Then, move the pivot right in the middle of those two halves
	*/
	private int partition(int low, int high){
		T pivot = arr[low];
		int wall = low + 1;
		int comparison;
		int i;
		
		for(i = low + 1; i <= high; i++){
			comparison = pivot.compareTo(arr[i]);
			
			// pivot > item: move to left of the wall
			if(comparison > 0){
				swap(i, wall);
				wall++;
			}
		}
		swap(--wall, low);
		return wall;
	}
	
	/**
	* Swap two items in given indices in an array
	*/
	private void swap(int i, int j){
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/** Test code */
	public static void main(String[] args){
		String str = "Tom Choi Is An Awesome Googler";
		String[] arr = str.split(" ");
		QuickSort<String> qs = new QuickSort<String>(arr);
		qs.quickSort();
		qs.print();
	}
}