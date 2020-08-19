package edu.upenn.cit594.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Property {
	private String zipcode;
	private double marketVal;
	private double livableArea;

	public Property(String zipcode, double marketVal, double livableArea) {
		this.livableArea = livableArea;
		this.marketVal = marketVal;
		this.zipcode = zipcode;
	}

	/**
	 * this method return a list of <Property> from a list of String
	 * 
	 * @param info List<String>
	 * @return
	 */
	public static List<Property> getListOfProperty(List<String> info) {
		List<Property> propertiesList = new ArrayList<>();

		List<String> header = Arrays.asList(info.get(0).split(",", -1));
		int marketIdx = header.indexOf("market_value");
		int zipIdx = header.indexOf("zip_code");
		int areaIdx = header.indexOf("total_livable_area");

		List<String> tokensList = new ArrayList<String>();
		StringBuilder foundString = new StringBuilder();
		boolean inQuotes = false;
		for (int i = 1; i < info.size(); i++) {
			tokensList.clear();
			String tested = info.get(i);
			for (char c : tested.toCharArray()) {
				switch (c) {
					case ',':
						if (inQuotes) {
							foundString.append(c);
						} else {
							tokensList.add(foundString.toString());
							foundString.setLength(0);
						}
						break;
					case '"':
						inQuotes = !inQuotes;
					default:
						foundString.append(c);
				}
			}
			tokensList.add(foundString.toString());
			double marketVal = isValid(tokensList.get(marketIdx));
			String zipcode = isValidZip(tokensList.get(zipIdx));
			double livableArea = isValid(tokensList.get(areaIdx));
			Property property = new Property(zipcode, marketVal, livableArea);
			propertiesList.add(property);
		}
		return propertiesList;
	}

	/**
	 * this method using regex is too slow
	 * 
	 * @param info
	 * @return
	 */
	public static List<Property> getListOfProperty2(List<String> info) {
		List<Property> list = new LinkedList<>();
		List<String> header = Arrays.asList(info.get(0).split(",", -1));
		int cols = header.size();
		int marketIdx = header.indexOf("market_value");
		int zipIdx = header.indexOf("zip_code");
		int areaIdx = header.indexOf("total_livable_area");
		final String regex = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
		String[] tokens;

		for (int i = 1; i < info.size(); i++) {
			String row = info.get(i);
			tokens = row.split(",", -1);
			// if parse by "," result in wrong number of tokens, then parse with regex
			if (tokens.length != cols) {
				tokens = row.split(regex, -1);
			}
			// System.out.println("regex finishes at " + System.currentTimeMillis());
			String marketValString = tokens[marketIdx];
			String zipString = tokens[zipIdx];
			String livableString = tokens[areaIdx];
			double marketVal = isValid(marketValString);
			String zipcode = isValidZip(zipString);
			double livableArea = isValid(livableString);
			Property property = new Property(zipcode, marketVal, livableArea);
			list.add(property);
		}
		System.out.println("finished reading");
		return list;
	}

	/**
	 * validate string, return value in double
	 * 
	 * @param String s
	 * @return Double
	 */
	private static Double isValid(String s) {
		if (s.isBlank())
			return 0.0;
		if (s.matches("\\d+\\.?\\d*"))
			return Double.parseDouble(s);
		// System.out.println(s + " is invalid");
		return 0.0;
	}

	/**
	 * validate zipcode input, if matches, return substring, if not return 0
	 * 
	 * @param String
	 * @return String
	 */
	private static String isValidZip(String s) {
		if (s.isBlank() || s.length() < 5) {
			return "0";
		}
		String temp = s.substring(0, 5);
		if (temp.matches("\\d+"))
			return temp;
		return "0";
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public double getMarketVal() {
		return marketVal;
	}

	public void setMarketVal(Double marketVal) {
		this.marketVal = marketVal;
	}

	public double getLivableArea() {
		return livableArea;
	}

	public void setLivableArea(Double livableArea) {
		this.livableArea = livableArea;
	}
}
