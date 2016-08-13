public interface SearchTree<E>{
	
	// Inserts item it belongs in the tree
	// Returns true if item is inserted
	// Otherwise, return false
	public boolean add(E item);
	
	// Returns true if item is found in the tree 
	public boolean contains(E item);
	
	// Returns a reference to the data in the node
	// that is equal to the item. Otherwise, return null
	public E find(E item);
	
	// Removes item (if found) from tree and returns it
	// Otherwise, return null
	public E delete(E item);

	// Removes item (if found) from tree and returns true
	// Otherwise, return false
	public boolean remove(E item);
	
	// Prints items in a tree in
	// node -> left -> right order
	public void preorderTraverse();
	
	// Prints items in a tree in
	// left -> node -> right order
	public void inorderTraverse();
	
	// Prints items in a tree in
	// left -> right -> node order
	public void postorderTraverse();
}