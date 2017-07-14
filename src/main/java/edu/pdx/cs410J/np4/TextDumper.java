package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Nirdeshika Polisetti
 */
public class TextDumper implements AirlineDumper<Airline> {
    private static String SEPARATOR = "||";
    private File file;

    public TextDumper(String fileName) {
        this.file = new File(fileName);
    }

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
