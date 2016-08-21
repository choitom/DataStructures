public class AVLTree <E extends Comparable<E>> implements AVLInterface<E>{
	
	/** Node class */
	private static class AVLNode<E>{
		private E data;
		private int height;
		private AVLNode<E> left;
		private AVLNode<E> right;
		
		private AVLNode(){
			init(null);
		}
		
		private AVLNode(E data){
			init(data);
		}
		
		private void init(E data){
			this.data = data;
			this.height = 0;
			this.left = null;
			this.right = null;
		}
	}
	
	/** The root of the tree */
	private AVLNode<E> root;
	private E removed;
	private int size;
	
	/** Constructor*/
	public AVLTree(){
		init();
	}
	
	
	/**
	* Clear everything in the tree
	*/
	public void clear(){
		init();
	}
	
	/**
	* Finds if the tree is empty or not
	*
	* @return	true if empty; otherwise, false
	*/
	public boolean isEmpty(){
		return (root == null);
	}
	
	/**
	* Inserts an item to the tree while balancing the tree height
	*
	* @param	item	an item to insert
	*/
	public void insert(E data){
		root = insert(root, data);
	}
	
	/**
	* Removes an item from the tree while balancing the tree height
	*
	* @ param	item	an item to remove
	*/
	public E remove(E data){
		root = remove(root, data);
		return removed;
	}
	
	/**
	* Returns the size of the tree
	*
	* @return	number of nodes in the tree
	*/
	public int size(){
		return size;
	}
	
	/**
	* Traverse in left -> node -> right order
	*/
	public void inorderTraversal(){
		inorderHelper(root);
	}
	
	/**
	* Traverse in node -> left -> right order
	*/
	public void preorderTraversal(){
		preorderHelper(root);
	}
	
	/**
	* Traverse in left -> right -> node order
	*/
	public void postorderTraversal(){
		postorderHelper(root);
	}
	
	
	/**
	* PRIVATE METHODS
	*/
	private void init(){
		this.root = null;
		this.removed = null;
		this.size = 0;
	}
	
	private AVLNode<E> insert(AVLNode<E> node, E data){
		/** create a new node */
		if(node == null){
			node = new AVLNode<E>(data);
		}else{
			int comparison = data.compareTo(node.data);
			
			/** duplicate */
			if(comparison == 0){
				return node;
			}
			/** insert into the left subtree */
			else if(comparison < 0){
				node.left = insert(node.left, data);
			}
			/** insert into the right subtree*/
			else{
				node.right = insert(node.right, data);
			}
		}
		/** balance the height of the tree */
		size++;
		return balanceHeight(node);
	}
	
	private AVLNode<E> remove(AVLNode<E> node, E data){
		/** item to delete not found */
		if(node == null){
			removed = null;
			return null;
		}
		
		int comparison = data.compareTo(node.data);
		
		/** remove from the left subtree */
		if(comparison < 0){
			node.left = remove(node.left, data);
		}
		/** remove from the right subtree */
		else if(comparison > 0){
			node.right = remove(node.right, data);
		}
		/** remove the node */
		else{
			removed = node.data;
			
			/** two children */
			if(node.left != null && node.right != null){
				node.data = findRightMost(node.left);
				node.left = remove(node.left, node.data);
			}else{
				return (node.left != null) ? node.left : node.right;
			}
		}
		size--;
		return balanceHeight(node);
	}
	
	private E findRightMost(AVLNode<E> node){
		while(node.right != null){
			node = node.right;
		}
		return node.data;
	}
	
	private AVLNode<E> balanceHeight(AVLNode<E> node){
		/** left imbalance */
		if(height(node.left) - height(node.right) == 2){
			
			/** right-right rotation */
			if(height(node.left.left) >= height(node.left.right)){
				node = rightRightRotation(node);
			}
			/** left-right rotation */
			else{
				node = leftRightRotation(node);
			}
		}
		/** right imbalance */
		else if(height(node.right) - height(node.left) == 2){
			
			/** left-left rotation */
			if(height(node.right.right) >= height(node.right.left)){
				node = leftLeftRotation(node);
			}
			/** right-left rotation */
			else{
				node = rightLeftRotation(node);
			}
		}
		node.height = max(height(node.left), height(node.right)) + 1;
		return node;
	}
	
	private AVLNode<E> rightRightRotation(AVLNode<E> node){
		AVLNode<E> newNode = node.left;
		node.left = newNode.right;
		newNode.right = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		newNode.height = max(height(newNode.left), height(newNode.right)) + 1;
		return newNode;
	}
	
	private AVLNode<E> leftLeftRotation(AVLNode<E> node){
		AVLNode<E> newNode = node.right;
		node.right = newNode.left;
		newNode.left = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		newNode.height = max(height(newNode.left), height(newNode.right)) + 1;
		return newNode;
	}
	
	private AVLNode<E> rightLeftRotation(AVLNode<E> node){
		node.right = rightRightRotation(node.right);
		return leftLeftRotation(node);
	}
	
	private AVLNode<E> leftRightRotation(AVLNode<E> node){
		node.left = leftLeftRotation(node.left);
		return rightRightRotation(node);
	}
	
	/**
	* Returns the height of a node in the tree
	*/
	private int height(AVLNode<E> node){
		return (node == null) ? 0 : node.height;
	}
	
	/**
	* Compare two integers and return the greater one
	*/
	private int max(int i, int j){
		return (i > j) ? i : j;
	}
	
	private void inorderHelper(AVLNode<E> node){
		if(node != null){
			inorderHelper(node.left);
			System.out.print(node.data + " ");
			inorderHelper(node.right);
		}
	}
		
	private void preorderHelper(AVLNode<E> node){
		if(node != null){
			System.out.print(node.data + " ");
			preorderHelper(node.left);
			preorderHelper(node.right);
		}
	}
	
	private void postorderHelper(AVLNode<E> node){
		if(node != null){
			postorderHelper(node.left);
			postorderHelper(node.right);
			System.out.print(node.data + " ");
		}
	}
	
	
	/** TEST CODES */
	public static void main(String[] args){
		AVLTree<Integer> tree = new AVLTree<Integer>();
		int[] arr = {90, 80, 100, 70, 85};
		for(int i = 0; i < arr.length; i++){
			tree.insert(arr[i]);
		}
		System.out.println(tree.remove(100));
		tree.preorderTraversal();
		System.out.println(tree.size());
		System.out.println(tree.isEmpty());
		tree.clear();
		System.out.println(tree.isEmpty());
	}
}