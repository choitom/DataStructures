import java.util.EmptyStackException;

/*
	Author	: Tom Choi
	Date	: 08/10/2016
	
	Implementation of Postfix Evaluator
	
	Strategy
		- push numbers
		- if sees an operator,
			pop two operands
			execute the operation
			push the result to the stack
		- pop the final result
*/

public class PostfixEvaluator{
	private LinkedStack<Double> stack;
	private String[] operators = {"+", "-", "*", "/"};
	private String[] tokens;
	
	public PostfixEvaluator(){
		
	}
	
	public double eval(String exp){
		stack = new LinkedStack<Double>();
		tokens = exp.split(" ");
		
		for(int i = 0; i < tokens.length; i++){
			String token = tokens[i];
			
			// token is an operator
			if(isOperator(token)){
				executeOperation(token);
			}
			// convert to double and push
			else{
				try{
					double num = Double.parseDouble(token);
					stack.push(num);
				}catch(NumberFormatException nfe){
					System.out.println(nfe.getMessage());
				}
			}
		}
		double evaled = 0;
		try{
			evaled = stack.pop();
		}catch(EmptyStackException ese){
			System.out.println(ese.getMessage());
		}
		if(!stack.empty()){
			System.err.println("The stack should be empty");
			return 0;
		}
		return evaled;
	}
	
	private void executeOperation(String token){
		try{
			double rhs = stack.pop();
			double lhs = stack.pop();
			execute(token, lhs, rhs);
		}catch(EmptyStackException ese){
			System.out.println(ese.getMessage());
		}
	}
	
	private void execute(String token, double lhs, double rhs){
		double result = 0;
		switch(token){
			case "+":
				result = lhs + rhs;
				break;
			case "-":
				result = lhs - rhs;
				break;
			case "*":
				result = lhs * rhs;
				break;
			case "/":
				result = lhs / rhs;
				break;
		}
		stack.push(result);
	}
	
	private boolean isOperator(String token){
		for(int i = 0; i < operators.length; i++){
			if(token.equals(operators[i])){
				return true;
			}
		}return false;
	}
	
	public static void main(String[] args){
		PostfixEvaluator post = new PostfixEvaluator();
		
		String[] exp = {"1 2 +", "1 2 / 3 +", "1 2 + 1 2 + *"};
		for(int i = 0; i < exp.length; i++){
			System.out.println(post.eval(exp[i]));
		}
	}
}