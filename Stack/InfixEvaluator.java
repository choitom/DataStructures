import java.util.*;

/*
	Author	: Tom Choi
	Date	: 08/10/2016
	
	Implementation of Infix Evaluator
	
	Idea	: Convert an infix expression to postfix
			  Then use PostfixEvaluator to evaluate the expression
*/

public class InfixEvaluator{
	private ParenChecker parenChecker;
	
	// stack for storing operators
	private String[] tokens;
	private PostfixEvaluator postfixEval;
	
	private String[] operators = {"+", "-", "*", "/", ")", "("};
	private int[] precedence = {1, 1, 2, 2, -1, -1};
	
	public InfixEvaluator(){
		postfixEval = new PostfixEvaluator();
		parenChecker = new ParenChecker();
	}
	
	public double eval(String exp){
		checkParenBalance(exp);
		tokens = exp.split(" ");

		String post = infixToPostfix();
		System.out.println(post);
		
		double evaled = postfixEval.eval(post);
		return evaled;
	}
	
	private String infixToPostfix(){
		StringBuilder postfix = new StringBuilder();
		LinkedStack<String> stack = new LinkedStack<String>();
		
		// For each token
		for(int i = 0; i < tokens.length; i++){
			String token = tokens[i];
			
			// If a token is an operator including parentheses
			if(isOperator(token)){
				
				// If the operator stack is empty
				// Or a token is an open parenthesis push the token
				if(stack.empty() || token.equals("(")){
					stack.push(token);
				}
				
				// If stack is not empty
				else{
					
					// If the token is a close parenthesis
					// 	- pop until it sees an open parenthesis
					if(token.equals(")")){
						while(!stack.peek().equals("(")){
							postfix.append(stack.pop());
							postfix.append(' ');
						}
						stack.pop(); // popping "("
					}
					
					// If not a close parenthesis
					//	- if the current token has higher precedence
					//		-> push it to the stack
					//	- otherwise,
					//		-> pop until either stack is empty or
					//		   the current token has higher precedence
					//		   than the top token
					else{
						int topValue =  getValue(stack.peek());
						int currentValue = getValue(token);
						if(currentValue > topValue){
							stack.push(token);
						}else{
							while(!stack.empty() &&
								currentValue <= getValue(stack.peek())){
								postfix.append(stack.pop());
								postfix.append(' ');
							}
							stack.push(token);
						}
					}
				}
			}
			// If a token is not an operator
			// See if it can be converted to a number
			// 	- If not a number, throw an exception
			// 	- Otherwise, add it to the postfix expression
			else{
				try{
					double num = Double.parseDouble(token);
					postfix.append(token);
					postfix.append(' ');
				}catch(NumberFormatException nfe){
					System.out.println(nfe.getMessage());
				}
			}
		}
		
		while(!stack.empty()){
			postfix.append(stack.pop());
			postfix.append(' ');
		}
		
		return postfix.toString();
	}
	
	private int getValue(String op){
		for(int i = 0; i < operators.length; i++){
			if(op.equals(operators[i])){
				return precedence[i];
			}
		}return -999;
	}
		
	private void checkParenBalance(String exp){
		// check for pairs of parentheses
		if(!parenChecker.isBalanced(exp)){
			System.err.println("Parentheses do not match!");
			return;
		}
	}
	
	private boolean isOperator(String token){
		for(int i = 0; i < operators.length; i++){
			if(token.equals(operators[i])){
				return true;
			}
		}return false;
	}
	
	public static void main(String[] args){
		InfixEvaluator infix = new InfixEvaluator();
		String[] exp = {"1 + 1",
						"1 + 2 * 3 + 1",
						"( 1 + 2 ) * ( 2 * ( ( 5 - 1 / 0.1 ) ) ) + ( 1 + 2 * 3 )",
						"( 1 + 2 ) * 2",
						"( 1 + 2 / 4 * 3 ) + 3"};
		
		System.out.println(infix.eval(exp[2]));
		System.out.println(infix.eval(exp[4]));
	}
}