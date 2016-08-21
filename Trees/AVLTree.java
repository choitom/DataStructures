public class AVLTree<E extends Comparable<E>> implements AVLInterface<E>{
	
	/** Node class with data, height, left and right child */
	private static class AVLNode<E>{
		private E data;
		private int height;
		private AVLNode<E> left;
		private AVLNode<E> right;
		
		private AVLNode(){
			this.data = null;
			this.height = 0;
			this.left = null;
			this.right = null;
		}
		
		private AVLNode(E data){
			this.data = data;
			this.height = 0;
			this.left = null;
			this.right = null;
		}
	}
	
	/** root of the tree */
	private AVLNode<E> root;
	private int size;
	
	public AVLTree(){
		root = null;
	}
	
	/**
	* Clear everything in the tree
	*/
	public void clear(){
		root = null;
	}
	
	/**
	* Finds if the tree is empty or not
	*
	* @return	true if empty; otherwise, false
	*/
	public boolean isEmpty(){
		return root == null;
	}
	
	/**
	* Inserts an data to the tree while balancing the tree height
	*
	* @param	data	an item to insert
	*/
	public void insert(E data){
		root = insert(root, data);
	}
	
	private AVLNode<E> insert(AVLNode<E> node, E data){
		/** the node to store data is empty */
		if(node == null){
			node = new AVLNode<E>(data);
		}else{
			int comparison = data.compareTo(node.data);
			
			/** duplicates */
			if(comparison == 0){
				return node;
			}
			/** insert into left subtree */
			else if(comparison < 0){
				node.left = insert(node.left, data);
				
				/** the height is unbalanced after left insertion */
				if(height(node.left) - height(node.right) == 2){
					
					comparison = data.compareTo(node.left.data);
					
					/** right-right rotation */
					if(comparison < 0){
						node = rightRightRotation(node);
					}
					/** left-right rotation */
					else{
						node = leftRightRotation(node);
					}
				}
			}
			/** insert into right subtree */
			else{
				node.right = insert(node.right, data);
				
				/** the height is unbalanced after right insertion */
				if(height(node.right) - height(node.left) == 2){
					
					comparison = data.compareTo(node.right.data);
					
					/** left-left rotation */
					if(comparison > 0){
						node = leftLeftRotation(node);
					}
					/** right-left rotation */
					else{
						node = rightLeftRotation(node);
					}
				}
			}
		}
		node.height = max(height(node.left), height(node.right)) + 1;
		return node;
	}
	
	/**
	* Rotates a child to right
	*/
	private AVLNode<E> rightRightRotation(AVLNode<E> node){
		AVLNode<E> rotated = node.left;
		node.left = rotated.right;
		rotated.right = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		rotated.height = max(height(rotated.left), height(rotated.right)) + 1;
		return rotated;
	}
	
	/**
	* Rotates a child to left
	*/
	private AVLNode<E> leftLeftRotation(AVLNode<E> node){
		AVLNode<E> rotated = node.right;
		node.right = rotated.left;
		rotated.left = node;
		node.height = max(height(node.left), height(node.right)) + 1;
		rotated.height = max(height(rotated.left), height(rotated.right)) + 1;
		return rotated;
	}
	
	/**
	* Double rotates left first and then right
	*/
	private AVLNode<E> leftRightRotation(AVLNode<E> node){
		node.left = leftLeftRotation(node.left);
		return rightRightRotation(node);
	}
	
	/**
	* Double rotates right first and then left
	*/
	private AVLNode<E> rightLeftRotation(AVLNode<E> node){
		node.right = rightRightRotation(node.right);
		return leftLeftRotation(node);
	}
	
	/**
	* Returns the height of a node
	*/
	private int height(AVLNode<E> node){
		return (node != null) ? node.height : 0;
	}
	
	/**
	* Compares two numbers and return the greater one
	*/
	private int max(int i, int j){
		return (i > j) ? i : j;
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
	
	private void inorderHelper(AVLNode<E> node){
		if(node != null){
			inorderHelper(node.left);
			System.out.print(node.data + " ");
			inorderHelper(node.right);
		}
	}
	
	/**
	* Traverse in node -> left -> right order
	*/
	public void preorderTraversal(){
		preorderHelper(root);
	}
	
	private void preorderHelper(AVLNode<E> node){
		if(node != null){
			System.out.print(node.data + " ");
			preorderHelper(node.left);
			preorderHelper(node.right);
		}
	}
	
	/**
	* Traverse in left -> right -> node order
	*/
	public void postorderTraversal(){
		postorderHelper(root);
	}
	
	private void postorderHelper(AVLNode<E> node){
		if(node != null){
			postorderHelper(node.left);
			postorderHelper(node.right);
			System.out.print(node.data + " ");
		}
	}
	
	
	/** Test Code */
	public static void main(String[] args){
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insert(5);
		tree.insert(7);
		tree.insert(6);
		tree.preorderTraversal();
	}
}