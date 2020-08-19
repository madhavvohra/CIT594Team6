package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    String line = reader.readLine();
    List<String> list = new ArrayList<>();
    while (line != null) {
      list.add(line);
      line = reader.readLine();
    }
    reader.close();
    return list;
  }
}