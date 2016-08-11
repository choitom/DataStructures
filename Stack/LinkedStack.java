public class LinkedStack<E> implements Stack<E>{
	private static class Node<E>{
		private Node<E> next = null;;
		private E item;
		
		private Node(E item){
			this.item = item;
		}
	}
	
	private Node<E> top;
	
	public LinkedStack(){
		top = null;
	}
	
	// returns if the stack is empty or not
	public boolean empty(){
		return (top == null) ? true : false;
	}
	
	// returns the top item of the stack
	public E peek(){
		if (top == null){
			System.err.println("The stack is empty.");
			return null;
		}
		return top.item;
	}
	
	// removes and returns the top item of the stack
	public E pop(){
		Node<E> popped = top;
		top = top.next;
		return popped.item;
	}
	
	// add an item on the top of the stack
	public E push(E obj){
		Node<E> newNode = new Node<E>(obj);
		if(top == null){
			top = newNode;
		}else{
			newNode.next = top;
			top = newNode;
		}
		return newNode.item;
	}
	
	public static void main(String[] args){
		LinkedStack<String> stack = new LinkedStack<String>();
		String[] str = {"Googler", "The", "Choi", "Tom"};
		for(int i = 0; i < str.length; i++){
			System.out.print(stack.push(str[i]) + " ");
		}
		System.out.println("\nPeek: " + stack.peek());
		for(int i = 0; i < str.length; i++){
			System.out.print(stack.pop() + " ");
		}
		System.out.println("\nEmpty: " + stack.empty());
		
	}
}