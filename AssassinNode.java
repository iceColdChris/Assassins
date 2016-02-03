/*
 * Chris Fahlin
 * 3/4/12
 * AssassinNode.java
 * A node class for the assassin link list
 */
package assassins;

/**
 * A node class for the assassin link list
 * @author Chris
 * @version 1.0
 */
public class AssassinNode 
{
	//Constants
	private String name;
	private String killer;
	private AssassinNode next;
	
	/**
	 * Creates an assassin node
	 * @param name the name of the person to be a node
	 * 
	 */
	public AssassinNode(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the name instance field.
	 * @return the name of a person
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Sets the name instance field
	 * @param name - sets a new name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Returns the killer instance field
	 * @return the name of the killer
	 */
	public String getKiller() 
	{
		return killer;
	}

	/**
	 * Sets the  killer
	 * @param killer - the name of the killer
	 */
	public void setKiller(String killer) 
	{
		this.killer = killer;
	}

	/**
	 * Gets the next node of the link list
	 * @return the next node in the link list
	 */
	public AssassinNode getNext() 
	{
		return next;
	}

	/**
	 * Sets the next node of the link list.
	 * @param next - the next node in your link list
	 */
	public void setNext(AssassinNode next) 
	{
		this.next = next;
	}
}