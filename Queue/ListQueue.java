/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of Single-Linked List Queue
*/

public class ListQueue<E> implements Queue<E>{
	private static class Node<E>{
		private Node<E> next;
		private E item;
		
		private Node(E item){
			this.item = item;
			this.next = null;
		}
		
		private Node(E item, Node<E> next){
			this.item = item;
			this.next = next;
		}
	}
	
	private int size;
	private Node<E> front;
	private Node<E> rear;
	
	public ListQueue(){
		size = 0;
		front = null;
		rear = null;
	}
	
	// Inserts item at the rear of the queue
	// Returns true if successful. Ohterwise, return false
	public boolean offer(E item){
		
		// queue is empty, add to front
		if(front == null){
			front = new Node(item);
			rear = front;
		}
		// queue enot empty, add to rear
		else{
			rear.next = new Node(item);
			rear = rear.next;
		}size++;
		return true;
	}
	
	// Removes the entry at the front of the queue and return it
	// Retuns null if empty
	public E poll(){
		if(front == null){
			return null;
		}
		E polled = front.item;
		front = front.next;
		size--;
		return polled;
	}
	
	// Returns the entry at the front of the queue without removing it
	// Returns null if empty
	public E peek(){
		if(front == null){
			return null;
		}
		return front.item;
	}
	
	// Returns the size of the queue
	public int size(){
		return size;
	}
	
	public static void main(String[] args){
		ListQueue<String> q = new ListQueue<String>();
		String[] list = {"Tom", "Choi", "The", "Googler"};
		
		// add items to the queue
		for(int i = 0; i < list.length; i++){
			q.offer(list[i]);
		}
		System.out.println(q.size());
		
		while(q.size() > 0){
			System.out.print(q.poll() + " ");
		}System.out.println();
	}
}