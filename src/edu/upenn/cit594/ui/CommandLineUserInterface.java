package edu.upenn.cit594.ui;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.*;

import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.ResidenceProcessor;
import edu.upenn.cit594.processor.ViolationProcessor;

public class CommandLineUserInterface {
	
	protected ResidenceProcessor residenceProcessor;
	protected PopulationProcessor populationProcessor;
	protected ViolationProcessor violationProcessor;
	protected int zipInput = 0;
	
	public CommandLineUserInterface(ResidenceProcessor rp, PopulationProcessor pr, ViolationProcessor vp) {
		this.residenceProcessor = rp;
		this.populationProcessor = pr;
		this.violationProcessor = vp;
	}
	
	private void runUserInput(int userInput, Scanner in) {		
		switch (userInput) {
			case 0:
				in.close();
				System.exit(0);
				break;
			case 1:
				System.out.println(this.populationProcessor.calculateTotalPopulation());
				break;
			case 2:
				TreeSet<ZipCode> zipTree = this.violationProcessor.getZipCodeFines();
				for (ZipCode zipCode : zipTree) {
					System.out.println(zipCode.getZipCode() + " " + String.format("%.4f", zipCode.getFinePerCapita()));
				}
				break;
			case 3:
				this.zipInput = this.storeZipInput(in);
				long avgMarketValue = residenceProcessor.calculateAvgMarketValue(this.zipInput);
				System.out.println(avgMarketValue);					
				break;
			case 4:
				this.zipInput = this.storeZipInput(in);
				long avgLivingArea = residenceProcessor.calculateAvgLivingArea(this.zipInput);
				System.out.println(avgLivingArea);
				break;
			case 5:
				this.zipInput = this.storeZipInput(in);
				long totalResidentialPerCapita = residenceProcessor.calculateTotalMarketValue(this.zipInput);
				System.out.println(totalResidentialPerCapita);
				break;
			case 6:
				System.out.println(residenceProcessor.calculateFinePopulationCorrelation());
				break;
			default:
				System.out.println("You have entered a value that is not between 0 and 6\n"
						+ "Please enter a number between 0 and 6 to use this program");
				in.close();
				System.exit(0);
				break;
		}
	}
	
	private int storeZipInput(Scanner in) {
		int zipInt = 0;
		Pattern zipPattern = Pattern.compile("^[0-9]{5}$");
		System.out.println("Enter zip code (Formatted to five digits 00000):");
		String zipInput = in.next();
		Matcher zipMatcher = zipPattern.matcher(zipInput);	
				
		if (zipMatcher.find() ) {
			zipInt = Integer.parseInt(zipInput);
		} 
				
		long currentTime = System.currentTimeMillis();
		Logger.getInstance().log(currentTime + " " + zipInput);
		
		return zipInt;
	}
	
	public void displayUserInput() {
		Scanner in = new Scanner(System.in);
		while(true) {
			long currentTime = System.currentTimeMillis();
			System.out.println("Specify what action you would like to run:\n" 
								+ "Enter '0' to exit\n" 
								+ "Enter '1' to show the total population for all Zip Codes\n" 
								+ "Enter '2' to show the total parking fines per capita for each Zip Code\n"
								+ "Enter '3' to show the average market value for residences in a specified Zip Code\n"
								+ "Enter '4' to show the average total livable area for residences in a specified Zip Code\n"
								+ "Enter '5' to show the total residential market value per capita for a specified Zip Code\n"
								+ "Enter '6' to show the correlation between population density and average fine amount for all Zip Codes");
			
			if(!in.hasNextInt()) {
				String inputStr = in.next();
				System.out.println("You have entered a value that is not an integer!" + "\n"
									+ "Enter a number between 0 and 6 to use this program!");
				Logger.getInstance().log(currentTime + " " + inputStr);
				in.close();
				System.exit(0);
			}
			
			int input = in.nextInt();
			Logger.getInstance().log(currentTime + " " + input);
			
			runUserInput(input, in);
		}
				
	}
}
