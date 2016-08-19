/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Merge Sort
*/

public class MergeSort{
	private static String[] arr;
	
	/**
	* Sort left and right half of an array and merge them back together
	*/
	public static <T extends Comparable<T>> void mergeSort(T[] arr){
		if(arr.length > 1){
			T[] l = leftHalf(arr);
			T[] r = rightHalf(arr);
			
			mergeSort(l);
			mergeSort(r);
			merge(arr, l, r);
		}
	}
	
	/**
	* Combines left and right arrays together
	*/
	private static <T extends Comparable<T>> void merge(T[] arr, T[] l, T[] r){
		int left = 0;
		int right = 0;
		int result = 0;
		while(left < l.length && right < r.length){
			int comparison = l[left].compareTo(r[right]);
			if(comparison < 0){
				arr[result] = l[left++];
			}else{
				arr[result] = r[right++];
			}
			result++;
		}
		while(left < l.length){
			arr[result++] = l[left++];
		}
		while(right < r.length){
			arr[result++] = r[right++];
		}
	}
	
	/**
	* Get the left half of an array
	*/
	private static <T extends Comparable<T>> T[] leftHalf(T[] arr){
		int size = arr.length / 2;
		T[] l = (T[]) new Comparable[size];
		for(int i = 0; i < l.length; i++){
			l[i] = arr[i];
		}
		return l;
	}
	
	/**
	* Get the right half of an array
	*/
	private static <T extends Comparable<T>> T[] rightHalf(T[] arr){
		int size = arr.length - arr.length/2;
		T[] r = (T[]) new Comparable[size];
		for(int i = 0; i < r.length; i++){
			r[i] = arr[i + arr.length/2];
		}
		return r;
	}
	
	/** Test Code */
	public static void main(String[] args){
		String str = "Tom Is An Awesome Googler";
		arr = str.split(" ");
		mergeSort(arr);
		
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
}