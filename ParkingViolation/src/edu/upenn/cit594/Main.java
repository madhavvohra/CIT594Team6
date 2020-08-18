package edu.upenn.cit594;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.datamanagement.CSVReader;
import edu.upenn.cit594.datamanagement.JSONReader;
import edu.upenn.cit594.datamanagement.Reader;
import edu.upenn.cit594.datamanagement.TXTReader;
import edu.upenn.cit594.processor.Logger;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {
  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
    // handle not enough args
    if (args.length < 5 || args.length > 5) {
      System.out.println("wrong number of files");
      System.exit(0);
    }
    // handle args[0] about file format
    if (args[0] == null || (!args[0].equals("csv") && (!args[0].equals("json")))) {
      System.out.println("wrong file format");
      System.exit(0);
    }

    // handle args[1] args[2] args[3] for openning and reading files
    String format = args[0];
    String parkingInput = args[1];
    String propertyInput = args[2];
    String populationInput = args[3];
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
    if (logName != null && logName.length() != 0) {
      File file = new File(logName);
      if (file.exists()) {
        file.delete();
      }
      Logger log = Logger.getInstance(logName);
      log.logStart(format, parkingInput, propertyInput, populationInput, logName);
    } else {
      System.out.println("invalid file name for log");
      System.exit(0);
    }

    // set up relationships
    Reader csvReader;
    Logger log = Logger.getInstance(logName);
    List<Violation> violations;
    if (format.equals("csv")) {
      csvReader = new CSVReader(parkingInput);
      log.logInputFileOpened(parkingInput);
      violations = Violation.getListOfViolation(csvReader.getAllInfo());
    } else {
      Reader jsonReader = new JSONReader(parkingInput);
      log.logInputFileOpened(parkingInput);
      violations = Violation.getListOfViolation(jsonReader.getAllInfo());
    }
    csvReader = new CSVReader(propertyInput);
    log.logInputFileOpened(propertyInput);
    System.out.println("start reading " + System.currentTimeMillis());
    List<String> propertyStringList = csvReader.getAllInfo();
    System.out.println("reading finished " + System.currentTimeMillis());
    List<Property> properties = Property.getListOfProperty(propertyStringList);
    System.out.println("parsing finished " + System.currentTimeMillis());
    Reader txtreader = new TXTReader(populationInput);
    log.logInputFileOpened(populationInput);
    List<Population> population = Population.getListofPopulation(txtreader.getAllInfo());
    Processor processor = new Processor(properties, violations, population);
    UserInterface ui = new UserInterface(processor);
    ui.start();
  }
}