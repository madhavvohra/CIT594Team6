package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader implements Reader {
  protected String filename;

  public JSONReader(String filename) {
    this.filename = filename;
  }

  @Override
  public List<String> getAllInfo() throws FileNotFoundException, IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray violations = (JSONArray) parser.parse(new FileReader(filename));
    Iterator iterator = violations.iterator();
    List<String> listOfViolations = new LinkedList<>();

    while (iterator.hasNext()) {
      JSONObject eachViolation = (JSONObject) iterator.next();
      String timestamp = (String)eachViolation.get("date");
      String fine = eachViolation.get("fine").toString();
      String reason = (String)eachViolation.get("violation");
      String plate = (String)eachViolation.get("plate_id");
      String state = (String)eachViolation.get("state");
      String ticketNo = eachViolation.get("ticket_number").toString();
      String zip = (String)eachViolation.get("zip_code");
      zip = (zip == null || zip.isBlank()) ? "0" : zip;
      String violation = timestamp + "," + fine + "," + reason + "," + plate + "," + state + "," + ticketNo + ","
          + zip;
      listOfViolations.add(violation);
    }

    return listOfViolations;
  }

}