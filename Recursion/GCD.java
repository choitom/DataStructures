/*
	Greatest Common Divisor
		- gcd(m, n) = n if n is a divisor of m
		- gcd(m, n) = gcd(n, m%n) if n isn't a divisor of m
*/

public class GCD{
	public GCD(){}
	
	public int gcd(int m, int n){
		if(m % n == 0){
			return n;
		}else if(n > m){
			return gcd(n, m);
		}else{
			return gcd(n, m%n);
		}
	}
	
	public static void main(String[] args){
		GCD gcd = new GCD();
		System.out.println(gcd.gcd(32, 20));
	}
}