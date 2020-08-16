package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public interface AverageCalculator {

	public double calculator(String zipCode, List<Property> properties);
	
}
