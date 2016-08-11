import java.util.*;

/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of Infix Compiler that prints out
	the order of arithmetic operations with a given infix expression

	Strategy
		- Same technique as used in InfixEvaluator on baseline
		- Keep one stack for pushing operators and the other for operands
*/

public class InfixCompiler{
	private char resultSymbol;
	
	// stacks for operands and operators
	private LinkedStack<String> charStack;
	private LinkedStack<String> opStack;
	
	private ParenChecker parenCheck;
	private String[] tokens;
	private String[] operators = {"+", "-", "*", "/", "(", ")"};
	private int[] values = {1, 1, 2, 2, -1, -1};
	
	public InfixCompiler(){
		parenCheck = new ParenChecker();
		resultSymbol = 'z';
	}
	
	public void compile(String exp){
		// check for parentheses balance
		parenCheck.isBalanced(exp);
		
		// tokenize the expression
		tokens = exp.split(" ");
		
		//compile expressions
		compileExpression();
	}
	
	private void compileExpression(){
		charStack = new LinkedStack<String>();
		opStack = new LinkedStack<String>();
		
		for(int i = 0; i < tokens.length; i++){
			String token = tokens[i];
			// token is operator
			if(isOperator(token)){
				processOperator(token);
			}
			// if anything other than operator, push it to the charstack
			else{
				charStack.push(token);
			}
		}
		while(!opStack.empty()){
			getCompiledExpressions();
		}
	}
	
	private void processOperator(String op){
		// if the stack is empty or op is open paren, push
		if(opStack.empty() || op.equals("(")){
			opStack.push(op);
		}
		
		// if stack is not empty and op is close paren
		// pop ops until it sees an open paren.
		// while popping, compile the expressions
		else if(op.equals(")")){
			while(!opStack.peek().equals("(")){
				getCompiledExpressions();
			}
			opStack.pop(); // pop '('
		}
		
		// otherwise
		else{
			int currentValue = getValue(op);
			int topValue = getValue(opStack.peek());
			
			// if current operator has higher precedence, push it
			if(currentValue > topValue){
				opStack.push(op);
			}
			
			// pop until the stack is empty or
			// current value is greater than the top
			else{
				while(!opStack.empty() &&
					currentValue <= topValue){
					getCompiledExpressions();
				}
				opStack.push(op);
			}
		}
	}
	
	private void getCompiledExpressions(){
		String[] cExpression = popStacks();
		print(cExpression);
	}
	
	private int getValue(String op){
		int v = -999;
		for(int i = 0; i < operators.length; i++){
			if(op.equals(operators[i])){
				v = values[i];
			}
		}
		return v;
	}
	
	private void print(String[] exp){
		for(int i = 0; i < exp.length; i++){
			System.out.print(exp[i] + "\t");
		}System.out.println();
	}
	
	private String[] popStacks(){
		String[] cExpression = new String[4];
		try{			
			String operator = opStack.pop();
			String rhs = charStack.pop();
			String lhs = charStack.pop();
			String result = String.valueOf(resultSymbol);
			resultSymbol = (char)((int)resultSymbol - 1);
			
			cExpression[0] = operator;
			cExpression[1] = lhs;
			cExpression[2] = rhs;
			cExpression[3] = String.valueOf(result);
			
			charStack.push(result);
		}catch(EmptyStackException ese){
			System.out.println(ese.getMessage());
		}
		return cExpression;
	}
	
	private boolean isOperator(String token){
		for(int i = 0; i < operators.length; i++){
			if(token.equals(operators[i])){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args){
		InfixCompiler comp = new InfixCompiler();
		
		String[] exp = {"a + b * c - d",
						"( a + b ) * ( c - d )"};
		
		System.out.println("Oper    Op1    Op2    Result");
		comp.compile(exp[1]);
		System.out.println();
		comp.compile(exp[0]);
	}
}