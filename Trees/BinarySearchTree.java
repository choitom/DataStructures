/**
	Author	: Tom Choi
	Date	: 08/13/2016
	
	Implementation of Binary Search Tree
		- root				 : Node<E>
		- addReturn			 : boolean
		- deleteReturn		 : E
		
		+ add(E item)		 : boolean
		+ contains(E item)	 : boolean
		+ delete(E item)	 : E
		+ find(E item)		 : E
		+ inorderTraverse()	 : void
		+ remove(E item)	 : boolean
		+ preorderTraverse() : void
		+ postorderTraverse(): void
*/

public class BinarySearchTree<E extends Comparable<E>> implements SearchTree<E>{
	/**
	* Node class for binary tree
	*/
	private static class Node<E>{
		/** left child */
		private Node<E> left = null;
		
		/** right child */
		private Node<E> right = null;
		
		/** data to store */
		private E item;
		
		private Node(E item){
			this.item = item;
		}
	}
	
	
	/** the root of the tree */
	private Node<E> root;
	
	private boolean addReturn;
	private E deleteReturn;
	
	/** constructors of the tree */
	public BinarySearchTree(){
		this.root = null;
	}
	
	public BinarySearchTree(Node<E> root){
		this.root = root;
	}
	
	
	/**
	* Returns true if an item is added to a tree
	* Otherwise, return false
	*
	* @param	item	an item to add to the tree
	* @return			whether an item is added or not
	*/
	public boolean add(E item){
		root = add(item, root);
		return addReturn;
	}
	
	/**
	* Private helper method of add(E item)
	* Recursively searches the appropriate place to add a node
	*
	* @param	item	an item to add to the tree
	* @param	root	the node to compare/find the place to add
	* @return			the node with an item added
	*/
	private Node<E> add(E item, Node<E> root){
		/** found a place to store the item */
		if(root == null){
			addReturn = true;
			root = new Node<E>(item);
			return root;
		}
		
		int comparison = item.compareTo(root.item);
		
		/** item to add already exists in the tree */
		if(comparison == 0){
			addReturn = false;
			return root;
		}
		/** item is less -> add to the left of the root */
		else if(comparison < 0){
			root.left = add(item, root.left);
			return root;
		}
		/** item is greater -> add to the right of the root */
		else{
			root.right = add(item, root.right);
			return root;
		}
	}
	
	/**
	* Returns true if an item exists in a tree
	* If not, return false
	*
	* @param	item	an item to search in the tree
	* @return			whether an item is in a tree or not
	*/
	public boolean contains(E item){
		return (find(item) != null) ? true : false;
	}
	
	/**
	* Returns the reference of an item in the tree if found
	* Otherwise, return null
	*
	* @param	item	an item to find in the tree
	* @return			the reference to the item in the tree
	*/
	public E find(E item){
		E found = find(item, root);
		return found;
	}
	
	/**
	* Private helper method of find(E item)
	* Search an item in the tree and returns its reference
	*
	* @param	item	an item to find in the tree
	* @param	root	the start node
	* @return			the reference to the item in the tree
	*/
	private E find(E item, Node<E> root){
		/** item not found */
		if(root == null){
			return null;
		}
		
		int comparison = item.compareTo(root.item);
		
		/** item found */
		if(comparison == 0){
			return root.item;
		}
		/** item less than that of root */
		else if(comparison < 0){
			return find(item, root.left);
		}
		/** item greater than that of root */
		else{
			return find(item, root.right);
		}
	}
	
	
	/**
	* Delete an item and returns it.
	* If the item to delete is not in the tree, return null
	*
	* @param	item	an item to delete
	* @return			an item deleted
	*/
	public E delete(E item){
		root = delete(item, root);
		return deleteReturn;
	}

