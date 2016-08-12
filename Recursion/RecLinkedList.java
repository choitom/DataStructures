/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of Recursive Linked List
		- add(E item): void
		- replace(E old, E new): void
		- traverse(): void
		- size(): int
		- remove(E item): boolean
		
	Although recursion is not efficient, it is a worthwhile exrcise
	
*/

public class RecLinkedList<E>{
	private static class Node<E>{
		private Node<E> next = null;
		private E item;
		
		private Node(E item){
			this.item = item;
		}
	}
	
	private Node<E> head;
	
	public RecLinkedList(){
		this.head = null;
	}
	
	// recursively finds the size of the list
	public int size(){
		if(head == null){
			return 0;
		}
		return recSize(head, 0);
	}
	
	// recursively adds a node to the list
	public void add(E item){
		if(head == null){
			head = new Node<E>(item);
		}else{
			recAdd(head, item);
		}
	}
	
	// recursively traverse the list
	public void traverse(){
		if(head == null){
			System.err.println("The list is empty");
		}
		recTraverse(head);
	}
	
	// replace an item recursively
	public void replace(E o, E n){
		if(head == null){
			System.err.println("The list is empty");
			return;
		}
		recReplace(head, o, n);
	}
	
	// remove an item recursively
	public boolean remove(E item){
		if(head == null){
			System.err.println("The list is empty");
			return false;
		}
		return recRemove(head, null, item);
	}
	
	private boolean recRemove(Node<E> current, Node<E> previous, E item){
		if(current == null){
			System.err.println("The item to remove does not exist");
			return false;
		}else if(current.item.equals(item)){
			previous.next = current.next;
			return true;
		}else{
			return recRemove(current.next, current, item);
		}
	}
	
	private void recReplace(Node<E> current, E o, E n){
		if(current == null){
			System.err.println("The item to replace does not exist");
			return;
		}else if(current.item.equals(o)){
			current.item = n;
		}else{
			recReplace(current.next, o, n);
		}
	}
	
	private void recTraverse(Node<E> current){
		if(current == null){
			System.out.println();
		}else{
			System.out.print(current.item + " ");
			recTraverse(current.next);
		}
	}
	
	private void recAdd(Node<E> current, E item){
		if(current.next == null){
			current.next = new Node<E>(item);
		}else{
			recAdd(current.next, item);
		}
	}
	
	private int recSize(Node<E> current, int size){
		if(current == null){
			return size;
		}else{
			return recSize(current.next, 1 + size);
		}
	}
	
	public static void main(String[] args){
		RecLinkedList<Integer> lst = new RecLinkedList<Integer>();
		for(int i = 0; i < 10; i++){
			lst.add(i);
		}
		System.out.println(lst.size());
		lst.traverse();
		lst.replace(9, 100);
		lst.traverse();
		lst.remove(8);
		lst.traverse();
		lst.remove(999);
	}
}