/**
	The purpose of Hash Table is
		-> speedy insertion, deletion and look-up
			
	Comparisons with other data structures (Sorted Array & Binary Tree)
	
					Insert	Remove	Search
	Hash Table		O(1)	O(1)	O(1)
	Binary Tree		O(logn)	O(logn)	O(logn)
	Sorted Array	O(n)	O(n)	O(n)
	
	Hash Functions
		-> calculate the index to store in the hash function
*/

public interface HashTable<K, V>{
	
	/**
	* Returns the value associated with the key
	* If the key is not present, return null
	*
	* @param	key		the key look up a value
	* @return	the value associated with the key or null
	*/
	public V get(K key);
	
	/**
	* See if the hash table is empty or not
	*
	* @return	true if the table is empty; otherwise, false
	*/
	public boolean isEmpty();
	
	/**
	* Puts a value associated with a key into the table
	* and returns the old value if it is replaced
	*
	* @param	key		the key for a value
	* @param	value	the value associated with the key
	* @return	value if it is replaced; otherwise, null
	*/
	public V put(K key, V value);
	
	/**
	* Removes the entry associated with a key
	*
	* @param	key		the key to search for a value to delete
	* @return	the value deleted
	*/
	public V remove(K key);
	
	/**
	* Returns the size of the table
	*
	* @return	the size of the table
	*/
	public int size();
}