	/**
	* Private helper method for delete(E item)
	*
	* @param	item	an item to delete
	* @param	root	the start node
	* @return			deleted node
	*/
	private Node<E> delete(E item, Node<E> root){
		/** the item to delete does not exist */
		if(root == null){
			deleteReturn = null;
			return root;
		}
		
		int comparison = item.compareTo(root.item);
		
		/** delete from the left subtree */
		if(comparison < 0){
			root.left = delete(item, root.left);
			return root;
		}
		/** delete from the right subtree */
		else if(comparison > 0){
			root.right = delete(item, root.right);
			return root;
		}
		/** the node to delete is found */
		else{
			deleteReturn = root.item;
			
			/** the node is a leaf */
			if(root.left == null && root.right == null){
				return null;
			}
			/** the node has one left child */
			else if(root.left != null && root.right == null){
				return root.left;
			}
			/** the node has one right child */
			else if(root.left == null && root.right != null){
				return root.right;
			}
			/** the node has two children */
			else{
				/**
				* the left child becomes the local root
				*/
				if(root.left.right == null){
					root.item = root.left.item;
					root.left = root.left.left;
					return root;
				}
				/**
				* find the left-rightmost node and replace the local root's
				* item with that of left-rightmost node.
				*/
				else{
					root.item = findRightmost(root.left);
					return root;
				}
			}
		}
	}
	
	/**
	* Finds the rightmost child of a node
	*
	* @param	node	the start node
	* @return			the reference to the item of the rightmost child
	*/
	private E findRightmost(Node<E> node){
		/** the rightmost child found */
		if(node.right.right == null){
			E rightmost = node.right.item;
			node.right = node.right.left;
			return rightmost;
		}else{
			return findRightmost(node.right);
		}
	}
	
	/**
	* Delete an item and returns true if deleted.
	* If the item to delete is not in the tree, return false
	*
	* @param	item	an item to delete
	* @return			whether an item is deleted or not
	*/
	public boolean remove(E item){
		return (delete(item) != null) ? true : false;
	}
	
	/**
	* Traverse the tree in the following order
	* root -> left subtree -> right subtree
	*/
	public void preorderTraverse(){
		preorderHelper(root);
		System.out.println();
	}
	
	/**
	* Private preorderTraverse() helper method
	*
	* @param	root	the start node
	*/
	private void preorderHelper(Node<E> root){
		if(root != null){
			System.out.print(root.item.toString() + " ");
			preorderHelper(root.left);
			preorderHelper(root.right);
		}
	}
	
	/**
	* Traverse the tree in the following order
	* left subtree -> root -> right subtree
	*/
	public void inorderTraverse(){
		inorderHelper(root);
		System.out.println();
	}
	
	/**
	* Private inroderTraverse() helper method
	*
	* @param	root	the start node
	*/
	private void inorderHelper(Node<E> root){
		if(root != null){
			inorderHelper(root.left);
			System.out.print(root.item.toString() + " ");
			inorderHelper(root.right);
		}
	}
	
	/**
	* Traverse the tree inthe following order
	* left subtree -> right usbtree -> root
	*/
	public void postorderTraverse(){
		postorderHelper(root);
		System.out.println();
	}
	
	/**
	* Private postorderTraverse() helper method
	*
	* @param	root	the start node
	*/
	private void postorderHelper(Node<E> root){
		if(root != null){
			postorderHelper(root.left);
			postorderHelper(root.right);
			System.out.print(root.item.toString() + " ");
		}
	}
	
	
	public static void main(String[] args){
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		
		String[] tom = {"Tom", "Sun", "Hyun", "Choi", "The", "Googler",
						"Yo", "Hi"};
						
		for(int i = 0; i < tom.length; i++){
			bst.add(tom[i]);
		}
		
		bst.preorderTraverse();
		bst.inorderTraverse();
		bst.postorderTraverse();
		
		System.out.println(bst.contains("Tom"));	// true
		System.out.println(bst.contains("Hello"));	// false
		System.out.println(bst.find("Hyun"));		// Hyun
		
		System.out.println(bst.delete("Yo"));
		System.out.println(bst.remove("Hi"));
		
		bst.preorderTraverse();
	}
}