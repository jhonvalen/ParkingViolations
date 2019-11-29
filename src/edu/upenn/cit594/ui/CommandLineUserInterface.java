package edu.upenn.cit594.ui;

import edu.upenn.cit594.processor.LivableAreaComparator;
import edu.upenn.cit594.processor.MarketValueComparator;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.ResidenceProcessor;
import edu.upenn.cit594.processor.ViolationProcessor;

public class CommandLineUserInterface {
	
	protected ResidenceProcessor residenceProcessor;
	protected PopulationProcessor populationProcessor;
	protected ViolationProcessor violationProcessor;
	
	public CommandLineUserInterface(ResidenceProcessor rp, PopulationProcessor pr, ViolationProcessor vp) {
		this.residenceProcessor = rp;
		this.populationProcessor = pr;
		this.violationProcessor = vp;
	}
	
	public void displayUserInput(int userInput) {
		
		switch (userInput) {
			case 1:
				System.out.println(this.populationProcessor.totalZipPopulation());
				break;
			case 2:
				System.out.println(this.violationProcessor.zipCodeFinePerCapita());
				break;
			case 3:
				double avgMarketValue = residenceProcessor.searchResidences(new MarketValueComparator(), 19137);
				System.out.println(avgMarketValue);
				break;
			case 4:
				double avgLivingArea = residenceProcessor.searchResidences(new LivableAreaComparator(), 19137);
				System.out.println(avgLivingArea);
				break;
			case 5:
				System.out.println("user entered 5");
				break;
			case 6:
				System.out.println("user entered 6");
				break;
			default:
				System.out.println("user entered invalid number");
				break;
		}
			
		
		
	}

}
