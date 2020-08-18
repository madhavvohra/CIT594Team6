package edu.upenn.cit594.data;

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

  public static List<Property> getListOfProperty(List<String> info) {
    List<Property> list = new LinkedList<>();
    List<String> header = Arrays.asList(info.get(0).split(",", -1));
    int cols = header.size();
    int marketIdx = header.indexOf("market_value");
    int zipIdx = header.indexOf("zip_code");
    int areaIdx = header.indexOf("total_livable_area");
    final String regex = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
    String[] tokens;
    //TODO
    for (int i = 1; i < info.size()-50000; i++) {
    	String row = info.get(i);
    	tokens = row.split(",", -1);
    	// if parse by "," result in wrong number of tokens, then parse with regex
    	if(tokens.length != cols) {
    		tokens = row.split(regex, -1);   		
    	}
//      System.out.println("regex finishes at " + System.currentTimeMillis());
      String marketValString = tokens[marketIdx];
      String zipString = tokens[zipIdx];
      String livableString = tokens[areaIdx];
      double marketVal = isValid(marketValString) ? Double.parseDouble(marketValString) : 0;
//      System.out.println("market " + marketVal);
      
      String zipcode = isValidZip(zipString);
//      System.out.println("zipcode " + zipcode);
      double livableArea = isValid(livableString) ? Double.parseDouble(livableString) : 0;
//      System.out.println("livable " + livableArea);
      Property property = new Property(zipcode, marketVal, livableArea);
      list.add(property);
    }
    System.out.println("finished reading");
    return list;
  }

  /**
   * check if string s is empty or not digit
   * 
   * @param s
   * @return
   */
  private static boolean isValid(String s) {
    if(s.isBlank()) return false;
    if(s.matches("\\d+\\.?\\d*")) return true;
//    System.out.println(s + " is invalid");
    return false;
  }
  
  private static String isValidZip(String s) {
	  if (s.isBlank() || s.length() < 5) {
		  return "0";
	  }
	  String temp = s.substring(0, 5);
	  if(temp.matches("\\d+")) return temp;
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
