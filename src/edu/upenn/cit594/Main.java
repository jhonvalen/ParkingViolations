
package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.CSVViolationReader;
import edu.upenn.cit594.datamanagement.JSONViolationReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.ResidenceReader;
import edu.upenn.cit594.datamanagement.ViolationReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.ResidenceProcessor;
import edu.upenn.cit594.processor.ViolationProcessor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class Main {

	public static void main(String[] args) {

		if(args.length == 5) {
			long startTime = System.currentTimeMillis();
			String violationFormat = args[0].toLowerCase().trim();
			String violationName = args[1].toLowerCase().trim();
			String residenceName = args[2].toLowerCase().trim();
			String populationName = args[3].toLowerCase().trim();
			String logName = args[4].toLowerCase().trim();			
			
			Logger.setFileName(logName);
			Logger.getInstance().log(startTime + " " + violationFormat + " " + violationName
									+ " " + residenceName + " " + populationName + " " + logName);
			
			ViolationReader violationReader = null;
			if (violationFormat.equals("json")) {
				violationReader = new JSONViolationReader(violationName);
			} else if (violationFormat.equals("csv")) {
				violationReader = new CSVViolationReader("parking.csv");
			} else {
				if(violationFormat.equalsIgnoreCase("json") || violationFormat.equalsIgnoreCase("csv")) {
					System.out.println("The format specified must be entered in lower case");
					System.out.println("Enter 'json' or 'csv' in lower case as the first argument to continue");
				} else {
					System.out.println("The format entered is not 'json' or 'csv'!");
					System.out.println("Enter 'json' or 'csv' as the first argument to continue.");
				}
				System.exit(0);
			}
			
			ResidenceReader residenceReader = new ResidenceReader(residenceName);
			PopulationReader populationReader = new PopulationReader(populationName);
			
			PopulationProcessor populationProcessor = new PopulationProcessor(populationReader);
			ViolationProcessor violationProcessor = new ViolationProcessor(violationReader, populationProcessor);
			ResidenceProcessor residenceProcessor = new ResidenceProcessor(residenceReader, violationProcessor, populationProcessor);
			
			CommandLineUserInterface ui = new CommandLineUserInterface(residenceProcessor, populationProcessor, violationProcessor);
			
			ui.displayUserInput();
		} else if (args.length < 5) {
			exitProgram("few");
		} else {
			exitProgram("many");
		}
		
	}
	
	private static void exitProgram(String argumentQty) {
		System.out.println("You have entered too " + argumentQty + " arguments!");
		System.out.println("This program requires the following arguments to run:" + "\n" + ""
				+ "1. Format of the parking violations file" + "\n" + "2. Name of the parking violations file" + "\n"
				+ "3. Name of the residences input file" + "\n" + "4. Name of the population input file" + "\n" + 
				"5. Name of the log file" + "\n" + "Please enter the arguments in the above order to continue!");
		System.exit(0);
	}

}

