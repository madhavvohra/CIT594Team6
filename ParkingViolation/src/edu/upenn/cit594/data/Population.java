package edu.upenn.cit594.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Population {
  private String zipcode;
  private int population;

  public Population(String zipcode, int population) {
    this.population = population;
    this.zipcode = zipcode;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public static List<Population> getListofPopulation(List<String> info) {
    List<Population> list = new ArrayList<>();
    for (String string : info) {
      String[] split = string.split("\\s+", -1);
      String zip = split[0];
      int pop = Integer.parseInt(split[1]);
      Population population = new Population(zip, pop);
      list.add(population);
    }
    return list;
  }
}
