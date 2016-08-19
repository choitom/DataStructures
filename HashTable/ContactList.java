/**
	Author	: Tom Choi
	Date	: 08/19/2016
	
	Implementation of ContactList using Tree Map
*/

import java.util.*;

public class ContactList{
	private Map<String, List<String>> contactList;
	
	public ContactList(){
		this.contactList = new TreeMap<String, List<String>>();
	}
	
	/**
	* Change the numbers associated with the given name
	* or adds a new entry with this name and list of numbers
	*
	* @param	name		name to look up
	* @param	numbers		numbers to add or change
	* @return	list of numbers replaced; otherwise, null
	*/
	public List<String> addOrChange(String name, List<String> numbers){
		List<String> oldContact = contactList.put(name, numbers);
		return oldContact;
	}
	
	/**
	* Find a list of numbers associated with a name
	*
	* @param	name		name to look up
	* @return	list of numbers associated with a name
	*/
	public List<String> lookupEntry(String name){
		return contactList.get(name);
	}
	
	/**
	* Remove an entry associated with a name
	*
	* @param	name		name to look up
	* @return	an entry removed
	*/
	public List<String> removeEntry(String name){
		return contactList.remove(name);
	}
	
	/**
	* Displays the contact list ordered by name
	*/
	public void display(){
		for(Map.Entry<String, List<String>> entry : contactList.entrySet()){
			System.out.print(entry.getKey() + ": ");
			System.out.println(entry.getValue());
		}
	}
	
	/** Test code */
	public static void main(String[] args){
		ContactList contact = new ContactList();
		List<String> nums = new ArrayList<String>();
		nums.add("123");
		nums.add("456");
		contact.addOrChange("King", nums);
		nums = new ArrayList<String>();
		nums.add("135");
		nums.add("246");
		contact.addOrChange("Tom", nums);
		contact.display();
	}
}