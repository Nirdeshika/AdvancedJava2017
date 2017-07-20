package edu.pdx.cs410J.np4;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * The helper class for the CS410J airline Project.
 *
 * @author Nirdeshika Polisetti
 */
public class Project1 {
    private String nameOfTheAirLine;
    private int flightNumber = -1;
    private String source = null;
    private String departDate = null;
    private String departTime = null;
    private String destination = null;
    private String arrivalDate = null;
    private String arrivalTime = null;

    private ArrayList<String> options = null;
    String nameOfTheFileFromCommandLine;
    Airline airline;
    private Flight flight;

    /**
     * <p>
     * This method parses the command line arguments and checks for the correct type and format. If any of the
     * arguments do not match the requirements, the program exits gracefully with appropriate error message. If everything passes, then it sets
     * the appropriate variables with the given values.
     * </p>
     *
     * @param args The commandline arguments
     */
    void processCommandLineArguments(String[] args) {
        if (Arrays.asList(args).contains("-README")) {
            System.out.println(getReadMe());
            System.exit(0);
        }

        int countOfArgs = args.length;
        try{
            options = getOptionalArguments(args, countOfArgs);
        }
        catch (IllegalOptionException ioe)
        {
            System.out.println(ioe.getMessage());
            System.exit(9);
        }
        int numberOfOptions = options.size();


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
        try {
            flightNumber = getFlightNumber(args[1 + numberOfOptions]);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            System.exit(2);
        }

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

        airline = new Airline(nameOfTheAirLine);
        flight = new Flight(flightNumber, source, destination, departDate, departTime, arrivalDate, arrivalTime);
        airline.addFlight(flight);

        if (options.contains("-print")) {
            System.out.println(flight);
        }


    }

    /**
     * Returns the flight number after validating it. If it is not a positive integer, it throws an error.
     *
     * @param flightNumberAsString The command line argument corresponding to flight number.
     * @return The flight number
     * @throws IllegalArgumentException if the number is a negative integer or not a number at all.
     */
    static int getFlightNumber(String flightNumberAsString) {
        int flightNumber = 0;
        try {
            flightNumber = Integer.parseInt(flightNumberAsString);
            if (flightNumber < 0) {
                throw new IllegalArgumentException("Invalid flight number. Flight number should be a positive number.");
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid number: " + flightNumberAsString + ". Flight number should be a positive integer.");
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae.getMessage());
        }
        return flightNumber;
    }

    /**
     * Returns a list of Strings containing the options passed to main method via command line.
     *
     * @param args        The command line arguments passed to main method
     * @param countOfArgs Count of number of command line arguments.
     * @return A list of Strings containing the options.
     * @throws IllegalOptionException When -textFile option is followed by an option instead of a file name.
     * @see edu.pdx.cs410J.np4.Project1#processCommandLineArguments(String[])
     */
    private ArrayList<String> getOptionalArguments(String[] args, int countOfArgs) {
        ArrayList<String> options = new ArrayList<>();

        if (countOfArgs > 0 && args[0].charAt(0) == '-') {
            options.add(args[0]);
        }

        if (countOfArgs > 1) {
            if (args[0].equals("-textFile") && args[1].charAt(0) == '-') {
                throw new IllegalOptionException("-textFile option should be followed by a fileName.");
            } else if (args[1].charAt(0) == '-') {
                options.add(args[1]);
            } else if (args[0].equals("-textFile")) {
                options.add(args[1]);
                nameOfTheFileFromCommandLine = args[1];
            }
        }

        if (countOfArgs > 2) {
            if (args[1].equals("-textFile") && args[2].charAt(0) == '-') {
                throw new IllegalOptionException("-textFile option should be followed by a fileName.");
            } else if (args[2].charAt(0) == '-')
                options.add(args[2]);
            else if (args[1].equals("-textFile")) {
                options.add(args[2]);
                nameOfTheFileFromCommandLine = args[2];
            }
        }

        if (countOfArgs > 3)
        {
            if (args[3].charAt(0) == '-')
                options.add(args[3]);
            else if (args[2].equals("-textFile")) {
                options.add(args[3]);
                nameOfTheFileFromCommandLine = args[3];
            }
        }

        return options;
    }

//    private void extractTextFileNameFromCommandLine(String option) {
//        StringTokenizer stringTokenizer = new StringTokenizer(option, " ");
//        if (stringTokenizer.countTokens() != 2) {
//            throw new IllegalOptionException("Please check. There is an error in the textFile option.");
//        }
//        stringTokenizer.nextToken();
//        nameOfTheFileFromCommandLine = stringTokenizer.nextToken();
//    }

