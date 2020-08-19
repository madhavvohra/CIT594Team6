package edu.upenn.cit594.ui;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
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
	// below variables are for memoization
	private int answer1 = -1;
	private Map<String, Integer> answer2;
	private Map<String, Double> answer3 = new HashMap<>();
	private Map<String, Double> answer4 = new HashMap<>();
	private Map<String, Double> answer5 = new HashMap<>();
	private double answer6 = -1;

	public UserInterface(Processor processor, String logFileName) {
		this.processor = processor;
		this.logFileName = logFileName;
		in = new Scanner(System.in);
	}

	public void start() {
		System.out.println(
				"Welcome to this awesome program. Please enter a number from 1 - 6 to get the answer for the questions below");
		System.out.println();
		displayOptions();
		Logger log = Logger.getInstance(logFileName);
		int choice = -1;
		choice = catchInvalidOption(choice);
		Scanner zipScanner = new Scanner(System.in);
		while (choice != 0) {
			log.logUsersSelection(choice);
			System.out.println("user choice " + choice);
			if (choice == 1) {
				promptOneDisplay();
			} else if (choice == 2) {
				promptTwoDisplay();
			} else if (choice == 3) {
				System.out.println("Please enter the zipcode");
				String zipcode = zipScanner.next(); // user input
				log.logZipcodeInputted(zipcode);
				promptThreeDisplay(zipcode);
			} else if (choice == 4) {
				System.out.println("Please enter the zipcode");
				String zipcode = zipScanner.next(); // user input
				log.logZipcodeInputted(zipcode);
				promptFourDisplay(zipcode);
			} else if (choice == 5) {
				System.out.println("Please enter the zipcode"); // SHOULD WE CHECK FOR ERROR SCENARIO HERE???
				String zipcode = zipScanner.next();
				log.logZipcodeInputted(zipcode);
				promptFiveDisplay(zipcode);
			} else if (choice == 6) {
				promptSixDisplay();
			} else {
				System.out.println("You entered an invalid number. Program has terminated");
				zipScanner.close();
				System.exit(0);
			}
			displayOptions();
			choice = catchInvalidOption(choice);
		}
		System.exit(0);
	}

	public void promptOneDisplay() {
		answer1 = answer1 == -1 ? processor.promptOne() : answer1;
		System.out.println(answer1);
		printToScreen(answer1);
	}

	public void promptTwoDisplay() {
		answer2 = answer2 == null ? processor.promptTwo() : answer2;
		printToScreen(answer2);
	}

	public void promptThreeDisplay(String zipcode) {
		double display;
		if (answer3.isEmpty() || !answer3.containsKey(zipcode)) {
			display = processor.promptThree(zipcode);
			answer3.put(zipcode, display);
		} else {
			display = answer3.get(zipcode);
		}
		printToScreen(display);
	}

	public void promptFourDisplay(String zipcode) {
		double display;
		if (answer4.isEmpty() || !answer4.containsKey(zipcode)) {
			display = processor.promptFour(zipcode);
			answer4.put(zipcode, display);
		} else {
			display = answer4.get(zipcode);
		}
		printToScreen(display);
	}

	public void promptFiveDisplay(String zipcode) {
		double display;
		if (answer5.isEmpty() || !answer5.containsKey(zipcode)) {
			display = processor.promptFive(zipcode);
			answer5.put(zipcode, display);
		} else {
			display = answer5.get(zipcode);
		}
		printToScreen(display);
	}

	public void promptSixDisplay() {
		answer6 = answer6 == -1 ? processor.promptSix() : answer6;
		printToScreen(answer6);
	}

	/**
	 * Method that takes in a map and prints out the key - value pairs
	 * 
	 * @param map
	 */
	public void printToScreen(Map<String, Integer> map) {

		Set<Entry<String, Integer>> entries = map.entrySet();

		for (Entry<String, Integer> entry : entries) {
			System.out.println(entry.getKey() + ": " + new DecimalFormat("#.####").format(entry.getValue()));
		}

	}

	/**
	 * Takes in a double and prints it to the console
	 * 
	 * @param doubleToPrint
	 */
	public void printToScreen(double doubleToPrint) {
		System.out.println(new DecimalFormat("#.####").format(doubleToPrint));
	}

	/**
	 * Takes in an integer and prints it to the console
	 * 
	 * @param integerToPrint
	 */
	public void printToScreen(int integerToPrint) {
		System.out.println(integerToPrint);
	}

	private void displayOptions() {
		System.out.println("1: Total Population for all zipcodes");
		System.out.println("2: Total Fines per Capita for each zipcode");
		System.out.println("3: Average market value for a zipcode of your choice");
		System.out.println("4: Average livable area for all residences in a zipcode of your choice");
		System.out.println("5: Total residential market value per capital for a zipcode of your choice");
		System.out.println("6: Per capita property value of the zipcode with the maximum parking violations");
	}

	private int catchInvalidOption(int choice) {
		try {
			choice = in.nextInt();
			return choice;
		} catch (InputMismatchException e) {
			System.out.println("wrong input format, system terminated");
			in.close();
			System.exit(0);
		}
		return choice;
	}
}
