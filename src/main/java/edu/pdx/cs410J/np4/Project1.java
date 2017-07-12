package edu.pdx.cs410J.np4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * The main class for the CS410J airline Project.
 *
 * @author Nirdeshika Polisetti
 */
public class Project1 {

    /**
     * <p>
     * The entry point of the project. This method parses the command line arguments and checks for the correct type and format. If any of the
     * arguments does not match the requirements, the programs throws an exception and exits gracefully. If everything passes, then it sets
     * the appropriate variables with the given values.
     * </p>
     *
     * @param args It is an array of Strings. It can take any number of Strings. But for the program to work as expected, it should be passed
     *             zero or more of the these two options: -README and -print and 8 other arguments. For more details, refer
     *             <a href = "http://web.cecs.pdx.edu/~whitlock/pdf/AppClasses.pdf">Project1 Details</a>
     */
    public static void main(String[] args) {
        String nameOfTheAirLine;
        int flightNumber = -1;
        String source = null;
        String departDate = null;
        String departTime = null;
        String destination = null;
        String arrivalDate = null;
        String arrivalTime = null;

        int countOfArgs = args.length;
        ArrayList<String> options = getOptionalArguments(args, countOfArgs);
        int numberOfOptions = options.size();

        if (options.contains("-README")) {
            System.out.println(getReadMe());
            return;
        }

        if (numberOfOptions != 0) {
            try {
                checkValidityOfOptions(options);
            } catch (IllegalOptionException ioe) {
                System.out.println(ioe.getMessage());
                System.exit(9);
            }
        }

        try {
            checkNumberOfArguments(countOfArgs, numberOfOptions);
        } catch (ErroneousNumberOfArgumentsException mae) {
            System.out.println(mae.getMessage());
            System.exit(1);
        }

        nameOfTheAirLine = args[0 + numberOfOptions];
        flightNumber = getFlightNumber(args[1 + numberOfOptions]);

        try {
            checkAirportCodeFormat(args[2 + numberOfOptions]);
            source = args[2 + numberOfOptions];
        } catch (IllegalAirportCodeException iace) {
            System.out.println("Invalid source. " + iace.getMessage());
            System.exit(3);
        }

        try {
            checkDateFormat(args[3 + numberOfOptions]);
            departDate = args[3 + numberOfOptions];
        } catch (ErroneousDateTimeFormatException edte) {
            System.out.println("Error in depart date. " + edte.getMessage());
            System.exit(4);
        }

        try {
            checkTimeFormat(args[4 + numberOfOptions]);
            departTime = args[4 + numberOfOptions];
        } catch (ErroneousDateTimeFormatException edte) {
            System.out.println("Error in depart time. " + edte.getMessage());
            System.exit(5);
        }

        try {
            checkAirportCodeFormat(args[5 + numberOfOptions]);
            destination = args[5 + numberOfOptions];
        } catch (IllegalAirportCodeException iace) {
            System.out.println("Invalid destination. " + iace.getMessage());
            System.exit(6);
        }

        try {
            checkDateFormat(args[6 + numberOfOptions]);
            arrivalDate = args[6 + numberOfOptions];
        } catch (ErroneousDateTimeFormatException edte) {
            System.out.println("Error in arrival date. " + edte.getMessage());
            System.exit(7);
        }

        try {
            checkTimeFormat(args[7 + numberOfOptions]);
            arrivalTime = args[7 + numberOfOptions];
        } catch (ErroneousDateTimeFormatException edte) {
            System.out.println("Error in arrival time. " + edte.getMessage());
            System.exit(8);
        }

        Airline airline = new Airline(nameOfTheAirLine);
        Flight flight = new Flight(flightNumber, source, destination, departDate, departTime, arrivalDate, arrivalTime);
        airline.addFlight(flight);

        if (options.contains("-print")) {
            System.out.println(flight);
        }


    }