    /**
     * To check if the options are valid. If there are any invalid options, it throws an exception.
     *
     * @param options The options passed via command line
     * @throws IllegalOptionException if it contains any option not listed.
     */
    private static void checkValidityOfOptions(ArrayList<String> options) {
        int indexOfFileOption = options.indexOf("-textFile");
        String name = null;
        if (indexOfFileOption != -1)
            name = options.remove(indexOfFileOption + 1);
        if (!Arrays.asList("-README", "-print", "-textFile").containsAll(options)) {
            throw new IllegalOptionException("Please check there is an invalid option. The valid options are -README, -textFile and -print." +
                    "Note that these are case sensitive.");
        }
        if (indexOfFileOption != -1)
            options.add(indexOfFileOption + 1, name);
    }

    /**
     * Returns the README of the project, a brief description of what the project does.
     *
     * @return String containing the hard coded README.
     */
    private static String getReadMe() {
        String readMe = "Name: Nirdeshika Polisetti\n";
        readMe += "Name of the assignment: Project 2\n";
        readMe += "Purpose of the project: This project parses command line arguments to check for the validity of the values. " +
                "If everything is of correct format and type, it creates an airline object and a flight object with the passed in arguments. " +
                "It further adds the flight object to the airline object.\n" +
                "This project also takes three options: -README which prints a brief description of what the project does.\n" +
                "\t\t\t\t\t-print: Prints the details of the flight.\n" +
                "\t\t\t\t\t-textFile file: Adds the details of the airline and flight to the given file.\n";
        readMe += "Command Line Usage: java edu.pdx.cs410J.np4.Project1 [options] <args>\n" +
                "args are (in order)\n" +
                "name : Name of the flight : String\n" +
                "flightNumber: The flight number: positive int\n" +
                "source: A three letter code of departure airport: String containing only characters\n" +
                "departDate: Date on which the flight departs: String of the format mm/dd/yyyy (Month or day can one or two digits but year must be 4 digits.\n" +
                "departTime: Time at which the flight departs: String of HH:mm (Hours can be 1 or digits but minutes should be two digits.\n" +
                "destination: A three letter code of arrival airport: String containing only characters\n" +
                "arrivalDate: Date on which the flight arrives: String of the format mm/dd/yyyy (Month or day can one or two digits but year must be 4 digits.\n" +
                "arrivalTime: Time at which the flight arrives: String of HH:mm (Hours can be 1 or digits but minutes should be two digits.\n\n";
        readMe += "Note: \nIf the String contains a space, it should be enclosed in double quotes.\nDate Strings should also be enclosed in double quotes.\n" +
                "Options should precede args.\nIf the options contains -README, the program prints a brief description of the project and exits. " +
                "It will not do anything else. Even error checking.\nFor this project, we can add only one flight to the airline.\n" +
                "The -textFile option should be followed by the file name, it cannot be followed by an option." +
                "The -textFile option checks if the file given exists; if it doesn't, then it creates a new file and add the details of the airline and its flights to the file.\n" +
                "If the file exists, it checks if the name of the airline in the command line and the file matches; if it does, then adds the details, else exits gracefully with error message.\n" +
                "The file name should not be a directory. It creates a new file only if all the subdirectories in the path exists. It will not create subdirectories.\n" +
                "The format of the file is:\n" +
                "Name of the Airline\n" +
                "FlightNumber|Source Airport Code|DepartDate DepartTime|Destination|ArrivalDate ArrivalTime";


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
    static void checkAirportCodeFormat(String airportCode) {
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
    static void checkDateFormat(String date) {
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
     * @param time Departure time or Arrival time
     * @throws ErroneousDateTimeFormatException
     */
    static void checkTimeFormat(String time) {
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

    /**
     * Returns the name of the airline from the text file passed via commandline. It exits with error message if the file doesn't exists or
     * if there is an error while reading the file.
     *
     * @return The name of the airline as given in the text file.
     */
    String getAirlineNameFromTextFile() {
        String nameOfTheAirlineInTheTextFile = null;
        File file = new File(nameOfTheFileFromCommandLine);
        if (file.exists() && !file.isDirectory()) {
            FileReader fileReader;
            try {
                fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                nameOfTheAirlineInTheTextFile = bufferedReader.readLine();
            } catch (FileNotFoundException e) {
                System.out.println("Error while reading the name of the airline from text file:" + file + " " + e.getMessage());
                System.exit(10);
            } catch (IOException e) {
                System.out.println("Error while reading the name of the airline from text file:" + file + " " + e.getMessage());
                System.exit(10);
            }
        }
        return nameOfTheAirlineInTheTextFile;
    }

    /**
     * Checks if the name of the airline given in the command line matches with the airline name in the textfile provided.
     *
     * @throws AirlineNameMismatchException
     */
    void checkIfAirlineNameInTextFileIsSameAsTheOneGivenInTheCommandLine() {
        String nameOftheAirlineFromTextFile = getAirlineNameFromTextFile();
        if (!nameOftheAirlineFromTextFile.equals(nameOfTheAirLine)) {
            throw new AirlineNameMismatchException("Name of the airline from the text file:" + nameOftheAirlineFromTextFile + " does not match with the name of the airline " +
                    "given in the command line:" + nameOfTheAirLine);
        }

    }

}