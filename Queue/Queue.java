public interface Queue<E>{
	
	// Inserts item at the rear of the queue
	// Returns true if successful. Ohterwise, return false
	public boolean offer(E item);
	
	// Removes the entry at the front of the queue and return it
	// Retuns null if empty
	public E poll();
	
	// Returns the entry at the front of the queue without removing it
	// Returns null if empty
	public E peek();
}