package edu.upenn.cit594.processor;

import java.io.File;
import java.io.PrintWriter;

public class Logger {

	private PrintWriter out;

	private Logger(String filename) {
		try {
			out = new PrintWriter(new File(filename));
		} catch (Exception e) {
			System.out.println("There was an error with logging to the file");
		}
	}

	private static Logger instance = null;

	public static Logger getInstance(String fileName) {
		if (instance == null) {
			instance = new Logger(fileName);
		}
		return instance;
	}

	/**
	 * Method that writes the start time of the program and all the runtime
	 * arguments
	 * 
	 * @param parkingViolationFormat
	 * @param parkingViolationFileName
	 * @param propertyValuesFileName
	 * @param populationFileName
	 * @param logFileName
	 */
	public void logStart(String parkingViolationFormat, String parkingViolationFileName, String propertyValuesFileName,
			String populationFileName, String logFileName) {
		out.println(System.currentTimeMillis() + " " + parkingViolationFormat + " " + parkingViolationFileName + " "
				+ propertyValuesFileName + " " + populationFileName + " " + logFileName);
		out.flush();
	}

	/**
	 * Method that writes the time an input file was opened and the name of the file
	 * 
	 * @param filename
	 */
	public void logInputFileOpened(String filename) {
		out.println(System.currentTimeMillis() + " " + filename);
		out.flush();
	}

	/**
	 * Method that write the time the user made a selection and the user selection
	 * 
	 * @param choice
	 */
	public void logUsersSelection(int choice) {
		out.println(System.currentTimeMillis() + " " + choice);
		out.flush();
	}

	/**
	 * Method that write the time and the zipcode inputted by the user
	 * 
	 * @param zipcode
	 */
	public void logZipcodeInputted(String zipcode) {
		out.println(System.currentTimeMillis() + " " + zipcode);
		out.flush();
	}

}