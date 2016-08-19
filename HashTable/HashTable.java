/**
	Author	: Tom Choi
	Date	: 08/18/2016
	
	Implementation of Hash Table with
	Open Addressing and Quadratic Probing
*/

import java.util.*;

public class HashTable<K,V> implements HashTableInterface<K,V>{
	/** A pair of key and value */
	protected static class Entry<K,V>{
		protected K key;
		protected V value;
		
		public Entry(K key, V value){
			this.key = key;
			this.value = value;
		}
	}
	
	/** Instance Variables */
	protected Entry<K,V>[] table;
	protected final Entry<K,V> DELETED = new Entry<K,V>(null, null);
	private final double LOAD_LIMIT = 0.75;
	private final int INIT_SIZE = 101;
	private int numKeys;
	private int numDeletes;
	
	/** Constructor */
	public HashTable(){
		init(INIT_SIZE);
	}
	
	public HashTable(int size){
		init(size);
	}
	
	/**
	* Returns the value of a given key
	* 
	* @return	the value of a key
	*/
	public V get(K key){
		int index = find(key);
		if(table[index] == null || table[index] == DELETED){
			return null;
		}
		return table[index].value;
	}
	
	/**
	* See if no (key, value) pair is in the map or not
	*
	* @return	true if empty; otherwise, false
	*/
	public boolean isEmpty(){
		return (numKeys == 0);
	}
	
	/**
	* Print the table
	*/
	public void print(){
		int i;
		for(i = 0; i < table.length; i++){
			if(table[i] == null){
				System.out.print("null ");
			}else if(table[i] == DELETED){
				System.out.print("[DELETED] ");
			}else{
				System.out.print(table[i].value + " ");
			}
		}
		System.out.println();
	}
	
	/**
	* Add (key, value) pair to the map
	*
	* @param	key		key to add
	* @param	value	value to add
	* @return	old value if replaced; otherwise, null
	*/
	public V put(K key, V value){
		int index = find(key);
		/** the index is empty */
		if(table[index] == null){
			table[index] = new Entry<K,V>(key, value);
			numKeys++;
			
			/** key added -> check load limit */
			double loadFactor = (double)(numKeys + numDeletes)/table.length;
			if(loadFactor > LOAD_LIMIT){
				rehash();
			}
			return null;
		}
		/** the index is occupied */
		V old = table[index].value;
		table[index].value = value;
		return old;
	}
	
	/**
	* Remove a pair with a given key
	*
	* @param	key		key of a pair to remove
	* @return	removed value; otherwise, null
	*/
	public V remove(K key){
		int index = find(key);
		if(table[index] == null || table[index] == DELETED){
			return null;
		}
		V removed = table[index].value;
		table[index] = DELETED;
		numKeys--;
		numDeletes++;
		return removed;
	}
	
	/**
	* The number of pairs in the map
	*
	* @return	the size of the map
	*/
	public int size(){
		return numKeys;
	}
	
	
	/**
	* Initialize instance variables
	*
	* @param	size	the initial size of the table
	*/
	private void init(int size){
		numKeys = 0;
		numDeletes = 0;
		table = new Entry[size];
	}
	
	/**
	* Finds the index of a given key in the hash table
	*
	* @param	key		key to convert
	* @return	the index of the key
	*/
	private int find(K key){
		int index = hashCode(key) % table.length;
		if(index < 0){
			index += table.length;
		}
		
		/** resolve collision */
		
		int quadratic = 1;
		while((table[index] != null) &&
			 (!key.equals(table[index].key))){
			//System.out.println("while" + index);
			index = (index + quadratic * quadratic) % table.length;
			if(index < 0){
				index += table.length;
			}
			quadratic++;
		}
		return index;
	}
	
	/**
	* Converts a key to a hash code using the following hash function
	* hash code = 31^(n-1)*c1 + 31^(n-2)*c2 + ... where key = c1c2c3...
	*
	* @param	key		key to convert
	* @return	hash code
	*/
	private int hashCode(K key){
		String keyStr = String.valueOf(key);
		char[] charArr = keyStr.toCharArray();
		int code = 0;
		for(int i = 0; i < charArr.length; i++){
			code += Math.pow(31, i) * (int)charArr[i];
		}
		return code;
	}

	/**
	* Double the size and move items over to the new table
	*/
	private void rehash(){
		numKeys = 0;
		numDeletes = 0;
		Entry<K,V>[] old = table;
		table = new Entry[table.length * 2 + 1];
		
		for(int i = 0; i < old.length; i++){
			if(old[i] != null && old[i] != DELETED){
				put(old[i].key, old[i].value);
			}
		}
	}
	
	
	/** Test code */
	public static void main(String[] args){
		HashTable<Integer, Integer> table = new HashTable<Integer, Integer>();
		Random rand = new Random();
		int randSize = rand.nextInt(20) + 1;
		
		int[] randomKeys = new int[randSize];
		int[] randomValues = new int[randSize];
		
		for(int i = 0; i < randomKeys.length; i++){
			randomKeys[i] = rand.nextInt(1000);
		}
		
		for(int i = 0; i < randomValues.length; i++){
			randomValues[i] = rand.nextInt(100);
		}
		
		for(int i = 0; i < randomKeys.length; i++){
			table.put(randomKeys[i], randomValues[i]);
		}
		
		table.print();
		System.out.println("Size: " + table.size());
		System.out.println("Is empty? " + table.isEmpty());
		
		for(int i = 0; i < randomKeys.length; i++){
			table.remove(randomKeys[i]);
		}
		table.print();
	}
}