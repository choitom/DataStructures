public interface ContactListInterface{
	
	/**
	* Change the numbers associated with the given name
	* or adds a new entry with this name and list of numbers
	*
	* @param	name		name to look up
	* @param	numbers		numbers to add or change
	* @return	list of numbers replaced; otherwise, null
	*/
	public List<String> addOrChange(String name, List<String> numbers);
	
	/**
	* Find a list of numbers associated with a name
	*
	* @param	name		name to look up
	* @return	list of numbers associated with a name
	*/
	public List<String> lookupEntry(String name);
	
	/**
	* Remove an entry associated with a name
	*
	* @param	name		name to look up
	* @return	an entry removed
	*/
	public List<String> removeEntry(String name);
	
	/**
	* Displays the contact list ordered by name
	*/
	public void display();
}