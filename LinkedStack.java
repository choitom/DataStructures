import java.util.EmptyStackException;

/*
	Author	: Tom Choi
	Date	: 08/09/2016
	
	Implementation of Linked Stack
		- empty(): boolean
		- peek(): E
		- pop(): E
		- push(E obj): E
*/

public class LinkedStack<E> implements Stack<E>{
	private static class Node<E>{
		private E data;
		private Node<E> next = null;
		private Node<E> previous = null;
		
		private Node(E data){
			this.data = data;
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
		if(empty()){
			throw new EmptyStackException();
		}
		return top.data;
	}
	
	// removes and returns the top item of the stack
	public E pop(){
		if(empty()){
			throw new EmptyStackException();
		}
		E popped = top.data;
		if(top.previous != null){
			top.previous.next = null;
		}
		top = top.previous;
		return popped;
	}
	
	// add an item on the top of the stack
	public E push(E obj){
		Node<E> n = new Node<E>(obj);
		if(top == null){
			top = n;
		}else{
			n.previous = top;
			top.next = n;
			top = n;
		}
		return obj;
	}
	
	public static void main(String[] args){
		LinkedStack<Integer> lst = new LinkedStack<Integer>();
		for(int i = 0; i < 10; i++){
			lst.push(i);
		}
		System.out.println(lst.empty());
		System.out.println(lst.peek());
		for(int i = 0; i < 10; i++){
			System.out.print(lst.pop() + " ");
		}
		System.out.println("\n"+ lst.empty());
	}
}