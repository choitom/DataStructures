public interface SearchTree<E>{
	
	/**
	* Returns true if an item is added to a tree
	* Otherwise, return false
	*
	* @param	item	an item to add to the tree
	* @return			whether an item is added or not
	*/
	public boolean add(E item);
	
	/**
	* Returns true if an item exists in a tree
	* If not, return false
	*
	* @param	item	an item to search in the tree
	* @return			whether an item is in a tree or not
	*/
	public boolean contains(E item);
	
	/**
	* Returns the reference of an item in the tree if found
	* Otherwise, return null
	*
	* @param	item	an item to find in the tree
	* @return			the reference to the item in the tree
	*/
	public E find(E item);
	
	/**
	* Delete an item and returns it.
	* If the item to delete is not in the tree, return null
	*
	* @param	item	an item to delete
	* @return			an item deleted
	*/
	public E delete(E item);

	/**
	* Delete an item and returns true if deleted.
	* If the item to delete is not in the tree, return false
	*
	* @param	item	an item to delete
	* @return			whether an item is deleted or not
	*/
	public boolean remove(E item);
	
	/**
	* Traverse the tree in the following order
	* root -> left subtree -> right subtree
	*/
	public void preorderTraverse();
	
	/**
	* Traverse the tree in the following order
	* left subtree -> root -> right subtree
	*/
	public void inorderTraverse();
	
	/**
	* Traverse the tree inthe following order
	* left subtree -> right usbtree -> root
	*/
	public void postorderTraverse();
}