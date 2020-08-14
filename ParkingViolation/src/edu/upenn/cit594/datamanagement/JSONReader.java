package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
      String timestamp = eachViolation.get("date").toString();
      int fine = Integer.parseInt(eachViolation.get("fine").toString());
      String reason = eachViolation.get("violation").toString();
      int plate = Integer.parseInt(eachViolation.get("plate_id").toString());
      String state = eachViolation.get("state").toString();
      int ticketNo = Integer.parseInt(eachViolation.get("ticket_number").toString());
      String zip = (String) eachViolation.get("zip_code");
      int zipcode = (zip == null || zip.isEmpty()) ? 0 : Integer.parseInt(zip.toString());
      String violation = timestamp + "," + fine + "," + reason + "," + plate + "," + state + "," + ticketNo + ","
          + zipcode;
      listOfViolations.add(violation);
    }

    return listOfViolations;
  }

}