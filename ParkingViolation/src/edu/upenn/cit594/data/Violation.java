package edu.upenn.cit594.data;

import java.sql.Timestamp;

public class Violation {
  private Timestamp timestamp;
  private int fine;
  private String reason;
  private int plate;
  private String state;
  private int ticketNo;
  private int zipcode;

  public Violation(Timestamp timestamp, int fine, String reason, int plate, String state, int ticketNo, int zipcode) {
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

  public Timestamp getTimestamp() {
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

  public int getZipcode() {
    return zipcode;
  }

  public void setTimestamp(Timestamp timestamp) {
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

  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }

}