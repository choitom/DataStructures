/*
	Author	: Tom Choi
	Date	: 08/11/2016
	
	Implementation of a recursive linear search algorithm
	before diving into a binary search algorithm
*/

public class LinearSearch<E>{
	private E[] lst;
	
	public LinearSearch(E[] lst){
		this.lst = lst;
	}
	
	public E linearSearch(int index, E c){
		if(index >= lst.length){
			System.err.println("The item \'" + c + "\' does not exist");
			return null;
		}else if(lst[index].equals(c)){
			return lst[index];
		}else{
			return linearSearch(++index, c);
		}
	}
	
	public static void main(String[] args){
		Character[] chars = new Character[52];
		chars[0] = 'A';
		
		for(int i = 1; i < chars.length; i++){
			chars[i] = (char)((int)chars[i-1] + 1);
		}
		
		LinearSearch<Character> search = new LinearSearch<Character>(chars);
		
		try{
			char index = search.linearSearch(0, '(');
			System.out.println(index);
		}catch(NullPointerException npe){
			System.out.println(npe.getMessage());
		}
	}
}