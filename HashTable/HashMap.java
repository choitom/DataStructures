public interface HashMap<K,V>{
	
	/**
	* Returns the value of a given key
	* 
	* @return	the value of a key
	*/
	public V get(K key);
	
	/**
	* See if no (key, value) pair is in the map or not
	*
	* @return	true if empty; otherwise, false
	*/
	public boolean isEmpty();
	
	/**
	* Add (key, value) pair to the map
	*
	* @param	key		key to add
	* @param	value	value to add
	* @return	old value if replaced; otherwise, null
	*/
	public V put(K key, V value);
	
	/**
	* Remove a pair with a given key
	*
	* @param	key		key of a pair to remove
	* @return	removed value; otherwise, null
	*/
	public V remove(K key);
	
	/**
	* The number of pairs in the map
	*
	* @return	the size of the map
	*/
	public int size();
}