/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of Dutch National Flag with the following restrictions
		- for a particular i, I can query the item
		- for two indices i, j, I can swap
*/

import java.util.Random;

public class DutchNationalFlag{
	
	private static char[] arr;
	
	private static char query(int index){
		return arr[index];
	}
	
	private static void swap(int i, int j){
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void sort(){
		int i = 0;
		int j = 0;
		int k = arr.length - 1;
		while(j <= k){
			char c = query(j);
			if(c == 'A'){
				swap(i, j);
				i++;
				j++;
			}else if(c == 'B'){
				j++;
			}else{
				swap(j,k);
				k--;
			}
			print();
		}
	}
	
	public static void print(){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}System.out.println();
	}
	
	public static void main(String[] args){
		Random rand = new Random();
		arr = new char[10];
		for(int i = 0; i < arr.length; i++){
			char randChar = (char)(rand.nextInt(3) + 65);
			arr[i] = randChar;
		}
		sort();
	}
}