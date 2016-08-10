import java.util.Arrays;

/*
	Author	: Tom Choi
	Date	: 08/09/2016
	
	Implementation of Java ArrayList
		- add(E item): boolean
		- add(int insert, E item): void
		- get(int index): E
		- print(): void
		- remove(int index): E
		- set(int index, E newItem): E
		- size(): int
		- clear(): void
		- indexOf(E item): int
*/

public class ArrayList<E>{
	private final int INITIAL_CAPACITY = 10;
	private int size;
	private int capacity = 0;
	private E[] list;
	
	public ArrayList(){
		init(INITIAL_CAPACITY);
	}
	
	public ArrayList(int capacity){
		init(capacity);
	}
	
	/*
		Add an item to the list
		Return true if added
	*/
	public boolean add(E item){
		if(size >= capacity){
			ensureCapacity();
		}
		list[size++] = item;
		return true;
	}
	
	/*
		Insert an item into the list
	*/
	public void add(int insert, E item){
		if(insert < 0 || insert > size){
			throw new ArrayIndexOutOfBoundsException(insert);
		}
		
		if(size >= capacity){
			ensureCapacity();
		}
		
		for(int i = size-1; i > insert; i--){
			list[i] = list[i-1];
		}
		list[insert] = item;
		size++;
	}
	
	/*
		Remove an item and return it
	*/
	public E remove(int index){
		if(index < 0 || index >= size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E removed = list[index];
		
		for(int i = index + 1; i < size; i++){
			list[i-1] = list[i];
		}
		list[size-1] = null;
		size--;
		return removed;
	}
	
	/*
		Getter and setter
	*/
	public E get(int index){
		if(index < 0 || index >= size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return list[index];
	}
	
	public E set(int index, E newItem){
		if(index < 0 || index >= size){
			throw new ArrayIndexOutOfBoundsException(index);
		}
		E old = list[index];
		list[index] = newItem;
		return old;
	}
	
	/*
		Returns the index of an item
		If the item does not exist, it returns -1
	*/
	public int indexOf(E item){
		int index = -1;
		for(int i = 0; i < size; i++){
			if(item.equals(list[i])){
				index = i;
				break;
			}
		}
		return index;
	}
	
	/*
		Return the size of the list
	*/
	public int size(){
		return size;
	}
	
	/*
		Print the list
	*/
	public void print(){
		for(int i = 0; i < size; i++){
			System.out.print(list[i] + " ");
		}System.out.println();
	}
	
	/*
		Clear the list
	*/
	public void clear(){
		init(INITIAL_CAPACITY);
	}
	
	
	/*
		Double the size of the list
	*/
	private void ensureCapacity(){
		capacity = 2 * capacity;
		list = Arrays.copyOf(list, capacity);
	}

	private void init(int capacity){
		size = 0;
		capacity = capacity;
		list = (E[]) new Object[capacity];
	}
	
	public static void main(String[] args){
		ArrayList<Integer> lst = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++){
			lst.add(i);
		}
		lst.print();
		System.out.println(lst.size());
		
		lst.remove(5);
		lst.print();
		
		for(int i = 100; i < 110; i++){
			lst.add(i);
		}
		
		System.out.println("The index of 105 is: " + lst.indexOf(105));
		lst.print();
	}
}