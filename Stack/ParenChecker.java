/*
	Implementation of Parenthesis Checker from
	DataStructures(Koffman & Wolfgang p.156)
*/

public class ParenChecker{
	private LinkedStack<Character> stack;
	
	public ParenChecker(){
		stack = new LinkedStack<Character>();
	}
	
	public boolean isBalanced(String str){
		boolean balanced = true;
		
		/*
			If sees an open paren,
				- push it to the stack
			If sees a close paren,
				- pop an open paren from the stack
				- see if the open and close paren match
				- if not match: not balacned, break
		*/
		
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if(isOpenParen(c)){
				stack.push(c);
			}else if(isCloseParen(c)){
				char o = stack.pop();
				if(!balance(c, o)){
					balanced = false;
					break;
				}
			}
		}

		if(balanced && stack.empty()){
			System.out.println("The expression\n\t" + str + "\nis balanced");
			return true;
		}else{
			System.out.println("The expression\n\t" + str + "\nis not balanced");
			return false;
		}
	}
	
	public boolean balance(char c, char o){
		if ((c == ')' && o == '(') ||
			(c == '}' && o == '{') ||
			(c == ']' && o == '[')){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isOpenParen(char c){
		if(c == '(' || c == '{' || c == '['){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isCloseParen(char c){
		if(c == ')' || c == '}' || c == ']'){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args){
		String exp1 = "(w * (x + y) / z - (p / (r - q)))";
		String exp2 = "(w * [x + y] / z - [p / {r - q}])";
		String exp3 = "(w * [x + y) / z - [p / {r - q}])";
		
		ParenChecker pc = new ParenChecker();
		System.out.println(pc.isBalanced(exp1));
		System.out.println(pc.isBalanced(exp2));
		System.out.println(pc.isBalanced(exp3));
	}
}