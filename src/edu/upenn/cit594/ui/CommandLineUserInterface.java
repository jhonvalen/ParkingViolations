package edu.upenn.cit594.ui;

import java.util.TreeSet;

import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.processor.LivableAreaNumerator;
import edu.upenn.cit594.processor.MarketValueNumerator;
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
				TreeSet<ZipCode> zipTree = this.violationProcessor.zipCodeFinePerCapita();
				for (ZipCode zipCode : zipTree) {
					System.out.println(zipCode.getZipCode() + " " + String.format("%.4f", zipCode.getFinePerCapita()));
				}
				break;
			case 3:
				double avgMarketValue = residenceProcessor.residenceAvgCalculation(new MarketValueNumerator(), 19137);
				System.out.println(avgMarketValue);
				break;
			case 4:
				double avgLivingArea = residenceProcessor.residenceAvgCalculation(new LivableAreaNumerator(), 19137);
				System.out.println(avgLivingArea);
				break;
			case 5:
				double totalResidentialPerCapita = residenceProcessor.mktValPerZip(19137);
				System.out.println(totalResidentialPerCapita);
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
