package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class CSVReader implements Reader {

  public CSVReader() {
  }

  /**
   * return a list of string of violation info
   */
  @Override
  public List<String> getAllInfo(String fileName) throws FileNotFoundException, IOException, ParseException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line;
    List<String> list = new LinkedList<>();
    while ((line = reader.readLine()) != null) {
      list.add(line);
    }
    reader.close();
    return list;
  }
}