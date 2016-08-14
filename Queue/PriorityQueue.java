/**
	Author	: Tom Choi
	Date	: 08/13/2016
	
	Implementation of Priority Queue using heap
		- queue			: ArrayList<E>
		+ offer(E item) : boolean
		+ remove()		: E
		+ peek()		: E
*/

import java.util.ArrayList;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{
	
	/** ArrayList used for implementing a queue */
	private ArrayList<E> queue;
	
	/** Constructors initializing a queue */
	public PriorityQueue(){
		queue = new ArrayList<E>();
	}
	
	public PriorityQueue(E root){
		queue = new ArrayList<E>();
		queue.add(root);
	}
	
	
	/**
	* Insert an item to the priority queue
	*
	* @param	item	item to insert
	* @return	true if inserted
	*/
	public boolean offer(E item){
		queue.add(item);
		
		/** the index of the child just added */
		int child = queue.size() - 1;
		
		/** the index of its parent */
		int parent = (child - 1) / 2;
		
		/** reheap */
		while(parent >=0 && queue.get(parent).compareTo(queue.get(child)) > 0){
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}
		return true;
	}
	
	/**
	* Swaps two items in the queue
	*
	* @param	parent	the index of the parent node
	* @param	child	the index of the child node
	*/
	private void swap(int parent, int child){
		E temp = queue.get(parent);
		queue.set(parent, queue.get(child));
		queue.set(child, temp);
	}
	
	/**
	* Remove an item from the priority queue
	*
	* @return	the item with the smallest prioirty value or null if empty
	*/
	public E poll(){
		/** the queue is empty */
		if(queue.size() == 0){
			System.err.println("The queue is empty");
			return null;
		}
		
		E removed = queue.get(0);
		
		/** if only one item, then remove it */
		if(queue.size() == 1){
			queue.remove(0);
		}else{
			/** move the last item to the root */
			queue.set(0, queue.remove(queue.size() - 1));
			int parent = 0;
			
			/** reheap */
			while(true){
				int left = 2 * parent + 1;
				int right = left + 1;
				
				/** break if the left child index out of bound */
				if(left > queue.size() - 1){
					break;
				}
				
				/** find the minimum child */
				int minChild = left;
				if(right < queue.size() &&
				   queue.get(right).compareTo(queue.get(left)) < 0){
					minChild = right;
				}
				
				/** swap with parent if parent > minChild */
				if(queue.get(parent).compareTo(queue.get(minChild)) > 0){
					swap(parent, minChild);
					parent = minChild;
				}else{
					break;
				}
			}
		}
		return removed;
	}
	
	/**
	* Return the item with the highest priority in the queue
	*
	* @return	the item with the smallest value in the priority queue
	*/
	public E peek(){
		return queue.get(0);
	}
	
	
	public static void main(String[] args){
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		
		for(int i = 10; i >= 0; i--){
			q.offer(i);
		}
		
		for(int i = 0; i < 5; i++){
			System.out.println(q.poll());
		}
		
		PriorityQueue<String> q2 = new PriorityQueue<String>();
		
		String[] arr = {"this", "is", "the", "house", "that", "jack", "built"};
		for(int i = 0; i < arr.length; i++){
			q2.offer(arr[i]);
		}
		
		for(int i = 0; i < arr.length; i++){
			System.out.println(q2.poll());
		}
	}
}