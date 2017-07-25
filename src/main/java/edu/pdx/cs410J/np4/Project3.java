package edu.pdx.cs410J.np4;

/**
 * The main class of Project 3
 *
 * @author Nirdeshika Polisetti
 * @version 1.0
 */
public class Project3 {
    /**
     * The entry point of Project 3. It checks if arguments and options match the Project requirements. If everything passes,
     * then it creates an airline and a flight object and adds the flight to the airline. Else, it exits gracefully
     * with appropriate error message. It writes the details of the airline optionally based on the option -textFile/-pretty.
     *
     * @param args It is an array of Strings. It can take any number of Strings. But for the program to work as expected, it should be passed
     *             zero or more of the these three options: -README, -print and -textFile file and 8 other arguments. -textFile and file should be given as two arguments.
     *             For more details, refer
     *             <a href = "http://web.cecs.pdx.edu/~whitlock/pdf/AppClasses.pdf">Project1 Details</a>
     *             <a href = "http://web.cecs.pdx.edu/~whitlock/pdf/TextFile.pdf">Project2 Details</a>
     *             <a href = "http://web.cecs.pdx.edu/~whitlock/pdf/PrettyPrint.pdf">Project3 Details</a>
     */
    public static void main(String[] args)
    {
        Project2 project2 = new Project2();
        project2.processDumpOptions(args);
    }
}
