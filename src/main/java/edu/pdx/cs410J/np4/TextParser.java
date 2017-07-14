package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.IllegalFormatException;
import java.util.StringTokenizer;

public class TextParser implements AirlineParser<Airline> {
    private File file;
    private Airline airline = null;

    private int flightNumber;
    private String source;
    private String destination;
    private String departDate;
    private String departTime;
    private String arrivalDate;
    private String arrivalTime;
    private String departureString;
    private String arrivalString;

    public TextParser(String fileName) {
        this.file = new File(fileName);
    }

    @Override
    public Airline parse() throws ParserException {

        StringTokenizer stringTokenizer;
        BufferedReader bufferedReader;
        checkIfFileExists();
        checkIfItIsAFile();

        bufferedReader = getBufferedReader();
        if (bufferedReader != null) {
            try {
                String airlineName = bufferedReader.readLine();
                stringTokenizer = getStringTokenizer(airlineName);
                checkIfItIsInTheCorrectFormat(stringTokenizer, true);
                airlineName = stringTokenizer.nextToken();
                airline = new Airline(airlineName);

                String flightObjectDetails;
                while ((flightObjectDetails = bufferedReader.readLine()) != null) {
                    stringTokenizer = getStringTokenizer(flightObjectDetails);
                    checkIfItIsInTheCorrectFormat(stringTokenizer, false);

                    if (stringTokenizer.hasMoreTokens()) {
                        flightNumber = Project1.getFlightNumber(stringTokenizer.nextToken());
                    } else {
                        throw new IllegalFileFormatException("Not enough details about flight.");
                    }

                    if (stringTokenizer.hasMoreTokens()) {
                        source = stringTokenizer.nextToken();
                        Project1.checkAirportCodeFormat(source);
                    } else {
                        throw new IllegalFileFormatException("Not enough details about flight.");
                    }

                    if (stringTokenizer.hasMoreTokens()) {
                        departureString = stringTokenizer.nextToken();
                        setDateAndTime(departureString, true);
                    } else {
                        throw new IllegalFileFormatException("Not enough details about flight.");
                    }

                    if (stringTokenizer.hasMoreTokens()) {
                        destination = stringTokenizer.nextToken();
                        Project1.checkAirportCodeFormat(destination);
                    } else {
                        throw new IllegalFileFormatException("Not enough details about flight.");
                    }

                    if (stringTokenizer.hasMoreTokens()) {
                        arrivalString = stringTokenizer.nextToken();
                        setDateAndTime(arrivalString, false);
                    } else {
                        throw new IllegalFileFormatException("Not enough details about flight.");
                    }

                    Flight flight = new Flight(flightNumber, source, destination, departDate, departTime, arrivalDate, arrivalTime);
                    airline.addFlight(flight);
                }
            } catch (IOException e) {
                System.out.println("Cannot read from this file. " + e.getMessage());
                System.exit(6);
            } catch (IllegalFileFormatException iffe) {
                System.out.println(iffe.getMessage());
                System.exit(7);
            }
        }

        return airline;
    }

    private void setDateAndTime(String string, boolean isDeparture) {
        StringTokenizer st = new StringTokenizer(string, " ");
        if (st.countTokens() != 2) {
            throw new IllegalFileFormatException("Format not correct: " + string);
        }
        if (isDeparture) {
            departDate = st.nextToken();
            Project1.checkDateFormat(departDate);

            departTime = st.nextToken();
            Project1.checkTimeFormat(departTime);
        } else {
            arrivalDate = st.nextToken();
            Project1.checkDateFormat(departDate);

            arrivalTime = st.nextToken();
            Project1.checkTimeFormat(departTime);
        }

    }

    private void checkIfFileExists() {
        if (!file.exists())
            try {
                throw new FileNotFoundException("Cannot find file: " + file);
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage());
                System.exit(4);
            }
    }

    private void checkIfItIsAFile() {
        if (file.isDirectory()) {
            try {
                throw new FileNotFoundException("A file is expected, but a directory is provided.");
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage());
                System.exit(5);
            }

        }
    }

    private BufferedReader getBufferedReader() {
        FileReader fileReader;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            System.exit(4);
        }
        return bufferedReader;
    }

    private StringTokenizer getStringTokenizer(String string) {
        return new StringTokenizer(string, "||");
    }

    private void checkIfItIsInTheCorrectFormat(StringTokenizer stringTokenizer, boolean isAirlineName) {
        if (isAirlineName && stringTokenizer.countTokens() != 1) {
            throw new IllegalFileFormatException("Error while parsing Airline name. Format of the file: " + file + " is not valid. Please check.");
        }
        if (!isAirlineName && stringTokenizer.countTokens() != 5) {
            throw new IllegalFileFormatException("Error while parsing flight object. Format of the file: " + file + " is not valid. Please check.");
        }
    }
}
