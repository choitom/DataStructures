/*
	Author	: Tom Choi
	Date	: 08/09/2016
	
	Implementation of Double Linked List
		- add(E item): void
		- add(int index, E item): void
		- clear(): void
		- printForward(): void
		- printBackward(): void
		- remove(int index): E
		- size(): int
*/

public class DoubleLinkedList<E>{
	private static class Node<E>{
		private E data;
		private Node<E> next = null;
		private Node<E> previous = null;
		
		private Node(E data){
			this.data = data;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public DoubleLinkedList(){
		init(null);
	}
	
	public DoubleLinkedList(Node<E> h){
		init(h);
	}
	
	public void add(E item){
		add(size, item);
	}
	
	public void add(int index, E item){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(size == 0){
			addFirst(item);
		}else if(index == size){
			addLast(item);
		}else{
			addAfter(index, item);
		}
	}
	
	public E remove(int index){
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> n = head;
		int i = 0;
		while(i != index){
			n = n.next;
			i++;
		}
		n.previous.next = n.next;
		n.next.previous = n.previous;
		return n.data;
	}
	
	// print the array (beg -> end)
	public void printForward(){
		Node<E> current = head;
		while(current != null){
			System.out.print(current.data + " ");
			current = current.next;
		}System.out.println();
	}
	
	// print the array (end -> beg)
	public void printBackward(){
		Node<E> current = tail;
		while(current != null){
			System.out.print(current.data + " ");
			current = current.previous;
		}System.out.println();
	}
	
	// clear the array
	public void clear(){
		init(null);
	}
	
	// get the size of the array
	public int size(){
		return size;
	}	
	
	private void addFirst(E item){
		Node<E> n = new Node<E>(item);
		head = n;
		tail = n;
		size++;
	}
	
	private void addLast(E item){
		Node<E> n = new Node<E>(item);
		tail.next = n;
		n.previous = tail;
		tail = n;
		size++;
	}
	
	private void addAfter(int index, E item){
		Node<E> n = head;
		int i = 0;
		while(i != index - 1){
			n = n.next;
			i++;
		}
		Node<E> newNode = new Node<E>(item);
		newNode.previous = n;
		newNode.next = n.next;
		n.next.previous = newNode;
		n.next = newNode;
		size++;
	}
	
	private void init(Node<E> h){
		head = h;
		size = 0;
	}
	
	public static void main(String[] args){
		DoubleLinkedList<String> lst = new DoubleLinkedList<String>();
		String[] str = {"Tom", "Choi", "The", "Googler"};
		for(int i = 0; i < str.length; i++){
			lst.add(str[i]);
		}
		lst.printForward();	// Tom Choi The Googler
		lst.printBackward(); // Googler The Choi Tom
		lst.add(1, "Sun");
		lst.add(2, "Hyun");
		lst.printForward(); // Tom Sun Hyun Choi The Goolger
		lst.printBackward(); // Googler The Choi Hyun Sun Tom
		System.out.println(lst.size()); // 6
		
		lst.remove(1);
		lst.remove(1);
		lst.printForward(); // Tom Choi The Googler
		lst.printBackward(); // Googler The Choi Tom
	}
}