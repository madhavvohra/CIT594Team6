package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;

public class AverageTotalLivableArea implements AverageCalculator {

	// Need to solve for error scenario and truncating decimals

	@Override
	public double calculator(String zipCode, List<Property> properties) {
		
		int totalCount = 0;
		double livableArea = 0;

		for (Property property : properties) {
			if (zipCode.equals(property.getZipcode())) {
				livableArea = livableArea + property.getLivableArea();
				totalCount++;
			}
		}

		if (totalCount == 0) {
			return 0;
		}

		else {
			return livableArea / totalCount;
		}
	}

	
	

}
