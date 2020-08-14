package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.parser.ParseException;

public class TXTReader implements Reader {
  protected String filename;

  public TXTReader(String filename) {
    this.filename = filename;
  }

  @Override
  public List<String> getAllInfo() throws FileNotFoundException, IOException, ParseException {
    List<String> list = new LinkedList<>();
    BufferedReader reader = new BufferedReader(new FileReader(filename));
    String line;
    while ((line = reader.readLine()) != null) {
      list.add(line);
    }
    reader.close();
    return list;
  }

}
