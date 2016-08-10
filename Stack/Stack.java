public interface Stack<E>{
	// returns if the stack is empty or not
	public boolean empty();
	
	// returns the top item of the stack
	public E peek();
	
	// removes and returns the top item of the stack
	public E pop();
	
	// add an item on the top of the stack
	public E push(E obj);
}