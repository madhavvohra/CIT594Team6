package edu.upenn.cit594.ui;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class UserInterface {

	/**
	 * Method that takes in a map and prints out the key - value pairs 
	 * @param numberOfFluTweetsByState
	 */
	public void printToScreen(Map<String, Integer> numberOfFluTweetsByState) {

		Set<Entry<String, Integer>> entries = numberOfFluTweetsByState.entrySet();
		
		for (Entry<String, Integer> entry : entries) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}
	
	/**
	 * Takes in a double and prints it to the console
	 * @param doubleToPrint
	 */
	public void printToScreen(double doubleToPrint) {
		System.out.println(doubleToPrint);
	}
	
	/**
	 * Takes in an integer and prints it to the console
	 * @param integerToPrint
	 */
	public void printToScreen(int integerToPrint) {
		System.out.println(integerToPrint);
	}
}
