package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AbstractFlight;

import java.text.DateFormat;
import java.util.Date;

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
     * Departure Date time AM/PM
     */
    private Date departTime = null;
    /**
     * Arrival Date Time AM/PM
     */
    private Date arrivalTime = null;

    /**
     * Creates a flight object with the variables set to their corresponding values
     *
     * @param number      Unique identifying number
     * @param source      Source airport's three letter code
     * @param destination Destination airport's three letter code
     * @param departTime  Departure time
     * @param arrivalTime Arrival time
     */
    public Flight(int number, String source, String destination, Date departTime,  Date arrivalTime) {
        this.number = number;
        this.source = source;
        this.destination = destination;
        this.departTime = departTime;
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
     * Returns depart date time am/pm as a String in the format of {@link DateFormat#SHORT}
     *
     * @return Returns depart date time am/pm as a String
     */
    @Override
    public String getDepartureString() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT).format(departTime);
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
     * Returns arrival date time am/pm as a String in the format of {@link DateFormat#SHORT}
     *
     * @return Returns arrival date time am/pm as a String
     */
    @Override
    public String getArrivalString() {
        return DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT).format(arrivalTime);
    }

    /**
     * Returns this flight's departure time as a <code>Date</code>.
     */
    @Override
    public Date getDeparture() {
        return departTime;
    }

    /**
     * Returns this flight's arrival time as a <code>Date</code>.
     */
    @Override
    public Date getArrival() {
        return arrivalTime;
    }
}
