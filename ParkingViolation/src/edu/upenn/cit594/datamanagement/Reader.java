package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

public interface Reader {
  public List<String> getAllInfo(String filename) throws FileNotFoundException, IOException, ParseException;
}