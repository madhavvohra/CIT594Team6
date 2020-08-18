package edu.upenn.cit594.ui;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

import edu.upenn.cit594.processor.Logger;
import edu.upenn.cit594.processor.Processor;

import java.util.Map.Entry;
import java.util.Scanner;

public class UserInterface {

	protected Processor processor;
	protected Scanner in;
	protected String logFileName;
	
	public UserInterface(Processor processor, String logFileName) {
		this.processor = processor;
		this.logFileName = logFileName;
		in = new Scanner(System.in);
	}
	
	public void start() {
		System.out.println("Welcome to this awesome program. Please enter a number from 1 - 6 to get the answer for the questions below");
		System.out.println();
		System.out.println("1: Total Population for all zipcodes");
		System.out.println("2: Total Fines per Capita for each zipcode");
		System.out.println("3: Average market value for a zipcode of your choice");
		System.out.println("4: Average livable area for all residences in a zipcode of your choice");
		System.out.println("5: Total residential market value per capital for a zipcode of your choice");
		System.out.println("6: Per capita property valye of the zipcode with the maximum parking violations");
		
		Logger log = Logger.getInstance(logFileName);
		int choice = in.nextInt();
		log.logUsersSelection(choice);
		
		if (choice == 1) {
			promptOneDisplay();
		}
		else if (choice == 2) {
			promptTwoDisplay();
		}
		else if (choice == 3) {
			System.out.println("Please enter the zipcode"); 
			String zipcode = in.next();
			log.logZipcodeInputted(zipcode);
			promptThreeDisplay(zipcode);
		}
		else if (choice == 4) {
			System.out.println("Please enter the zipcode"); 
			String zipcode = in.next();
			log.logZipcodeInputted(zipcode);
			promptFourDisplay(zipcode);
		}
		
		else if (choice == 5) {
			System.out.println("Please enter the zipcode"); // SHOULD WE CHECK FOR ERROR SCENARIO HERE???
			String zipcode = in.next();
			log.logZipcodeInputted(zipcode);
			promptFiveDisplay(zipcode);
		}
		
		else if (choice == 6) {
			promptSixDisplay();
		}
		
		else if (choice == 0) {
			System.exit(0);
		}
		
		else {
			System.out.println("You entered an invalid number. Program has terminated");
			System.exit(0);
		}

	}
	
	public void promptOneDisplay () {
		int display = processor.promptOne();
		printToScreen(display);
	}
	
	public void promptTwoDisplay () {
		Map display = processor.promptTwo();
		printToScreen(display);
	}
	
	public void promptThreeDisplay (String zipcode) {
		double display = processor.promptThree(zipcode);
		printToScreen(display);
	}
	
	public void promptFourDisplay (String zipcode) {
		double display = processor.promptFour(zipcode);
		printToScreen(display);
	}
	
	public void promptFiveDisplay (String zipcode) {
		double display = processor.promptFive(zipcode);
		printToScreen(display);
	}
	
	public void promptSixDisplay () {
		double display = processor.promptSix();
		printToScreen(display);
	}
	
	
	/**
	 * Method that takes in a map and prints out the key - value pairs 
	 * @param numberOfFluTweetsByState
	 */
	public void printToScreen(Map<String, Integer> numberOfFluTweetsByState) {

		Set<Entry<String, Integer>> entries = numberOfFluTweetsByState.entrySet();
		
		for (Entry<String, Integer> entry : entries) {
			System.out.println(entry.getKey() + ": " + new DecimalFormat("#.####").format(entry.getValue()));
		}

	}
	
	/**
	 * Takes in a double and prints it to the console
	 * @param doubleToPrint
	 */
	public void printToScreen(double doubleToPrint) {
		System.out.println(new DecimalFormat("#.####").format(doubleToPrint));
	}
	
	/**
	 * Takes in an integer and prints it to the console
	 * @param integerToPrint
	 */
	public void printToScreen(int integerToPrint) {
		System.out.println(integerToPrint);
	}
}
