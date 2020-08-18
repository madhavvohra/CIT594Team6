package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Processor {
	protected List<Property> properties;
	protected List<Violation> violations;
	protected List<Population> population;
	// protected Logger logger;

	// Not sure if we need logger here or we can handle it all via main
	// ziwei: handled in main, so the the logger instance variable was removed when
	// merge.
	public Processor(List<Property> properties, List<Violation> violations, List<Population> population) {
		this.population = population;
		this.properties = properties;
		this.violations = violations;
		// this.logger = logger;
	}

	/**
	 * Returns the total population in all
	 * the zip codes
	 * 
	 */
	public int promptOne() {

		int sum = 0;
		for (Population pop : population) {
			sum = sum + pop.getPopulation();
		}

		return sum;
	}

	/**
	 * Prints out the total fine per capita for each Zip Code
	 */
	public Map promptTwo() {


		HashMap<String, Integer> sumOfFines = new HashMap<>(); 
		for (Violation violation : violations) {
			if (violation.getState().equals("PA")) {
				if (sumOfFines.containsKey(violation.getZipcode())) {
					sumOfFines.put(violation.getZipcode(),
							violation.getFine() + sumOfFines.get(violation.getZipcode()));
				} else {
					if (!violation.getZipcode().equals("0")) {
						sumOfFines.put(violation.getZipcode(), violation.getFine());

					}
				}

			}
		}

		TreeMap<String, Integer> perCapitaFines = new TreeMap<>();

		// iterating through the population list instead of the HashMap so we can
		// account for the error scenario of zip code not being in the population input
		// file
		for (Population pop : population) {
			if (sumOfFines.containsKey(pop.getZipcode())) {
				perCapitaFines.put(pop.getZipcode(),
						sumOfFines.get(pop.getZipcode()) / pop.getPopulation()); 
			}
		}

		return perCapitaFines;

	}

	/**
	 * Calculates the avg of an attribute of the properties in a zipcode
	 * 
	 * @param zipCode
	 * @param properties
	 * @param avgCalculator
	 * @return
	 */
	public double average(String zipCode, List<Property> properties, AverageCalculator avgCalculator) {

		return avgCalculator.calculator(zipCode, properties);

	}

	/**
	 * Prints out the average market value of residences in a specific zip code
	 * 
	 * @param zipCode
	 * @param properties
	 */
	public double promptThree(String zipCode) {

		return average(zipCode, properties, new AverageMarketValueCalculator());

	}

	public double promptFour(String zipCode) {

		return average(zipCode, properties, new AverageTotalLivableArea());

	}

	/**
	 * Returns the residential market value per capita of a zip code
	 * 
	 * @param zipCode
	 * @param properties
	 * @param population
	 * @return
	 */
	public double promptFive(String zipCode) {

		ArrayList<String> allZipCodes = new ArrayList<>();
		int populationInZipCode = 0;

		for (Population pop : population) {
			allZipCodes.add(pop.getZipcode());
			if (zipCode.equals(pop.getZipcode())) {
				populationInZipCode = pop.getPopulation();
			}
		}

		if (populationInZipCode == 0) {
			return 0;
		}

		double totalMarketValue = 0;

		for (Property property : properties) {
			if (zipCode.equals(property.getZipcode())) {
				totalMarketValue = totalMarketValue + property.getMarketVal();
			}
		}

		return totalMarketValue / populationInZipCode;

	}

	/**
	 * Returns the per capita property value of the PA zipcode with the maximum
	 * parking violations
	 * 
	 * @param violations
	 * @param properties
	 * @param population
	 * @return
	 */
	public double promptSix() {

		HashMap<String, Integer> violationsPerZipcode = new HashMap<>();

		for (Violation violation : violations) {
			if (violation.getState().equals("PA")) {
				String zipcode = violation.getZipcode();
				if (violationsPerZipcode.containsKey(zipcode)) {
					violationsPerZipcode.put(zipcode, violationsPerZipcode.get(zipcode));
				} else {
					violationsPerZipcode.put(zipcode, 1);
				}
			}

		}

		int maxValueInMap = Collections.max(violationsPerZipcode.values()); // Returns max value in the Hashmap
		String maxValueKey = null;

		for (Entry<String, Integer> entry : violationsPerZipcode.entrySet()) {
			if (entry.getValue() == maxValueInMap) {
				maxValueKey = (entry.getKey());
			}
		}

		double perCapitaPropertyValue = promptFive(maxValueKey);

		return perCapitaPropertyValue;

	}

}
