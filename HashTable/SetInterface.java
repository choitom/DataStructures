public interface SetInterface<K>{
	/**
	* Add an item to the set
	*
	* @param	item	item to add
	* @return	true if added; otherwise, false
	*/
	public boolean add(K item);
	
	/**
	* See if an item exists in the set
	*
	* @param	item	item to search
	* @return	true if exists; otherwise, false
	*/
	public boolean contains(K item);
	
	/**
	* Remove an item from the set
	*
	* @param	item	item to remove
	* @return	true if removed; otherwise, false
	*/
	public boolean remove(K item);
	
	/**
	* See if the set is empty or not
	*
	* @return	ture if empty; otherwise, false
	*/
	public boolean isEmpty();
	
	/**
	* @return	the size of the set
	*/
	public int size();
}