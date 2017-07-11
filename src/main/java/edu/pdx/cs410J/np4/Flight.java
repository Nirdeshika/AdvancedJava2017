package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {

  private int number = 0;
  private String source = null;
  private String destination = null;
  private String departDate = null;
  private String departTime = null;
  private String arrivalDate = null;
  private String arrivalTime = null;

  public Flight(int number, String source, String destination, String departDate, String departTime, String arrivalDate, String arrivalTime) {
    this.number = number;
    this.source = source;
    this.destination = destination;
    this.departDate = departDate;
    this.departTime = departTime;
    this.arrivalDate = arrivalDate;
    this.arrivalTime = arrivalTime;
  }

  @Override
  public int getNumber() {
    return number;
  }

  @Override
  public String getSource() {
    return source;
  }

  @Override
  public String getDepartureString() {
    return departDate + " " + departTime;
  }

  @Override
  public String getDestination() {
    return destination;
  }

  @Override
  public String getArrivalString() {
    return arrivalDate + " " + arrivalTime;
  }
}
