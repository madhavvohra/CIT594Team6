package edu.upenn.cit594.data;

import java.util.LinkedList;
import java.util.List;

public class Violation {
  private String timestamp;
  private int fine;
  private String reason;
  private int plate;
  private String state;
  private int ticketNo;
  private String zipcode;

  public Violation(String timestamp, int fine, String reason, int plate, String state, int ticketNo, String zipcode) {
    this.timestamp = timestamp;
    this.fine = fine;
    this.reason = reason;
    this.plate = plate;
    this.state = state;
    this.ticketNo = ticketNo;
    this.zipcode = zipcode;
  }

  public Violation(int ticketNo) {
    this.ticketNo = ticketNo;
  }

  @Override
  public String toString() {
    return getTimestamp() + " " + getFine() + " " + getReason() + " " + getPlate() + " " + getState() + " "
        + getTicketNo() + " " + getZipcode();
  }

  /**
   * this method convert of list of string of violation info into a
   * List<Violation>
   * 
   * @param List<String> info
   * @return List<Violation>
   */
  public static List<Violation> getListOfViolation(List<String> info) {
    List<Violation> list = new LinkedList<>();
    for (String s : info) {
      String[] split = s.split(",", -1);
      String timestamp = split[0];
      int fine = Integer.parseInt(split[1]);
      String reason = split[2];
      int plate = Integer.parseInt(split[3]);
      String state = split[4];
      int ticketNo = Integer.parseInt(split[5]);
      //       
      //.isBlank() is a method supported since JAVA 11
      //
      String zip = split[6];
      zip = (zip.isBlank()) ? "0" : zip;
      Violation violation = new Violation(timestamp, fine, reason, plate, state, ticketNo, zip);
      list.add(violation);
    }
    return list;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public int getFine() {
    return fine;
  }

  public String getReason() {
    return reason;
  }

  public int getPlate() {
    return plate;
  }

  public String getState() {
    return state;
  }

  public int getTicketNo() {
    return ticketNo;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public void setFine(int fine) {
    this.fine = fine;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public void setPlate(int plate) {
    this.plate = plate;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setTicketNo(int ticketNo) {
    this.ticketNo = ticketNo;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

}
