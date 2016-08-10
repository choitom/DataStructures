/*
	Implementation of Infix Evaluator
	
	Strategy: Convert infix to postfix using a stack and then evaluate
*/
import java.util.*;

public class InfixEvaluator{
	private StringBuilder postfix;
	private LinkedStack<String> stack;
	private PostfixEvaluator postEval;
	private String[] ops = {"+", "-", "*", "/"};
	private int[] precedence = {1, 1, 2, 2};
	
	public InfixEvaluator(){
		postEval = new PostfixEvaluator();
	}
	
	public double eval(String infix){
		String postfix = convertInfix(infix);
		double result = postEval.eval(postfix);
		return result;
	}
	
	public String convertInfix(String exp){
		stack = new LinkedStack<String>();
		postfix = new StringBuilder();
		
		String[] tokens = exp.split(" ");
		for(int i = 0; i < tokens.length; i++){
			String token = tokens[i];
			//System.out.println("token: " + token + ", isOp?: " + isOperator(token));
			if(isOperator(token)){
				processOperator(token);
			}else{
				// append numbers
				try{
					double num = Double.parseDouble(token);
					postfix.append(token);
					postfix.append(' ');
				}catch(NumberFormatException nfe){
					System.err.println("NumberFormatException: " + nfe.getMessage());
				}
			}
		}
		while(!stack.empty()){
			postfix.append(stack.pop());
			postfix.append(' ');
		}
		System.out.println(postfix.toString());
		return postfix.toString();
	}
	
	private void processOperator(String op){
		// if stack is empty, then push the operator
		if(stack.empty()){
			stack.push(op);
		}else{
			String top = stack.peek();
			int topPrecedence = getPrecedence(top);
			int opPrecedence = getPrecedence(op);
			
			// if the current operator has higher precedence
			// than the one on the top of the stack, push it
			// because we want to execute the higher ones first
			// and then the lower ones after
			if(topPrecedence < opPrecedence){
				stack.push(op);
			}
			
			// while the stack is not empty and the current precedence
			// is less than equal to, pop the top and append to postfix
			// then push the current one
			else{
				while(!stack.empty() &&
					opPrecedence <= getPrecedence(top)){
					postfix.append(stack.pop());
					postfix.append(' ');
					if(!stack.empty()){
						top = stack.peek();
					}
				}
				stack.push(op);
			}
		}
	}
	
	private int getPrecedence(String op){
		int pre = 0;
		for(int i = 0; i < ops.length; i++){
			if(op.equals(ops[i])){
				pre = precedence[i];
				break;
			}
		}return pre;
	}
	
	private boolean isOperator(String op){
		for(int i = 0; i < ops.length; i++){
			if(op.equals(ops[i])){
				return true;
			}
		}return false;
	}
	
	public static void main(String[] args){
		String exp1 = "1 / 2 * 2 + 1"; // 1 2 / 2 * 1 +
		String exp2 = "1 + 2 / 2 * 1"; // 1 2 2 / 1 * +
		String exp3 = "1 + 3 / 3 - 5"; // 1 3 3 / + 5 -
		String exp4 = "1 + 2 + 3 + 4 - 5 / 6 / 1.5 * 2 + 11 - 20 * 30 / 30.5";
		
		InfixEvaluator infixEval = new InfixEvaluator();
		
		System.out.println(infixEval.eval(exp1)); // 2
		System.out.println(infixEval.eval(exp2)); // 2
		System.out.println(infixEval.eval(exp3)); // -3
		System.out.println(infixEval.eval(exp4)); // 0.21675774134
	}
}