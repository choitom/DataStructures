public interface HashMapInterface<K,V>{
	/**
	* Returns a Set view of the keys contained in this map
	*/
	public HashSet<K> keySet();
	
	/**
	* Associates the specified value with the specified key in this map
	*/
	public V put(K key, V value);
	
	/**
	* Returns true if this map contains a mapping for the specified key
	*/
	public boolean containsKey(K key);
	
	/**
	* Returns true if this map contains a mapping for the specified value
	*/
	public boolean containsValue(V value);
}