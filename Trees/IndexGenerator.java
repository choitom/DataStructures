/**
	Author	: Tom Choi
	Date	: 08/13/2016
	
	Implementation of Index Generator
		- bst						: BinarySearchTree<String>
		+ buildIndex(Scanner scan)	: void
		+ showIndex()				: void
*/

import java.io.*;
import java.util.*;

public class IndexGenerator{
	/** Binary search tree for storing index and line numbers */
	private BinarySearchTree<String> bst;
	
	/** Constructor initializes a bst*/
	public IndexGenerator(){
		this.bst = new BinarySearchTree<String>();
	}
	
	/**
	* Tokenize input strings and store each token's index and line numbers
	*
	* @param	scan	text input
	*/
	public void buildIndex(Scanner scan){
		int lineNumber = 0; // initial line number
		
		/** read each line */
		while(scan.hasNextLine()){
			lineNumber++;	// increment the line number
			String token;
			
			/** find alphanumeric token in a line */
			while((token = scan.findInLine("[\\p{L}\\p{N}']+")) != null){
				token = token.toLowerCase();
				bst.add("[" + token + " - " + String.valueOf(lineNumber) + "]");
			}
			scan.nextLine();
		}
	}
	
	/**
	* Display index and line numbers in order
	*/
	public void showIndex(){
		bst.inorderTraverse();
	}
	
	public static void main(String[] args){
		IndexGenerator ig = new IndexGenerator();
		
		try{
			File file = new File("indexText.txt");
			Scanner scan = new Scanner(file);
			
			ig.buildIndex(scan);
			ig.showIndex();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}