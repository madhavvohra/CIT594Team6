package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

public interface Reader<T> {
  public List<T> getAllInfo() throws FileNotFoundException, IOException, ParseException;
}