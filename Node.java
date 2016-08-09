public class Node<E>{
	private Node next;
	private E item;
	
	public Node(){
		this.next = null;
	}
	
	public Node(E item){
		this.item = item;
		this.next = null;
	}
	
	public void setItem(E item){
		this.item = item;
	}
	
	public void setNext(Node next){
		this.next = next;
	}
	
	public E getItem(){
		return this.item;
	}
	
	public Node getNext(){
		return this.next;
	}
}