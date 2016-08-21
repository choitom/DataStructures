public interface AVLInterface<E>{
	
	/**
	* Clear everything in the tree
	*/
	public void clear();
	
	/**
	* Finds if the tree is empty or not
	*
	* @return	true if empty; otherwise, false
	*/
	public boolean isEmpty();
	
	/**
	* Inserts an item to the tree while balancing the tree height
	*
	* @param	item	an item to insert
	*/
	public void insert(E item);
	
	/**
	* Returns the size of the tree
	*
	* @return	number of nodes in the tree
	*/
	public int size();
	
	/**
	* Traverse in left -> node -> right order
	*/
	public void inorderTraversal();
	
	/**
	* Traverse in node -> left -> right order
	*/
	public void preorderTraversal();
	
	/**
	* Traverse in left -> right -> node order
	*/
	public void postorderTraversal();
}