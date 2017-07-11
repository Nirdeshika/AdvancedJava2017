package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AbstractAirline;

import java.util.ArrayList;
import java.util.Collection;

public class Airline extends AbstractAirline<Flight>
{
    private Collection<Flight> flights = new ArrayList<>();
    private String name = null;

    public Airline(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public Collection<Flight> getFlights() {
        return this.flights;
    }
}
