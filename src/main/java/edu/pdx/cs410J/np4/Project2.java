package edu.pdx.cs410J.np4;

import edu.pdx.cs410J.ParserException;

import java.io.File;

/**
 * The helper class for the CS410J airline Project.
 *
 * @author Nirdeshika Polisetti
 */
public class Project2 {

    /**
     * This method handles printing the details to the text file depending on the options: -pretty and -textFile.
     *
     * @param args The command line arguments.
     */
    void processDumpOptions(String[] args) {
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
                System.exit(11);
            }

            TextDumper textDumper = new TextDumper(project1.nameOfTheFileFromCommandLine);
            textDumper.dump(project1.airline);

            if (project1.getNameOfTheFileFromCommandLineForPrettyPrint != null) {
                PrettyPrinter prettyPrinter = new PrettyPrinter(project1.getNameOfTheFileFromCommandLineForPrettyPrint);
                try {
                    prettyPrinter.dump(new TextParser(project1.nameOfTheFileFromCommandLine).parse());
                } catch (ParserException e) {
                    System.out.println("Error in format of the file:" + project1.nameOfTheFileFromCommandLine);
                    System.exit(11);
                }
            }
        } else if (project1.getNameOfTheFileFromCommandLineForPrettyPrint != null) {
            PrettyPrinter prettyPrinter = new PrettyPrinter(project1.getNameOfTheFileFromCommandLineForPrettyPrint);
            prettyPrinter.dump(project1.airline);
        }
    }
}
