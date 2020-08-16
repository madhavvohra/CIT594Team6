package edu.upenn.cit594;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.datamanagement.CSVReader;
import edu.upenn.cit594.datamanagement.JSONReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.datamanagement.TXTReader;
import edu.upenn.cit594.processor.Processor;

public class Main {
  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
    // JSONReader reader = new JSONReader("parking.json");
    // List<Violation> list = Violation.getListOfViolation(reader.getAllInfo());
    // Reader populationR = new TXTReader("population.txt");
    // List<Population> population =
    // Population.getListofPopulation(populationR.getAllInfo());
    // for (Population population2 : population) {
    // System.out.println("population is " + population2.getPopulation() + "zip is "
    // + population2.getZipcode());
    // }
    // System.out.println(population.toString());
    CSVReader csv = new CSVReader("properties.csv");
    List<Property> p = Property.getListOfProperty(csv.getAllInfo());
    System.out.println(p.size() + " is the size");
    // List<Violation> list = Violation.getListOfViolation(txtViolation);
    // for (Violation violation : list) {
    // System.out.println(violation.getFine());
    // System.out.println(violation.getPlate());
    // System.out.println(violation.getTimestamp());
    // System.out.println(violation.getZipcode());
    // }
    // System.out.println(list.size());

    // handle no enough args
    if (args.length < 4) {
      System.out.println("no enough file input");
      System.exit(0);
    }
    // handle args[0] about file format
    if (args[0] == null || (!args[0].equals("csv") && (!args[0].equals("json")))) {
      System.out.println("wrong file format");
      System.exit(0);
    }
    String format = args[0];
    String parkingInput = args[1];
    String propertyInput = args[2];
    String populationInput = args[3];
    // handle args[1] args[2] args[3] for openning and reading files
    File parkingFile = new File(args[1]);
    File propertyFile = new File(args[2]);
    File populationFile = new File(args[3]);
    if (!parkingFile.isFile() || !propertyFile.isFile() || !populationFile.isFile() || !parkingFile.canRead()
        || !populationFile.canRead() || !propertyFile.canRead()) {
      System.out.println("cannot open/read file(s).");
      System.exit(0);
    }
    // handle args[4] for log file name, pass it to Log class
    String logName = args[4];
    Logger log;
    if (logName != null && logName.length() != 0) {
      File file = new File(logName);
      if (file.exists()) {
        file.delete();
      }
      log = Logger.getInstance(logName);
      log.write(System.currentTimeMillis() + " " + format + " " + parkingInput + " " + propertyInput + " "
          + populationInput + " " + logName);
    } else {
      System.out.println("invalid file name for log");
      System.exit(0);
    }
    // set up relationships
    Reader csvReader;
    List<Violation> violations;
    if (format.equalsIgnoreCase("csv")) {
      csvReader = new CSVReader(parkingInput);
      log.write();
      violations = Violation.getListOfViolation(csvReader.getAllInfo());
    } else if (format.equalsIgnoreCase("json")) {
      Reader jsonReader = new JSONReader(parkingInput);
      log.write();
      violations = Violation.getListOfViolation(jsonReader.getAllInfo());
    }
    csvReader = new CSVReader(propertyInput);
    log.write();
    List<Property> properties = Property.getListOfProperty(csvReader.getAllInfo());
    Reader txtreader = new TXTReader(populationInput);
    log.write();
    List<Population> population = Population.getListofPopulation(txtreader.getAllInfo());
    Processor processor = new Processor(properties, violations, population);
    CommandLineUI ui = new CommandLineUI(processor);
    ui.Start();
  }
}