import java.util.*;

/**
	Author	: Tom Choi
	Date	: 08/14/2016
	
	Impelementation of Hash Table with Open Addressing
		- DELETED				: Entry<K,V>
		- INIT_SIZE				: int
		- LOAD_THRESHOLD		: double
		- numKeys				: int
		- numDelete				: int
		- table					: Entry<K,V>[]
		
		+ get(K key)			: V
		+ isEmpty()				: boolean
		+ put(K key, V value)	: V
		+ remove(K key)			: V
		+ print()				: void
		+ size()				: int
*/

public class OpenAddressHashTable<K, V> implements HashTable<K,V>{
	/** The entry that stores the value and its key */
	private static class Entry<K, V>{
		private K key;
		private V value;
		
		/** Initialize a new key-value pair */
		private Entry(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		/**
		* Sets a new value
		*
		* @param	value	a new value
		* @return	returns the old value
		*/
		private V setValue(V value){
			V old = this.value;
			this.value = value;
			return old;
		}
		
		/**
		* Retrieves the value
		*
		* @return	the value of the pair
		*/
		private V getValue(){
			return this.value;
		}
		
		/**
		* Retrieves the key
		*
		* @return	the key of the pair
		*/
		private K getKey(){
			return this.key;
		}
	}
	
	
	/** Data field */
	private Entry<K,V>[] table;
	
	private final int INIT_SIZE = 101;
	private final double LOAD_THRESHOLD = 0.75;
	private final Entry<K,V> DELETED = new Entry<K,V>(null, null);
	
	private int numKeys;	// keep track of the number of live keys
	private int numDelete;	// keep track of the number of deleted keys
	
	
	/** Constructor */
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
		if(table[index] != null){
			return table[index].value;
		}else{
			return null;
		}
	}
	
	/**
	* See if the hash table is empty or not
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
		int index = find(key);
		
		/** the index is empty -> insert a new entry */
		if(table[index] == null){
			table[index] = new Entry<K, V>(key, value);
			numKeys++;
			
			/** check whether rehash is needed or not */
			double loadFactor = (double) (numKeys + numDelete)/table.length;
			if(loadFactor > LOAD_THRESHOLD){
				rehash();
			}
			return null;
		}
		
		/** the index is occupied -> replace the value */
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
		numDelete++;
		numKeys--;
		return removed;
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
	* Prints out the hash table
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
		System.out.println("\nThe rate of occupancy: " +
			(double)(numKeys + numDelete)/table.length);
	}

	
	/**
	* Initialize the hash table and other instance variables
	*
	* @param	size	the initial size of the hash table
	*/
	private void init(int size){
		table = new Entry[size];
		numKeys = 0;
		numDelete = 0;
	}
	
	
	/**
	* Finds the hash code of a key
	*
	* @param	key		the key to find its hash code
	* @return	the hash code of the key
	*/
	private int hashCode(K key){
		String keyStr = String.valueOf(key);
		int len = keyStr.length();
		int code = 0;
		for(int i = len - 1; i >= 0; i--){
			int c = (int)keyStr.charAt(i);
			code = code + (int)Math.pow(31, i) * c;
		}
		return code;
	}
	
	/**
	* Finds either the target key or the first empty slot
	* in the search chain using quadratic probing
	*
	* @param	key		the key to search
	* @return	index in the table
	*/
	private int find(K key){
		int index = hashCode(key) % table.length;
		
		/** if index is less than 0, make it positive */
		while(index < 0){
			index += table.length;
		}

		int quadratic = 1;
		while((table[index] != null) &&
			  !(key.equals(table[index].key))){
			index = (index + quadratic * quadratic) % table.length;
		}
		return index;
	}
	
	/**
	* Double the table size when more than 75% of the table
	* is filled by both live and deleted keys
	*/
	private void rehash(){
		Entry<K, V>[] old = table;
		table = new Entry[2 * table.length + 1];
		
		/** reinsert all items in old table into the new one */
		numKeys = 0;
		numDelete = 0;
		for(int i = 0; i < old.length; i++){
			if((old[i] != null) && (old[i] != DELETED)){
				put(old[i].key, old[i].value);
			}
		}
	}
		
	
	/** Testing codes */
	public static void main(String[] args){
		OpenAddressHashTable<String, Integer> hash = new OpenAddressHashTable<String, Integer>();
		System.out.println(hash.isEmpty());
		System.out.println(hash.size());
		
		String[] keys = new String[200];
		int[] values = new int[keys.length];
		
		/** randomly generate keys */
		Random rand = new Random();
		int i;
		int randNum;
		for(i = 0; i < keys.length; i++){
			randNum = rand.nextInt(65536);
			keys[i] = String.valueOf((char)randNum);
		}
		
		/** values from 0 ~ 199 */
		for(i = 0; i < values.length; i++){
			values[i] = i;
		}
		
		for(i = 0; i < keys.length; i++){
			hash.put(keys[i], values[i]);
		}
		
		hash.print();
		System.out.println(hash.isEmpty());
		System.out.println(hash.size());
		
		/** delete the values associated with the first 10 keys */
		System.out.print("Removed: ");
		for(i = 0; i < 10; i++){
			System.out.print(hash.remove(keys[i]) + " ");
		}System.out.println();
		
		hash.print();
	}
}