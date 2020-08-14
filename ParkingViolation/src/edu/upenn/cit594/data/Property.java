package edu.upenn.cit594.data;

public class Property {
  private String zipcode;
  private int marketVal;
  private int livableArea;

  public Property(String zipcode, int marketVal, int livableArea) {
    this.livableArea = livableArea;
    this.marketVal = marketVal;
    this.zipcode = zipcode;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public int getMarketVal() {
    return marketVal;
  }

  public void setMarketVal(int marketVal) {
    this.marketVal = marketVal;
  }

  public int getLivableArea() {
    return livableArea;
  }

  public void setLivableArea(int livableArea) {
    this.livableArea = livableArea;
  }
}