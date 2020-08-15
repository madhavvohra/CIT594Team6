package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class AverageMarketValueCalculator implements AverageCalculator {

	// Need to solve for error scenario and truncating decimals
	// what if there are no properties in the zipCode? Need to solve for this

	@Override
	public double calculator(String zipCode, List<Property> properties) {

		int totalCount = 0;
		double sumOfPropertyValue = 0;

		for (Property property : properties) {
			if (zipCode.equals(property.getZipcode())) {
				sumOfPropertyValue = sumOfPropertyValue + property.getMarketVal();
				totalCount++;
			}
		}

		if (totalCount == 0) {
			return 0;
		}

		else {
			return sumOfPropertyValue / totalCount;
		}

	}

}
