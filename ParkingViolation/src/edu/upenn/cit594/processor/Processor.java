package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Processor {
	protected List<Property> properties;
	protected List<Violation> violations;
	protected List<Population> population;

	public Processor(List<Property> properties, List<Violation> violations, List<Population> population) {
		this.population = population;
		this.properties = properties;
		this.violations = violations;
	}

	/**
	 * Takes in a list of Population objects and prints the total population in all
	 * the zip codes
	 * 
	 * @param zipCodes
	 */
	public int promptOne(List<Population> zipCodes) {

		int sum = 0;
		for (Population zipCode : zipCodes) {
			sum = sum + zipCode.getPopulation();
		}

		return sum;
	}

	/**
  	 * Prints out the total fine per capita for each Zip Code
  	 */
  	public Map promptTwo (List<Violation> violations, List<Population> zipCodes) {
  		
  		//IMPORTANT: Still need to figure out how to show this as four decimal please - change to double as well
  		
  		HashMap<String, Integer> sumOfFines = new HashMap<>(); // creating HashMap to input the sum of fines in a zipcode
  		for (Violation violation : violations) {
  			if (violation.getState().equals("PA")) {
  				if (sumOfFines.containsKey(violation.getZipcode())) {
  					sumOfFines.put(violation.getZipcode(), violation.getFine() + sumOfFines.get(violation.getZipcode()));
  				}
  				else {
  					if (!violation.getZipcode().equals("0")) {
  						sumOfFines.put(violation.getZipcode(), violation.getFine());

  					}
  				}

  			}
  		}
  		
  		TreeMap<String, Integer> perCapitaFines = new TreeMap<>();
  		
  		// iterating through the population list instead of the HashMap so we can account for the error scenario of zip code not being in the population input file
  		for (Population zipCode : zipCodes) {
  			if (sumOfFines.containsKey(zipCode.getZipcode())) {
  				perCapitaFines.put(zipCode.getZipcode(), sumOfFines.get(zipCode.getZipcode()) / zipCode.getPopulation()); // need to update to get the population number
  			}
  		}
  		
  		
  		// this can be put into the UI layer and this method can return a Map instead of being void
  		Set<Entry<String, Integer>> entries = perCapitaFines.entrySet();
  				
  		for (Entry<String, Integer> entry : entries) {
  			System.out.println(entry.getKey() + " " + entry.getValue());
  		}
  		
  		return perCapitaFines;

  		
  	}

  	/**
  	 * Calculates the avg of an attribute of the properties in a zipcode 
  	 * @param zipCode
  	 * @param properties
  	 * @param avgCalculator
  	 * @return
  	 */
  	public double average (String zipCode, List<Property> properties, AverageCalculator avgCalculator) {
		
  		return avgCalculator.calculator(zipCode, properties);
  		
  	}
	/**
	 * Prints out the average market value of residences in a specific zip code
	 * 
	 * @param zipCode
	 * @param properties
	 */
	public double promptThree(String zipCode, List<Property> properties) {

	
		return average(zipCode, properties, new AverageMarketValueCalculator());

	}

	public double promptFour(String zipCode, List<Property> properties) {

		return average(zipCode, properties, new AverageTotalLivableArea());

	}

	/**
	 * Returns the residential market value per capita of a zip code
	 * @param zipCode
	 * @param properties
	 * @param population
	 * @return
	 */
	public double promptFive(String zipCode, List<Property> properties, List<Population> population) {

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

}
