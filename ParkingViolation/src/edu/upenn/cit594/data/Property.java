package edu.upenn.cit594.data;

public class Property {
  private int zipcode;
  private int marketVal;
  private int livableArea;

  public Property(int zipcode, int marketVal, int livableArea) {
    this.livableArea = livableArea;
    this.marketVal = marketVal;
    this.zipcode = zipcode;
  }

  public int getZipcode() {
    return zipcode;
  }

  public void setZipcode(int zipcode) {
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