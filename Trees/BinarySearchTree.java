public class BinarySearchTree<E extends Comparable<E>> implements SearchTree<E>{
	private static class Node<E>{
		private Node<E> left;
		private Node<E> right;
		private E item;
		
		private Node(E item){
			this.item = item;
		}
	}
	
	private Node<E> root;
	private boolean addReturn;
	private E deleteReturn;
	
	/*
		CONSTRUCTORS
	*/
	public BinarySearchTree(){
		this.root = null;
	}
	
	public BinarySearchTree(Node<E> root){
		this.root = root;
	}
	
	
	/*
		PUBLIC METHODS
	*/
	
	// Inserts item it belongs in the tree
	// Returns true if item is inserted
	// Otherwise, return false
	public boolean add(E item){
		root = add(item, root);
		return addReturn;
	}
	
	// Returns true if item is found in the tree
	public boolean contains(E item){
		E found = find(item);
		return (found != null) ? true : false;
	}
	
	// Returns a reference to the data in the node
	// that is equal to the item. Otherwise, return null
	public E find(E item){
		E found = find(item, root);
		return found;
	}
	
	// Removes item (if found) from tree and returns it
	// Otherwise, return null
	public E delete(E item){
		root = delete(item, root);
		return deleteReturn;
	}
	
	// Removes item (if found) from tree and returns true
	// Otherwise, return false
	public boolean remove(E item){
		delete(item);
		return (deleteReturn != null) ? true : false;
	}
	
	// Prints items in a tree in
	// node -> left -> right order
	public void preorderTraverse(){
		preorderTraverseHelper(root);
		System.out.println();
	}
	
	// Prints items in a tree in
	// left -> node -> right order
	public void inorderTraverse(){
		inorderTraverseHelper(root);
		System.out.println();
	}
	
	// Prints items in a tree in
	// left -> right -> node order
	public void postorderTraverse(){
		postorderTraverseHelper(root);
		System.out.println();
	}
	
	
	/*
		PRIVATE METHODS
	*/
	private Node<E> add(E item, Node<E> localRoot){
		// found a place to add
		if(localRoot == null){
			addReturn = true;
			return new Node<E>(item);
		}
		
		int comparison = item.compareTo(localRoot.item);
		
		// item already exists
		if(comparison == 0){
			addReturn = false;
			return localRoot;
		}
		// item less than the local root item
		else if(comparison < 0){
			localRoot.left = add(item, localRoot.left);
			return localRoot;
		}
		// item greater than the local root item
		else{
			localRoot.right = add(item, localRoot.right);
			return localRoot;
		}
	}
	
	private E find(E item, Node<E> localRoot){
		// item not found
		if(localRoot == null){
			return null;
		}
		
		int comparison = item.compareTo(localRoot.item);
		
		// item found
		if(comparison == 0){
			return localRoot.item;
		}
		// item less than the node item: search left
		else if(comparison < 0){
			return find(item, localRoot.left);
		}
		// item greater than the node item: search right
		else{
			return find(item, localRoot.right);
		}
	}
	
	private Node<E> delete(E item, Node<E> localRoot){
		// item not found
		if(localRoot == null){
			deleteReturn = null;
			return localRoot;
		}
		
		int comparison = item.compareTo(localRoot.item);
		
		// item less than the local root item: search left
		if(comparison < 0){
			localRoot.left = delete(item, localRoot.left);
			return localRoot;
		}
		// item greater than the local root item: search right
		else if(comparison > 0){
			localRoot.right = delete(item, localRoot.right);
			return localRoot;
		}
		// item found
		else{
			deleteReturn = localRoot.item;
			
			// leaf
			if(localRoot.left == null && localRoot.right == null){
				return null;
			}
			// one left child
			else if(localRoot.left != null && localRoot.right == null){
				return localRoot.left;
			}
			// one right child
			else if(localRoot.left == null && localRoot.right != null){
				return localRoot.right;
			}
			// two children
			else{
				if(localRoot.left.right == null){
					localRoot.item = localRoot.left.item;
					localRoot.left = localRoot.left.left;
					return localRoot;
				}else{
					localRoot.item = findRightmost(localRoot.left);
					return localRoot;
				}
			}
		}
	}
	
	private E findRightmost(Node<E> node){
		// rightmost found
		if(node.right.right == null){
			E rightmost = node.right.item;
			node.right = node.right.left;
			return rightmost;
		}
		// not found
		else{
			return findRightmost(node.right);
		}
	}
	
	private void preorderTraverseHelper(Node<E> root){
		if(root != null){
			System.out.print(root.item.toString() + " ");
			preorderTraverseHelper(root.left);
			preorderTraverseHelper(root.right);
		}
	}
	
	private void inorderTraverseHelper(Node<E> root){
		if(root != null){
			inorderTraverseHelper(root.left);
			System.out.print(root.item + " ");
			inorderTraverseHelper(root.right);
		}
	}
	
	private void postorderTraverseHelper(Node<E> root){
		if(root != null){
			postorderTraverseHelper(root.left);
			postorderTraverseHelper(root.right);
			System.out.print(root.item + " ");
		}
	}
	
	
	public static void main(String[] args){
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		String[] tom = {"Tom", "Sun", "Hyun", "Choi", "The", "Googler", "Choi", "You Can Do It"};
		
		for(int i = 0; i < tom.length; i++){
			boolean added = bst.add(tom[i]);
		}
		
		bst.preorderTraverse();
		bst.inorderTraverse();
		bst.postorderTraverse();
		
		bst.delete("Sun");
		bst.delete("Hyun");
		
		bst.preorderTraverse();
		bst.inorderTraverse();
		bst.postorderTraverse();
		
		System.out.println(bst.contains("Sun"));
		
		bst.add("Sun");
		bst.add("Hyun");
		
		bst.preorderTraverse();
		bst.inorderTraverse();
		bst.postorderTraverse();
	}
}