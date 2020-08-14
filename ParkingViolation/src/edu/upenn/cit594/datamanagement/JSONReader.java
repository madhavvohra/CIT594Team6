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

import edu.upenn.cit594.data.Violation;

public class JSONReader implements Reader<Violation> {
  protected String filename;

  public JSONReader(String filename) {
    this.filename = filename;
  }

  @Override
  public List<Violation> getAllInfo() throws FileNotFoundException, IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray violations = (JSONArray) parser.parse(new FileReader(filename));
    Iterator iterator = violations.iterator();
    // LOG
    List<Violation> listOfViolations = new LinkedList<>();

    while (iterator.hasNext()) {
      JSONObject eachViolation = (JSONObject) iterator.next();
      String timestamp = eachViolation.get("date").toString();
      int fine = Integer.parseInt(eachViolation.get("fine").toString());
      String reason = eachViolation.get("violation").toString();
      int plate = Integer.parseInt(eachViolation.get("plate_id").toString());
      String state = eachViolation.get("state").toString();
      int ticketNo = Integer.parseInt(eachViolation.get("ticket_number").toString());
      int zipcode = Integer.parseInt(eachViolation.get("zip_code").toString());
      Violation violation = new Violation(timestamp, fine, reason, plate, state, ticketNo, zipcode);
      listOfViolations.add(violation);
    }

    return listOfViolations;
  }

}