    /**
     * Returns the flight number after validating it. If it is not a number or if it is a negative integer, it throws an error.
     *
     * @param flightNumberAsString The command line argument corresponding to flight number.
     * @return The flight number
     * @throws NumberFormatException    if the passed argument is not a valid number.
     * @throws IllegalArgumentException if the number is a negative integer.
     */
    private static int getFlightNumber(String flightNumberAsString) {
        int flightNumber = 0;
        try {
            flightNumber = Integer.parseInt(flightNumberAsString);
            if (flightNumber < 0) {
                throw new IllegalArgumentException("Invalid flight number. Flight number should be a positive number.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number: " + flightNumberAsString + ". Flight number should be a positive integer.");
            System.exit(2);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            System.exit(2);
        }
        return flightNumber;
    }

    /**
     * Returns a list of Strings containing the options passed to main method via command line.
     *
     * @param args        The command line arguments passed to main method
     * @param countOfArgs Count of number of command line arguments.
     * @return A list of Strings containing the options.
     * @see edu.pdx.cs410J.np4.Project1#main(String[])
     */
    private static ArrayList<String> getOptionalArguments(String[] args, int countOfArgs) {
        ArrayList<String> options = new ArrayList<>();

        if (countOfArgs > 0 && args[0].charAt(0) == '-') {
            options.add(args[0]);
        }
        if (countOfArgs > 1 && args[1].charAt(0) == '-') {
            options.add(args[1]);
        }

        return options;
    }

    /**
     * To check if the options are valid. If there are any ivalid options, it throws an exception.
     *
     * @param options The options passed via command line
     * @throws IllegalOptionException
     */
    private static void checkValidityOfOptions(ArrayList<String> options) {
        if (!Arrays.asList("-README", "-print").containsAll(options)) {
            throw new IllegalOptionException("Please check there is an invalid option. The valid options are -README and -print." +
                    "Note that these are case sensitive.");
        }

    }

    /**
     * Returns the README of the project, a brief description of what the project does.
     *
     * @return String containing the hard coded README.
     */
    private static String getReadMe() {
        String readMe = "Name: Nirdeshika Polisetti\n";
        readMe += "Name of the assignment: Project 1\n";
        readMe += "Purpose of the project: This project parses command line arguments to check for the validity of the values. " +
                "If everything is of correct format and type, it creates an airline object and a flight object with the passed in arguments. " +
                "It further adds the flight object to the airline object.\n" +
                "This project also takes two options: -README which prints a brief description of what the project does.\n" +
                "\t\t\t\t     -print: Prints the details of the flight.\nNote that these are case-sensitive.";
        readMe += "Command Line Usage: java edu.pdx.cs410J.np4.Project1 [options] <args>\n" +
                "args are (in order)\n" +
                "name : Name of the flight : String\n" +
                "flightNumber: The flight number: int\n" +
                "source: A three letter code of departure airport: String containing only characters\n" +
                "departDate: Date on which the flight departs: String of the format mm/dd/yyyy (Month or day can one or two digits but year must be 4 digits.\n" +
                "departTime: Time at which the flight departs: String of HH:mm (Hours can be 1 or digits but minutes should be two digits.\n" +
                "destination: A three letter code of arrival airport: String containing only characters\n" +
                "arrivalDate: Date on which the flight arrives: String of the format mm/dd/yyyy (Month or day can one or two digits but year must be 4 digits.\n" +
                "arrivalTime: Time at which the flight arrives: String of HH:mm (Hours can be 1 or digits but minutes should be two digits.\n\n";
        readMe += "Note: \nIf the String contains a space, it should be enclosed in double quotes.\nDate Strings should also be enclosed in double quotes.\n" +
                "Options should precede args.\nIf the options contains -README, the program prints a brief description of the project and exits. " +
                "It will not do anything else. Even error checking.\nFor this project, we can add only one flight to the airline.";


        return readMe;
    }

    /**
     * Checks if there are correct number of arguments required. If not, throws an exception.
     *
     * @param countOfArgs     Number of command line arguments
     * @param numberOfOptions Number of options passed in command line
     * @throws ErroneousNumberOfArgumentsException
     */
    private static void checkNumberOfArguments(int countOfArgs, int numberOfOptions) {
        if (countOfArgs - numberOfOptions < 8)
            throw new ErroneousNumberOfArgumentsException("Please Check! Some of the arguments are missing.");
        if (countOfArgs - numberOfOptions > 8)
            throw new ErroneousNumberOfArgumentsException("Please Check! There are some extra arguments.");
    }

    /**
     * Checks if the input is string of three characters. If not, throws an exception.
     *
     * @param airportCode A String of three lettered code for departure or arrival airport.
     * @throws IllegalAirportCodeException
     */
    private static void checkAirportCodeFormat(String airportCode) {
        if (!airportCode.matches("[a-zA-z][a-zA-Z][a-zA-Z]"))
            throw new IllegalAirportCodeException("Please check. Not a valid airport code: " + airportCode + ". Airport code should be a three lettered string.");
    }

    /**
     * Checks if the input String is of the format mm/dd/yyyy. Month and day can be 1 or digits, but year should be of 4 digits.
     * It also checks if the input is a valid date. If not, throws an exception.
     *
     * @param date Departure date or Arrival date
     * @throws ErroneousDateTimeFormatException
     */
    private static void checkDateFormat(String date) {
        if (!date.matches("\\d{1,2}/\\d{1,2}/\\d{4}"))
            throw new ErroneousDateTimeFormatException("Please check. Invalid date format: " + date);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            throw new ErroneousDateTimeFormatException("Please check. Invalid date : " + date);
        }
    }

    /**
     * Checks if the input string is of the format HH:mm. Hours can be 1 or 2 digits, but minutes should be 2 digits.
     * It also checks if it is a valid time. If not, it throws an exception.
     *
     * @param time
     * @throws ErroneousDateTimeFormatException
     */
    private static void checkTimeFormat(String time) {
        if (!time.matches("\\d{1,2}:\\d{2}"))
            throw new ErroneousDateTimeFormatException("Please check. Invalid time format: " + time);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setLenient(false);
        try {
            sdf.parse(time);
        } catch (ParseException e) {
            throw new ErroneousDateTimeFormatException("Please check. Invalid time: " + time);
        }
    }

}