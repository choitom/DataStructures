/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Heap Sort Revision
	
	One way to implement the heap sort is to
		1. build the heap in ascending order
		2. remove items from the heap and append them to a new array
		3. return the array
	However, this takes an extra O(n) space.
	
	To make it more efficiency,
		1. build the heap in descending order
		2. move swap the root and the last item
		3. reheap
	This approach sorts the heap bottom up and doesn't require extra space
*/

public class HeapSort2<E extends Comparable<E>>{
	
	/** Instance variables */
	private final int INIT_SIZE = 10;
	private E[] heap;
	private int size;
	
	/** Constructors */
	public HeapSort2(){
		init(INIT_SIZE);
	}
	
	public HeapSort2(int size){
		init(size);
	}
	
	/**
	* Sorts the heap using heap sort algorithm
	* Returns the sorted heap
	*/
	public E[] heapSort(E[] input){
		buildHeap(input);
		sortHeap();
		
		/** prevents copying 'null' items in the heap */
		E[] sorted = (E[]) new Comparable[size];
		System.arraycopy(heap, 0, sorted, 0, sorted.length);
		return sorted;
	}
	
	/**
	* Sort the heap into the descending order
	*/
	private void sortHeap(){
		int n = size;
		
		/** For each largest value */
		while(n > 0){
			
			/** Decrement the last index */
			n--;
			
			/** Move it to the last index */
			swap(0, n);
			
			/** Reheap so that the heap is in ascending order
				except for the last index
			*/
			int parent = 0;
			while(true){
				int left = 2 * parent + 1;
				if(left >= n){
					break;
				}
				int right = left + 1;
				int max = left;
				if(right < n && heap[right].compareTo(heap[left]) > 0){
					max = right;
				}
				
				if(heap[parent].compareTo(heap[max]) < 0){
					swap(parent, max);
					parent = max;
				}else{
					break;
				}
			}
		}
	}
	
	/**
	* Takes an array of items as input
	* Builds the heap in descending order
	*/
	private void buildHeap(E[] input){
		for(int i = 0; i < input.length; i++){
			add(input[i]);
			size++;
		}
	}
	
	/**
	* Add an item to the heap
	*/
	private void add(E item){
		if(size > heap.length - 1){
			reheap();
		}
		if(size == 0){
			heap[0] = item;
		}else{
			heap[size] = item;
			int child = size;
			int parent = (child - 1)/2;
			while(parent >= 0 && heap[child].compareTo(heap[parent]) > 0){
				swap(child, parent);
				child = parent;
				parent = (child - 1)/2;
			}
		}
	}
	
	/**
	* Swap two items in the array
	*/
	private void swap(int i, int j){
		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	/**
	* Double the size of the heap and move items if it is full
	*/
	private void reheap(){
		E[] old = heap;
		heap = (E[]) new Comparable[2 * heap.length];
		for(int i = 1; i < old.length; i++){
			heap[i] = old[i];
		}
	}
	
	/**
	* Initialize instance variables
	*/
	private void init(int size){
		this.heap = (E[]) new Comparable[size];
		this.size = 0;
	}
	
	public static void main(String[] args){
		String str = "Tom Choi You Can Become The World Best Googler";
		HeapSort2 heap = new HeapSort2();
		Comparable[] sorted = heap.heapSort(str.split(" "));
		for(int i = 0; i < sorted.length; i++){
			System.out.print(sorted[i] + " ");
		}
		System.out.println();
	}
}