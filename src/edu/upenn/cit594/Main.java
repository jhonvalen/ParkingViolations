package edu.upenn.cit594;
import java.util.List;
import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.datamanagement.CSVViolationReader;
import edu.upenn.cit594.datamanagement.JSONViolationReader;

public class Main {

	public static void main(String[] args) {
		JSONViolationReader testJSON = new JSONViolationReader("parking.json");
		List<Violation> testJSONList = testJSON.getAllViolations();
		
		CSVViolationReader testCSV = new CSVViolationReader("parking.csv");
		List<Violation> testCSVList = testCSV.getAllViolations();
		
		System.out.println("stop here");
	}

}

