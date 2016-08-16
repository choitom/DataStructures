/**
	Author	: Tom Choi
	Date	: 08/15/2016
	
	Implementation of Hash Table with
	Open Addressing and Quadratic Probing
		- DELETED				: Entry<K,V>
		- INIT_SIZE				: int
		- LOAD_LIMIT			: double
		- numKeys				: int
		- numDeletes			: int
		- table					: Entry<K,V>[]
		
		+ get(K key)			: V
		+ isEmpty()				: boolean
		+ print()				: void
		+ put(K key, V value)	: V
		+ remove(K key)			: V
		+ size()				: int
*/

public class OpenAddressHashTable<K,V> implements HashTable<K,V>{
	
	/** a pair of key and value */
	private static class Entry<K,V>{
		private K key;
		private V value;
		
		private Entry(K key, V value){
			this.key = key;
			this.value = value;
		}
	}
	
	/** instance variables */
	private Entry<K,V>[] table;
	private int numKeys;
	private int numDeletes;
	
	private final int INIT_SIZE = 101;
	private final double LOAD_LIMIT = 0.75;
	private final Entry<K,V> DELETED = new Entry<K,V>(null, null);
	
	
	/** constructor */
	public OpenAddressHashTable(){
		init(INIT_SIZE);
	}
	
	public OpenAddressHashTable(int size){
		init(size);
	}
	
	
	/**
	* Returns the value associated with the key
	* If the key is not present, return null
	*
	* @param	key		the key look up a value
	* @return	the value associated with the key or null
	*/
	public V get(K key){
		int index = find(key);
		if(table[index] == null){
			return null;
		}else{
			return table[index].value;
		}
	}
	
	/**
	* Sees if the hash table is empty or not
	*
	* @return	true if the table is empty; otherwise, false
	*/
	public boolean isEmpty(){
		return (numKeys == 0) ? true : false;
	}
	
	/**
	* Puts a value associated with a key into the table
	* and returns the old value if it is replaced
	*
	* @param	key		the key for a value
	* @param	value	the value associated with the key
	* @return	value if it is replaced; otherwise, null
	*/
	public V put(K key, V value){
		/** find the index for the key */
		int index = find(key);
		
		if(table[index] == null){
			table[index] = new Entry<K,V>(key,value);
			numKeys++;
			double loadFactor = (double)(numKeys + numDeletes)/table.length;
			if(loadFactor > LOAD_LIMIT){
				rehash();
			}
			return null;
		}
		V old = table[index].value;
		table[index].value = value;
		return old;
	}
	
	/**
	* Removes the entry associated with a key
	*
	* @param	key		the key to search for a value to delete
	* @return	the value deleted
	*/
	public V remove(K key){
		int index = find(key);
		
		if(table[index] == null){
			return null;
		}
		V removed = table[index].value;
		table[index] = DELETED;
		numKeys--;
		numDeletes++;
		return removed;
	}
	
	/**
	* Print the hash table
	*/
	public void print(){
		for(int i = 0; i < table.length; i++){
			if(table[i] == null){
				System.out.print("null ");
			}else if(table[i] == DELETED){
				System.out.print("[DELETED] ");
			}else{
				System.out.print("[" + table[i].value.toString() + "] ");
			}
		}
		System.out.println("\nOccupancy: " + (double)(numKeys+numDeletes)/table.length);
	}
	
	/**
	* Returns the size of the table
	*
	* @return	the size of the table
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
		table = new Entry[size];
		numKeys = 0;
		numDeletes = 0;
	}
	
	/**
	* Calculates the hash code of the key using the following hash function
	* 	-> hash code = 31^(n-1) * c1 + 31^(n-2) * c2 + ...
	*
	* @param	key		the key for a hash code
	* @return	the hash code of the key
	*/
	private int hashCode(K key){
		String keyStr = String.valueOf(key);
		int i;
		int code = 0;
		for(i = keyStr.length()-1; i >= 0; i--){
			code += Math.pow(31, i) * (int)keyStr.charAt(i);
		}
		return code;
	}
	
	/**
	* Find the index of the key. Use quadratic probing to resolve collisions
	*
	* @param	key		the key to convert
	* @return	the index of the key in the table
	*/
	private int find(K key){
		int index = hashCode(key) % table.length;
		
		if(index < 0){
			index += table.length;
		}
		
		int quadratic = 1;
		while((table[index] != null) &&
			  (!key.equals(table[index].key))){
			index = (index + quadratic * quadratic) % table.length;
			quadratic++;
		}
		return index;
	}
	
	/**
	* Double the table size and move all the keys
	* that are not deleted to the new table
	*/
	private void rehash(){
		numKeys = 0;
		numDeletes = 0;
		Entry<K,V>[] old = table;
		table = new Entry[2 * table.length + 1];
		
		int i;
		for(i = 0; i < old.length; i++){
			if(old[i] != DELETED && old[i] != null){
				put(old[i].key, old[i].value);
			}
		}
	}
	
	/** Test codes */
	public static void main(String[] args){
		OpenAddressHashTable<String, Integer> h = new OpenAddressHashTable<String, Integer>(10);
		String[] keys = new String[100];
		int[] values = new int[100];
		
		/** initialize 100 keys and values */
		for(int i = 0; i < keys.length; i++){
			keys[i] = String.valueOf(i);
		}
		
		for(int i = 0; i < values.length; i++){
			values[i] = i;
		}
		
		/** add 100 pairs of keys and values */
		for(int i = 0; i < keys.length; i++){
			h.put(keys[i], values[i]);
		}
		h.print();
		System.out.println(h.get(keys[10]));
		
		/** delete the first 10 keys */
		for(int i = 0; i < 10; i++){
			h.remove(keys[i]);
		}
		h.print();
	}
}