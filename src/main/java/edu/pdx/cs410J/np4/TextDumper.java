package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A class that implements AirlineDumper with methods to write the details of the Airline (name + its flights) to a text file.
 *
 * @author Nirdeshika Polisetti
 */
public class TextDumper implements AirlineDumper<Airline> {
    /**
     * The delimiter to be used the text file.
     */
    private static final String SEPARATOR = "||";

    /**
     * The file into which the details of the Airline are to be written.
     */
    private File file;

    /**
     * Creates a TextDumper object. It writes the details of the airline to the file given in the argument.
     *
     * @param fileName
     */
    public TextDumper(String fileName) {
        this.file = new File(fileName);
    }

    /**
     * It checks if the file exists; if it doesn't, then it creates one and writes to it.
     * If it does, it checks if it is a directory. If it is, then it exits gracefully with an error message.
     * Else, it writes to it. If any of the subdirectories do not exists, then the program exits with an error messge.
     *
     * @param airline The airline whose details are to be written in the text file.
     */
    @Override
    public void dump(Airline airline) {
        BufferedWriter bufferedWriter;

        checkIfFileExistsElseCreateIt();
        checkIfItIsADirectory();

        bufferedWriter = getBufferedWriter();
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

    /**
     * Gives the BufferedWriter object which aids in writing to the text file.
     *
     * @return BufferedWriter object wrapped around FileWriter whose append mode is on.
     */
    private BufferedWriter getBufferedWriter() {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (FileNotFoundException e) {
            checkIfFileExistsElseCreateIt();
        } catch (IOException e) {
            System.out.println("Cannot write to this file. " + e.getMessage());
            System.exit(3);
        }
        return bufferedWriter;
    }

    /**
     * Checks if the file exists. If it does not exists, it creates a new file.
     */
    private void checkIfFileExistsElseCreateIt() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File does not exist and cannot be created. " + file + " : " + e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * Checks if it is a directory.
     *
     */
    private void checkIfItIsADirectory() {
        if (file.isDirectory()) {
            try {
                throw new FileNotFoundException("A file is expected, but a directory is provided.");
            } catch (FileNotFoundException fnfe) {
                System.out.println(fnfe.getMessage());
                System.exit(2);
            }

        }
    }
}
