/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Simple string recursion exercises
*/

public class StringRecursion{
	
	// passes an intermediate value until it reaches the end
	public int lengthTailRecursion(String str, int i){
		if(str == null || str.length() == 0){
			return i;
		}else{
			return lengthTailRecursion(str.substring(1) , i + 1);
		}
	}
	
	public int length(String str){
		if(str == null || str.length() == 0){
			return 0;
		}else{
			return 1 + length(str.substring(1));
		}
	}
	
	public void print(String str){
		if(str == null || str.length() == 0){
			return;
		}else{
			System.out.print(str.charAt(0));
			print(str.substring(1));
		}
	}
	
	public void printReverse(String str){
		if(str == null || str.length() == 0){
			return;
		}else{
			print(str.substring(1));
			System.out.print(str.charAt(0));
		}
	}
	
	public static void main(String[] args){
		StringRecursion sr = new StringRecursion();
		String str = "hello world";
		
		int strLength = sr.length(str);
		System.out.println(strLength); // 11
		
		int strLength2 = sr.lengthTailRecursion(str , 0);
		System.out.println(strLength2);
		
		sr.print(str);	// hello world
		System.out.println();
		sr.printReverse(str); // dlrow olleh
	}
}