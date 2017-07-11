package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AbstractFlight;

/**
 * This class represents a concrete flight class. It has a unique identifying number, a source and a destination airport identified by a three-letter code,
 * a departure date and time, and an arrival date and time.
 *
 * @author Nirdeshika Polisetti
 * @version 1.0
 */
public class Flight extends AbstractFlight {

    /**
     * Unique identifying number
     */
    private int number = 0;
    /**
     * Source airport's code
     */
    private String source = null;
    /**
     * Destination airport's code
     */
    private String destination = null;
    /**
     * Departure date
     */
    private String departDate = null;
    /**
     * Departure time
     */
    private String departTime = null;
    /**
     * Arrival date
     */
    private String arrivalDate = null;
    /**
     * Arrival time
     */
    private String arrivalTime = null;

    /**
     * Creates a flight object with the variables set to their corresponding values
     *
     * @param number      Unique identifying number
     * @param source      Source airport's three letter code
     * @param destination Destination airport's three letter code
     * @param departDate  Departure date
     * @param departTime  Departure time
     * @param arrivalDate Arrival date
     * @param arrivalTime Arrival time
     */
    public Flight(int number, String source, String destination, String departDate, String departTime, String arrivalDate, String arrivalTime) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departDate = departDate;
        this.departTime = departTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Returns the flight's unique identifying number
     *
     * @return Unique identifying number
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Returns the source airport's code
     *
     * @return A three lettered airport code for the source.
     */
    @Override
    public String getSource() {
        return source;
    }

    /**
     * Concatenates departure date and time.
     *
     * @return Returns departure date and time as a single string
     */
    @Override
    public String getDepartureString() {
        return departDate + " " + departTime;
    }

    /**
     * Returns the destination airport's code
     *
     * @return A three lettered airport code for the destination.
     */
    @Override
    public String getDestination() {
        return destination;
    }

    /**
     * Concatenates arrival date and time.
     *
     * @return Returns arrival date and time as a single string
     */
    @Override
    public String getArrivalString() {
        return arrivalDate + " " + arrivalTime;
    }
}
