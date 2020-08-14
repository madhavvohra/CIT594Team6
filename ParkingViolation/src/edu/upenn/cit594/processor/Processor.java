package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;

public class Processor {
  protected List<Property> properties;
  protected List<Violation> violations;
  protected List<String> population;

  public Processor(List<Property> properties, List<Violation> violations, List<String> population) {
    this.population = population;
    this.properties = properties;
    this.violations = violations;
  }
}