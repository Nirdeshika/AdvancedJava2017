package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.ParserException;

import java.io.File;

/**
 * The main class for the CS410J airline Project2.
 */
public class Project2 {

    /**
     * The entry point for Project2. It checks if arguments and options match the Project requirements. If everything passes,
     * then it creates an airline and a flight object and adds the flight to the airline. Else, it exits gracefully
     * with appropriate error message. It writes the details of the airline optionally based on the option -textFile.
     *
     * @param args It is an array of Strings. It can take any number of Strings. But for the program to work as expected, it should be passed
     *             zero or more of the these three options: -README, -print and -textFile file and 8 other arguments. -textFile and file should be given as two arguments.
     *             For more details, refer
     *             <a href = "http://web.cecs.pdx.edu/~whitlock/pdf/AppClasses.pdf">Project1 Details</a>
     *             <a href = "http://web.cecs.pdx.edu/~whitlock/pdf/TextFile.pdf">Project2 Details</a>
     */
    public static void main(String[] args) {
        Project1 project1 = new Project1();
        project1.processCommandLineArguments(args);

        if (project1.nameOfTheFileFromCommandLine != null) {
            try {
                File file = new File(project1.nameOfTheFileFromCommandLine);

                if (file.exists() && !file.isDirectory()) {
                    new TextParser(project1.nameOfTheFileFromCommandLine).parse();
                    project1.checkIfAirlineNameInTextFileIsSameAsTheOneGivenInTheCommandLine();
                }
            } catch (AirlineNameMismatchException anme) {
                System.out.println(anme.getMessage());
                System.exit(11);
            } catch (ParserException e) {
                System.out.println("Error in format of the file:" + project1.nameOfTheFileFromCommandLine);
            }

            TextDumper textDumper = new TextDumper(project1.nameOfTheFileFromCommandLine);
            textDumper.dump(project1.airline);
        }
    }
}
