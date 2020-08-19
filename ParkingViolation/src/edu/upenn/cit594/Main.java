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

    checkArgs(args);
    String format = args[0];
    String parkingInput = args[1];
    String propertyInput = args[2];
    String populationInput = args[3];
    String logName = args[4];
    // check log input and get instance of Log, and log the start of the program
    checkLogInput(logName);

    Logger log = Logger.getInstance(logName);
    log.logStart(format, parkingInput, propertyInput, populationInput, logName);

    setUpRelation(format, parkingInput, propertyInput, populationInput, logName);
  }

  private static void checkArgs(String[] args) {
    // handle not enough args
    if (args.length != 5) {
      System.out.println("wrong number of files");
      System.exit(0);
    }
    // handle args[0] about file format
    if (args[0] == null || (!args[0].equals("csv") && (!args[0].equals("json")))) {
      System.out.println("wrong file format");
      System.exit(0);
    }

    // check if args[1] args[2] args[3] for openning and reading
    File parkingFile = new File(args[1]);
    File propertyFile = new File(args[2]);
    File populationFile = new File(args[3]);
    if (!parkingFile.isFile() || !propertyFile.isFile() || !populationFile.isFile() || !parkingFile.canRead()
        || !populationFile.canRead() || !propertyFile.canRead()) {
      System.out.println("cannot open/read file(s).");
      System.exit(0);
    }
  }

  private static void checkLogInput(String logName) {
    if (logName != null && logName.length() != 0) {
      File file = new File(logName);
      if (file.exists()) {
        file.delete();
      }
    } else {
      System.out.println("invalid file name for log");
      System.exit(0);
    }
  }

  private static void setUpRelation(String format, String parkingInput, String propertyInput, String populationInput,
      String logName) throws FileNotFoundException, IOException, ParseException {

    Reader csvReader = new CSVReader();
    Logger log = Logger.getInstance(logName);
    Reader violationReader = format.equals("csv") ? csvReader : new JSONReader();
    log.logInputFileOpened(parkingInput);
    List<Violation> violations = Violation.getListOfViolation(violationReader.getAllInfo(parkingInput));

    log.logInputFileOpened(propertyInput);
    List<String> propertyStringList = csvReader.getAllInfo(propertyInput);
    List<Property> properties = Property.getListOfProperty(propertyStringList);

    Reader txtreader = new TXTReader();
    log.logInputFileOpened(populationInput);
    List<Population> population = Population.getListofPopulation(txtreader.getAllInfo(populationInput));

    Processor processor = new Processor(properties, violations, population);
    UserInterface ui = new UserInterface(processor, logName);
    ui.start();
  }
}