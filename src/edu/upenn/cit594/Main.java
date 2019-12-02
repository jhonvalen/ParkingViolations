
package edu.upenn.cit594;

import edu.upenn.cit594.data.Residence;
import edu.upenn.cit594.datamanagement.CSVViolationReader;
import edu.upenn.cit594.datamanagement.JSONViolationReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.ResidenceReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.ResidenceProcessor;
import edu.upenn.cit594.processor.ViolationProcessor;
import edu.upenn.cit594.ui.CommandLineUserInterface;

public class Main {

	public static void main(String[] args) {
		Logger.setFileName("test.txt");
		
		JSONViolationReader testJSON = new JSONViolationReader("parking.json");		
//		CSVViolationReader testCSV = new CSVViolationReader("parking.csv");
		
		ResidenceReader testPropReader = new ResidenceReader("properties.csv");
		PopulationReader popReader = new PopulationReader("population.txt");
		
		PopulationProcessor testPopProc = new PopulationProcessor(popReader);
		ResidenceProcessor testResProc = new ResidenceProcessor(testPropReader, testPopProc);
		ViolationProcessor testViolProc = new ViolationProcessor(testJSON, testPopProc);
		
		CommandLineUserInterface ui = new CommandLineUserInterface(testResProc, testPopProc, testViolProc);
		ui.displayUserInput(5);
	}

}

