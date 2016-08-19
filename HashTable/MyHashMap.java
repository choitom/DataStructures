/**
	Author	: Tom Choi
	Date	: 08/18/2016
	
	Implementation of my own version of Hash Map
		- The underlying ideas of hash map are
		  unordered, data stored like python dictionary
*/

public class MyHashMap<K,V> extends HashTable<K,V> implements HashMapInterface<K,V>{

	public MyHashMap(){
		super();
	}
	
	/**
	* Returns a Set view of the keys contained in this map
	*/
	public HashSet<K> keySet(){
		HashSet<K> set = new HashSet<K>();
		int i;
		for(i = 0; i < table.length; i++){
			if(table[i] != null && table[i] != DELETED){
				set.add(table[i].key);
			}
		}
		return set;
	}
	
	/**
	* Associates the specified value with the specified key in this map
	*/
	public V put(K key, V value){
		return super.put(key, value);
	}
	
	/**
	* Returns true if this map contains a mapping for the specified key
	*/
	public boolean containsKey(K key){
		HashSet<K> set = keySet();
		return set.contains(key);
	}
	
	/**
	* Returns true if this map contains a mapping for the specified value
	*/
	public boolean containsValue(V value){
		int i;
		for(i = 0; i < table.length; i++){
			if(table[i] != null && table[i] != DELETED){
				if(value.equals(table[i].value)){
					return true;
				}
			}
		}
		return false;
	}
	
	/** Test code */
	public static void main(String[] args){
		MyHashMap<String, Integer> map = new MyHashMap<String, Integer>();
		String[] str = new String[]{"Tom", "Is", "A", "Awesome", "Googler"};
		int[] value = {1, 2, 3, 4, 5};
		
		
		for(int i = 0; i < str.length; i++){
			map.put(str[i], value[i]);		// add pairs to a map
		}
		
		HashSet<String> keys = map.keySet();	// get a key set
		
		for(int i = 0; i < str.length; i++){
			System.out.print(keys.contains(str[i]) + " ");
		}System.out.println();
		
		System.out.println(map.containsKey("Tom"));
		System.out.println(map.containsValue(1));
		
		System.out.println(map.containsKey("Gom"));
		System.out.println(map.containsValue(9));
	}
}