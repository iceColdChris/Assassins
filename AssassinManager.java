/*
 * Chris Fahlin
 * 3/5/13
 * AssassinManager.java
 * The main driver for the Assassins linked list
 */
package assassins;

import java.util.*;

/**
 * The base shell used in creating the assassins linked lists
 * @author Chris
 * @version 1.0
 */
public class AssassinManager 
{
	//Instance Fields
	private AssassinNode assassinsHead;
	private AssassinNode graveHead;
	private int assassinCount;
	private int graveCount;
	
	/**
	 * The parameterized constructor for the assassins linked list.
	 * @param name A list of names, used in naming your assassins.
	 */
	public AssassinManager(List<String> name) 
	{
		//Loop through the list 
		for(int i=0; i< name.size(); i++)
		{
			if(assassinsHead == null)//Pointing the first person on the list to the head
			{
				assassinsHead = new AssassinNode(name.get(i));
			}
			else//The head is already pointing to someone
			{
				AssassinNode current = assassinsHead;
				assassinCount = 1;
				
				//Loop through the list until you reach the end assassin
				while(current.getNext() !=null)
				{
					current = current.getNext();
					assassinCount++;//Increment the assassin counter
				}
				
				//Assigns the assassin to the end of the list
				current.setNext(new AssassinNode(name.get(i)));
				 
				
				if (i == name.size() - 1)//Linking the last assassin to the head
				{
					current.getNext().setNext(assassinsHead);
				}
			}
		}
	}
	
	/**
	 * Prints out all the assassins who are still alive.
	 */
	public void printKillRing()
	{
		//Checks for an empty list
		if(assassinsHead != null)
		{
			//Variables
			int counter = 0;
			AssassinNode current = assassinsHead;
			
			//While the next head is not equal to null and the counter is less then or equal to assassin count
			while(current.getNext() != null && counter <= assassinCount)
			{
				System.out.println(current.getName() + " is stalking " + current.getNext().getName());
				current = current.getNext();
				counter++;
			}
		}
		else//Your list is empty
		{
			System.out.println("There is no-one playing the game!");
		}
	}

	/**
	 * Prints out the list of people who are dead,
	 * as well as their killers.
	 */
	public void printGraveyard()
	{
		
		//Checks for an empty list
		if(graveHead != null)
		{
			
			//Variables
			int counter = 0;
			AssassinNode current = graveHead;
			
			//While the next head is not equal to null and the counter is less then or equal to assassin count
			while(current != null && counter <= graveCount-1)
			{
				System.out.println(current.getName() + " was killed by " + current.getKiller());
				current = current.getNext();
				counter++;
			}
			
			
		}
		else//Your list is empty
		{
			System.out.println("No one has died yet!");
		}
	}
	
	/**
	 * Checks if the person is currently in the kill ring
	 * @param name The name of the person you are looking for
	 * @return True: if the person is in the list, False: if they are not
	 */
	public boolean killRingContains(String name)
	{
		//Variables
		AssassinNode current = assassinsHead;
		int count = 0;
		
		//While the current node is not the person you are looking for
		//And you havent looped through the list yet
		while(!current.getNext().getName().equalsIgnoreCase(name) && 
				count <=  assassinCount-1)
		{
			current = current.getNext();
			count++;
		}
		
		//If you find the person you are looking for.
		if(current.getNext().getName().equalsIgnoreCase(name))
		{
			return true;
		}
		else//The person is not in the list
		{
			return false;
		}
	}
	
	/**
	 * Checks if the person is currently in the graveyard.
	 * @param name The person you are looking for
	 * @return True: if the person is in the list, False: if they are not
	 */
	public boolean graveyardContains(String name)
	{
		//Variables
		AssassinNode current = graveHead;
		int count = 0;
		
		//If the head is null and there is no one in the grave 
		if(current == null || graveCount == 0) return false;
		
		//Looping through the list while the next head exists
		while(current.getNext() != null && count <= graveCount - 1)
		{
			//If you find the person you are looking for
			if(current.getNext().getName().equalsIgnoreCase(name))
			{
				return true;
			}
			
			//If you have not reched the person you where looking for
			current = current.getNext();
			count++;
		}
	
		//You did not find the person you where looking for
		return false;
		
	}
	
	/**
	 * Returns if the game is down to one person
	 * @return True if there is only one person left
	 * @return False if there is more than one person left
	 */
	public boolean isGameOver()
	{
		//There is only one person left
		if(assassinCount == 0)
		{
			return true;
		}
		else//People are still playing
		{
			return false;
		}
	}
	
	/**
	 * Prints out the name of the winner
	 * @return the name of the winner
	 */
	public String winner()
	{
		//If there is one person left return their name
		if(assassinCount == 0)
		{
			return assassinsHead.getName();
		}
		else//People are still playing
		{
			return "There is no winner yet!";
		}
	}
	
	/**
	 * Kills the person whos name is written in the method.
	 * @param name The name of the next victim
	 */
	public void kill(String name)
	{	
		terminate(name, assassinsHead);
		assassinCount--;
		graveCount++;
	}		
	
	//Removes the specified assassin
	private AssassinNode terminate(String name, AssassinNode current)
	{
		//If we kill the head
		if(current == assassinsHead && current.getName().equalsIgnoreCase(name))
		{
			AssassinNode last = getLast();
			AssassinNode next = current.getNext();
			current.setKiller(last.getName());//Setting the killer
			graveHead = addToGrave(graveHead, current);//Add the head to the grave
			last.setNext(next);//Set the last to link to the head
			return assassinsHead = next;//Make the next node the head
			
		}
		else if(current.getNext().getName().equalsIgnoreCase(name))//If we kill in the middle or the last
		{
			
			AssassinNode next = current.getNext().getNext();
			current.getNext().setKiller(current.getName());//Sets the killer
			graveHead = addToGrave(graveHead, current.getNext());//Adds the person to the grave
			current.setNext(next);//Set the next person
			return current;
			
		}
		else//If none of them are true
		{
			current = current.getNext();
			return terminate(name, current);
		}
	}
	
	//Adding people to the graveyard
	private AssassinNode addToGrave(AssassinNode current, AssassinNode dead)
	{
		//if the person you are trying to kill is null
		if (dead == null) return null;
		
		//Set the dead person to null
		dead.setNext(null);
		
		if (current == null)
		{
			current = dead;
			
			return current;
		}
		
		//Recurse through adding people through the grave yard
		current.setNext(addToGrave(current.getNext(), dead));
		
		return current;
	}
	
	//Gets the last person in the list
	private AssassinNode getLast()
	{
		
		AssassinNode current = assassinsHead;
		
		//Loop until you reach someone whos linked to the head
		while(current.getNext() != assassinsHead)
		{
			current = current.getNext();
		}
		
		//Return the last person in the list
		return current;
	}
}