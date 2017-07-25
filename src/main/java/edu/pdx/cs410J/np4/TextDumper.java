package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.Collection;
import static edu.pdx.cs410J.np4.AirlineDumperHelper.*;

/**
 * A class that implements AirlineDumper with methods to write the details of the Airline (name + its flights) to a text file.
 *
 * @author Nirdeshika Polisetti
 */
public class TextDumper implements AirlineDumper<Airline> {
    /**
     * The delimiter to be used the text file.
     */
    private static final String SEPARATOR = "|";

    /**
     * The file into which the details of the Airline are to be written.
     */
    private File file;

    /**
     * Creates a TextDumper object. It writes the details of the airline to the file given in the argument.
     *
     * @param fileName Name of the file to which the content are written to.
     */
    public TextDumper(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * It checks if the file exists; if it doesn't, then it creates one and writes to it.
     * If it does, it checks if it is a directory. If it is, then it exits gracefully with an error message.
     * Else, it writes to it. If any of the subdirectories do not exists, then the program exits with an error message.
     *
     * @param airline The airline whose details are to be written in the text file.
     */
    @Override
    public void dump(Airline airline) {
        BufferedWriter bufferedWriter;

        checkIfFileExistsElseCreateIt(file);
        checkIfItIsADirectory(file);

        bufferedWriter = getBufferedWriter(file, true);
        if (bufferedWriter != null) {
            try {
                Collection<Flight> flights;
                flights = airline.getFlights();

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    if (bufferedReader.readLine() == null)
                        bufferedWriter.write(airline.getName() + "\n");
                }
                for (Flight f : flights) {
                    bufferedWriter.write(f.getNumber() + SEPARATOR + f.getSource() + SEPARATOR + f.getDepartureString() + SEPARATOR + f.getDestination() + SEPARATOR + f.getArrivalString() + "\n");
                }

                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("Cannot write to this file. " + e.getMessage());
                System.exit(3);
            }
        }
    }
}
