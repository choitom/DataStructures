import java.util.*;

/*
	Implementation of PalindromeFinder using a stack
	(DataStructures Koffman & Wolfgang p155)
*/

public class PalindromeFinder{
	private String inputStr;
	private LinkedStack<Character> stack;
	
	public PalindromeFinder(){
		this.inputStr = null;
		this.stack = new LinkedStack<Character>();
	}
	
	public boolean isPalindrome(String input){
		inputStr = input;
		fillStack();
		String result = popStack();
		return inputStr.equalsIgnoreCase(result);
	}
	
	private void fillStack(){
		for(int i = 0; i < inputStr.length(); i++){
			stack.push(inputStr.charAt(i));
		}
	}
	
	private String popStack(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < inputStr.length(); i++){
			builder.append(stack.pop());
		}
		return builder.toString();
	}
	
	public static void main(String[] args){
		String input = "kayak";
		PalindromeFinder p = new PalindromeFinder();
		
		System.out.println(p.isPalindrome("kayak"));
		System.out.println(p.isPalindrome("I saw I was I"));
		System.out.println(p.isPalindrome("Able was I ere I saw Elba"));
		System.out.println(p.isPalindrome("watermelon"));
	}
}