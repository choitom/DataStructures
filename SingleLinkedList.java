/*
	Author	: Tom Choi
	Date	: 08/08/2016
	
	Implementation of single linked list with
		- add
		- delete
		- size
		- traverse
		- clear
	methods.
*/

public class SingleLinkedList<E>{
	private Node head;
	private Node current;
	private int size;
	
	public SingleLinkedList(){
		init(null, 0);
	}
	
	public SingleLinkedList(Node n){
		init(n, 1);
	}
	
	private void init(Node head, int size){
		this.head = head;
		this.size = size;
	}
	
	public void add(Node n){
		if(head == null){
			head = n;
			current = n;
		}else{
			current.setNext(n);
			current = n;
		}size++;
	}
	
	public void delete(E item){
		Node c = head;
		Node previous = null;
		
		while(c != null){
			E v = (E)c.getItem();
			if(v.equals(item)){
				break;
			}
			previous = c;
			c = c.getNext();
		}
		
		if (c != null){
			previous.setNext(c.getNext());
			size--;
		}else{
			System.out.println("The item to delete (" + item.toString() + 
								") does not exist.");
		}
	}
	
	public int size(){
		return size;
	}
	
	public void traverse(){
		Node c = head;
		while(c != null){
			System.out.print(c.getItem() + " ");
			c = c.getNext();
		}
		System.out.println();
	}
	
	public void clear(){
		size = 0;
		head = null;
	}
	
	public static void main(String[] args){
		SingleLinkedList<String> lst = new SingleLinkedList<String>();
		
		String[] arr = {"the", "obvious", "answer", "of", "course",
						"is", "not", "to", "do", "uncheckd", "cast"};
		Node[] nodes = new Node[11];
		
		// create nodes
		for(int i = 0; i < arr.length; i++){
			nodes[i] = new Node<String>(arr[i]);
		}
		
		// add to the list
		for(int i = 0; i < nodes.length; i++){
			lst.add(nodes[i]);
		}
		
		lst.traverse();
		System.out.println(lst.size());
		
		lst.delete("off");
		lst.delete("of");
		lst.traverse();
		System.out.println(lst.size());
	}
}