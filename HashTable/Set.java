/**
	Author	: Tom Choi
	Date	: 08/18/2016
	
	Implementation of Hash Set
		- The underlying principle of the implementaion of set is
		  that set is not ordered and doens't allow duplicates.
	
		- By using HashTable for its internal data structures,
		  it is possible to store/access items not based on their orders
*/

public class Set<K> implements SetInterface<K>{
	private HashTable<K,K> map;
	
	public Set(){
		map = new HashTable<K,K>();
	}
	
	/**
	* Add an item to the set
	*
	* @param	item	item to add
	* @return	true if added; otherwise, false
	*/
	public boolean add(K item){
		return (map.put(item, item) == null);
	}
	
	/**
	* See if an item exists in the set
	*
	* @param	item	item to search
	* @return	true if exists; otherwise, false
	*/
	public boolean contains(K item){
		return (map.get(item) != null);
	}
	
	/**
	* Remove an item from the set
	*
	* @param	item	item to remove
	* @return	true if removed; otherwise, false
	*/
	public boolean remove(K item){
		return (map.remove(item) != null);
	}
	
	/**
	* See if the set is empty or not
	*
	* @return	ture if empty; otherwise, false
	*/
	public boolean isEmpty(){
		return map.isEmpty();
	}
	
	/**
	* @return	the size of the set
	*/
	public int size(){
		return map.size();
	}
	
	public static void main(String[] args){
		Set<Integer> set = new Set<Integer>();
		
		// add 0 ~ 9
		for(int i = 0; i < 10; i++){
			set.add(i);
		}
		
		System.out.println(set.contains(1));	// true
		System.out.println(set.contains(2));	// true
		System.out.println(set.remove(2));		// true
		System.out.println(set.contains(2));	// false
	}
}