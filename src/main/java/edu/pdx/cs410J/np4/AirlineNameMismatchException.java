package edu.pdx.cs410J.np4;

/**
 * An unchecked exception used to throw an exception when the Airline name in the file and command line does not match.
 *
 * @author Nirdeshika Polisetti
 * @version 1.0
 */
public class AirlineNameMismatchException extends RuntimeException {
    /**
     * Creates an AirlineNameMismatchException object with the input message.
     *
     * @param message The message to be displayed when the exception occurs.
     */
    public AirlineNameMismatchException(String message) {
        super(message);
    }
}
