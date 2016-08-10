import java.util.EmptyStackException;

public class PostfixEvaluator{
	private LinkedStack<Double> stack;
	private String[] ops = {"+", "-", "*", "/"};
	
	public PostfixEvaluator(){
	
	}
	
	public double eval(String exp){
		stack = new LinkedStack<Double>();
		
		// delimit expression by empty space
		String[] delimited = exp.split(" ");
		
		for(int i = 0; i < delimited.length; i++){
			String e = delimited[i];
			
			// if operator 	-> pop two numbers from the stack
			// 				-> execute operation
			if(isOperator(e)){
				try{
					double rhs = stack.pop();
					double lhs = stack.pop();
					double result = execute(e, lhs, rhs);
					stack.push(result);
				}catch(EmptyStackException ese){
					ese.printStackTrace();
				}
			}else{
				try{
					double num = Double.parseDouble(e);
					stack.push(num);
				}catch(NumberFormatException nfe){
					nfe.printStackTrace();
				}
			}
		}
		double evalExp = stack.pop();
		
		if(!stack.empty()){
			System.err.print("ERROR: The stack should be empty");
			evalExp = -1;
		}
		return evalExp;
	}
	
	
	private double execute(String op, double lhs, double rhs){
		double result = -1;
		switch(op){
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
		return result;
	}
	
	private boolean isOperator(String e){
		for(int i = 0; i < ops.length; i++){
			if(e.equals(ops[i])){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		String exp1 = "4 7 *"; // 4 * 7 = 28
		String exp2 = "4 7 2 + *"; // 4 * (7 + 2) = 36
		String exp3 = "4 7 * 20 -"; // (4 * 7) - 20 = 8
		String exp4 = "3 4 7 * 2 / +"; // 3 + ((4 * 7) / 2) = 17
		String[] exp = {exp1, exp2, exp3, exp4};
		
		PostfixEvaluator postEval = new PostfixEvaluator();
		
		for(int i = 0; i < exp.length; i++){
			System.out.println(postEval.eval(exp[i]));
		}
		
		String err1 = "1 2 3 /";
		System.out.println(postEval.eval(err1));
		
		String err2 = "1 2 + /";
		System.out.println(postEval.eval(err2));
	}
}