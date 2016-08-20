/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Heap & Heap Sort
	-> It removes items in ascending order
	   from the heap and add them to an array
*/

public class Heap<E extends Comparable<E>>{
	private E[] heap;
	private int size;
	private final int INIT_SIZE = 10;
	
	public Heap(){
		init(INIT_SIZE);
	}
	
	public Heap(int size){
		init(size);
	}
	
	/**
	* Add an item to the heap
	*/
	public void add(E item){
		if(size > heap.length-1){
			ensureCapacity();
		}
		if(size == 0){
			heap[0] = item;
		}else{
			heap[size] = item;
			heapUp();
		}
		size++;
	}
	
	/**
	* Remove an item from the heap
	*/
	public E remove(){
		if(size == 0){
			return null;
		}
		E removed = heap[0];
		heap[0] = heap[--size];
		heapDown();
		return removed;
	}
	
	/**
	* Print the heap
	*/
	public void print(){
		for(int i = 0; i < size; i++){
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	}
	
	/**
	* Return the size of the heap
	*/
	public int size(){
		return size;
	}
	
	/**
	* Remove items in ascending order and append them to an array
	*/
	public E[] heapSort(){
		E[] sorted = (E[]) new Comparable[size];
		int i = 0;
		while(size > 0){
			sorted[i++] = remove();
		}
		return sorted;
	}
	
	/**
	* Double the size of the heap if it is full
	*/
	private void ensureCapacity(){
		E[] old = heap;
		heap = (E[]) new Comparable[heap.length * 2];
		for(int i = 0; i < old.length; i++){
			heap[i] = old[i];
		}
	}
	
	/**
	* Reheap after an item removed
	*/
	public void heapDown(){
		int parent = 0;
		while(true){
			int left = 2 * parent + 1;
			int right = left + 1;
			if(left > size){
				break;
			}
			int min = left;
			if(right <= size){
				int comparison = heap[left].compareTo(heap[right]);
				if(comparison > 0){
					min = right;
				}
			}
			swap(parent, min);
			parent = min;
		}
	}
	
	/**
	* Reheap after an item added
	*/
	private void heapUp(){
		int child = size;
		int parent = (child - 1)/2;
		while(parent >= 0 && heap[child].compareTo(heap[parent]) < 0){
			swap(child, parent);
			child = parent;
			parent = (child - 1)/2;
		}
	}
	
	/**
	* Swap items in two indices in an array
	*/
	private void swap(int i, int j){
		E temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
	/**
	* Initialize instance variables
	*/
	private void init(int size){
		heap = (E[]) new Comparable[size];
		size = 0;
	}
	
	
	/** Test code */
	public static void main(String[] args){
		String[] arr = {"Tom", "Choi", "Is", "An", "Awesome", "Googler", "Believe", "Yourself", "That", "You", "Can", "Do", "It"};
		Heap<String> h = new Heap<String>();
		
		for(int i = 0; i < arr.length; i++){
			h.add(arr[i]);
		}
		
		Comparable[] sorted = h.heapSort();
		for(int i = 0; i < sorted.length; i++){
			System.out.print(sorted[i] + " ");
		}System.out.println();
	}
}