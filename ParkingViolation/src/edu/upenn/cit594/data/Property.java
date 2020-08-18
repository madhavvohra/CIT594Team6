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
    int marketIdx = header.indexOf("market_value");
    int zipIdx = header.indexOf("zip_code");
    int areaIdx = header.indexOf("total_livable_area");
    final String regex = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
   
    for (int i = 1; i < info.size(); i++) {
      String[] tokens = info.get(i).split(regex, -1);
      String marketValString = tokens[marketIdx];
      String zipString = tokens[zipIdx];
      String livableString = tokens[areaIdx];
      double marketVal = isValid(marketValString) ? Double.parseDouble(marketValString) : 0;
      String zipcode = isValid(zipString) ? zipString.substring(0, 5) : "0";
      double livableArea = isValid(livableString) ? Double.parseDouble(livableString) : 0;
      Property property = new Property(zipcode, marketVal, livableArea);
      list.add(property);
    }
    return list;
  }

  /**
   * check if string s is null or empty or not digit
   * 
   * @param s
   * @return
   */
  private static boolean isValid(String s) {
    //isBlank() is a method supported by java 11
    return (s == null || s.isBlank() || !s.matches("\\d*\\.?\\-?\\d*\\s*")) ? false : true;
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
