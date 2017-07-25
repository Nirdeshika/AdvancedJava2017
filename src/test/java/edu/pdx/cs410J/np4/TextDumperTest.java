//package edu.pdx.cs410J.np4;
//
//import org.junit.Test;
//
//import java.io.IOException;
//
///**
// * Unit tests for the {@link TextDumper} class.
// *
// * @author Nirdeshika Polisetti
// */
//
//public class TextDumperTest {
//
//    private TextDumper getTextDumperObject(String filename) {
//        return new TextDumper(filename);
//    }
//
//
//    private Airline getAirlineObject() {
//        Airline airline = new Airline("Delta");
//        Flight flight = new Flight(1, "SEA", "PDX", "3/1/2017", "1:09", "3/1/2017", "2:09");
//        airline.addFlight(flight);
//        return airline;
//    }
//
//    @Test
//    public void dumpsIntoATxtFile() {
//        Airline airline = getAirlineObject();
//        getTextDumperObject("dumpingFile.txt").dump(airline);
//    }
//
//    @Test
//    public void dumpsWhenCalledWithDirectory()
//    {
//        getTextDumperObject("/Users/priyathamanisetty/airline/edu").dump(getAirlineObject());
//    }
//
//    @Test
//    public void dumpsWhenCalledWithNonExistingDirectory()
//    {
//        getTextDumperObject("/Users/priyathamanisetty/airline/nonExistingDirectory/edu").dump(getAirlineObject());
//    }
//
//}
