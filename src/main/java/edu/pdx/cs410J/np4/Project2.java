package edu.pdx.cs410J.np4;

import java.io.File;

public class Project2 {

    public static void main(String[] args) {
        Project1 project1 = new Project1();
        project1.processCommandLineArguments(args);
        if (project1.nameOfTheFileFromCommandLine != null) {
            try {
                File file = new File(project1.nameOfTheFileFromCommandLine);
                if (file.exists() && !file.isDirectory()) {
                    project1.checkIfAirlineNameInTextFileIsSameAsTheOneGivenInTheCommandLine();
                }
            } catch (AirlineNameMismatchException anme) {
                System.out.println(anme.getMessage());
                System.exit(11);
            }

            TextDumper textDumper = new TextDumper(project1.nameOfTheFileFromCommandLine);
            textDumper.dump(project1.airline);
        }
    }
}
