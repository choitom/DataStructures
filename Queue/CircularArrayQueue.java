/*

	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of Queue using a circular array
	
	Reason using a circular array
		-	In linked list, each node contains references to
			its predecessor and successor which takes memory space
		-	Dequeue in an array has to re-buffer for every dequeue operation
		-	By joining the front and rear ends of a dequeue,
			solve the re-buffering issue
*/

public class CircularArrayQueue<E> implements Queue<E>{
	private final int INITIAL_CAPACITY = 10;
	private int size;
	private int front;
	private int rear;
	private int capacity;
	private E[] arr;
	
	public CircularArrayQueue(){
		init(INITIAL_CAPACITY);
	}
	
	public CircularArrayQueue(int capacity){
		init(capacity);
	}
	
	
	// Inserts item at the rear of the queue
	// Returns true if successful. Ohterwise, return false
	public boolean offer(E item){
		if(size >= capacity){
			ensureCapacity();
		}
		rear = (rear + 1) % capacity;
		arr[rear] = item;
		size++;
		return true;
	}
	
	// Removes the entry at the front of the queue and return it
	// Retuns null if empty
	public E poll(){
		if(size == 0){
			System.err.println("The queue is empty");
			return null;
		}
		E polled = arr[front];
		front = (front + 1) % capacity;
		size--;
		return polled;
	}
	
	// Returns the entry at the front of the queue without removing it
	// Returns null if empty
	public E peek(){
		if(size == 0){
			System.err.println("The queue is empty");
			return null;
		}
		return arr[front];
	}
	
	// Returns the size of the queue
	public int size(){
		return size;
	}
	
	// Clear the whole list
	public void clear(){
		init(INITIAL_CAPACITY);
	}
	
	private void ensureCapacity(){
		capacity = 2 * capacity;
		E[] temp = (E[]) new Object[capacity];
		int j = front;
		for(int i = 0; i < size; i++){
			temp[i] = arr[j];
			j = (j + 1) % capacity;
		}
		front = 0;
		arr = temp;
	}
	
	private void init(int capacity){
		this.capacity = capacity;
		size = 0;
		front = 0;
		rear = capacity - 1;
		arr = (E[]) new Object[capacity];
	}
	
	public static void main(String[] args){
		CircularArrayQueue<String> q = new CircularArrayQueue<String>();
		
		String[] tom = {"Tom", "Choi", "The", "Googler"};
		
		for(int i = 0; i < tom.length; i++){
			q.offer(tom[i]);
		}
		
		System.out.println("Size: " + q.size());
		System.out.println("Peek: " + q.peek());
		
		while(q.size() > 0){
			System.out.print(q.poll() + " ");
		}
		System.out.println("\n" + q.size());
		q.clear();
	}
}