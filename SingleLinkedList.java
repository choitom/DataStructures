/*
	Author	: Tom Choi
	Date	: 08/09/2016
	
	Implementation of Single Linked List
		- add(E item): void
		- add(int index, E item): void
		- clear(): void
		- get(int index): E
		- remove(int index): E
		- set(int index, E item): void
		- size(): int
		- traverse(): void
*/

public class SingleLinkedList<E>{
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
	
	// variables
	private Node<E> head;
	private int size;
	
	public SingleLinkedList(){
		init(null);
	}
	
	public SingleLinkedList(Node<E> h){
		init(h);
	}
	
	// add an item to the list
	public void add(E item){
		add(size, item);
	}
	
	// insert an item to a certain index
	public void add(int index, E item){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(size == 0){
			addFirst(item);
		}else{
			Node<E> n = getNode(index-1);
			addAfter(n, item);
		}
	}
	
	// remove an item in a certain index
	public E remove(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> current = head;
		Node<E> previous = null;
		Node<E> removed = getNode(index);
		int i = 0;
		while(i != index){
			previous = current;
			current = current.next;
			i++;
		}
		previous.next = current.next;
		size--;
		return removed.item;
	}
	
	// getter and setter
	public E get(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		return getNode(index).item;
	}
	
	public void set(int index, E item){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		getNode(index).item = item;
	}
	
	// get the size of the list
	public int size(){
		return size;
	}
	
	// clear the list
	public void clear(){
		init(null);
	}
	
	// print the list
	public void traverse(){
		Node<E> n = head;
		while(n != null){
			System.out.print(n.item + " ");
			n = n.next;
		}System.out.println();
	}
	
	private void addAfter(Node<E> n, E item){
		Node<E> newNode = new Node<E>(item, n.next);
		n.next = newNode;
		size++;
	}
	
	private void addFirst(E item){
		head = new Node<E>(item, head);
		size++;
	}
	
	private Node<E> getNode(int index){
		int i = 0;
		Node<E> n = head;
		while(i != index){
			n = n.next;
			i++;
		}
		return n;
	}
	
	private void init(Node<E> h){
		size = 0;
		head = h;
	}
	
	public static void main(String[] args){
		SingleLinkedList<String> lst = new SingleLinkedList<String>();
		
		String[] s = {"Tom", "Choi", "The", "Googler"};
		for(int i = 0; i < s.length; i++){
			lst.add(s[i]);
		}
		lst.traverse(); // Tom Choi The Googler
		
		lst.add(1, "Sun");
		lst.add(2, "Hyun");
		lst.traverse();	// Tom Sun Hyun Choi The Googler
		
		lst.remove(1);
		lst.remove(1);
		lst.traverse(); // Tom Choi The Googler
	}
}