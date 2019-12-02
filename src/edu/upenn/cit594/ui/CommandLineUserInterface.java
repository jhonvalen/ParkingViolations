package edu.upenn.cit594.ui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.*;

import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.processor.LivableAreaMetric;
import edu.upenn.cit594.processor.MarketValueMetric;
import edu.upenn.cit594.processor.PopulationProcessor;
import edu.upenn.cit594.processor.ResidenceProcessor;
import edu.upenn.cit594.processor.ViolationProcessor;

public class CommandLineUserInterface {
	
	protected ResidenceProcessor residenceProcessor;
	protected PopulationProcessor populationProcessor;
	protected ViolationProcessor violationProcessor;
	protected HashMap<Integer, Double> avgMarketMap;
	protected HashMap<Integer, Double> avgLivingMap;
	protected HashMap<Integer, Integer> totalMarketMap;
	protected int zipInput = 0;
	
	public CommandLineUserInterface(ResidenceProcessor rp, PopulationProcessor pr, ViolationProcessor vp) {
		this.residenceProcessor = rp;
		this.populationProcessor = pr;
		this.violationProcessor = vp;
		this.avgMarketMap = new HashMap<Integer, Double>();
		this.avgLivingMap = new HashMap<Integer, Double>();
		this.totalMarketMap = new HashMap<Integer, Integer>();
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
				this.zipInput = this.storeZipInput();
				int avgMarketValue = residenceProcessor.residenceAvgCalculation(
						new MarketValueMetric(), this.zipInput);
				System.out.println(avgMarketValue);					
				break;
			case 4:
				this.zipInput = this.storeZipInput();
				int avgLivingArea = residenceProcessor.residenceAvgCalculation(new LivableAreaMetric(), this.zipInput);
				System.out.println(avgLivingArea);
				break;
			case 5:
				this.zipInput = this.storeZipInput();
				int totalResidentialPerCapita = residenceProcessor.totalMarketValue(this.zipInput);
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
	
	private int storeZipInput() {
		Pattern zipPattern = Pattern.compile("^[0-9]{5}$");
		System.out.println("Enter zip code (Formatted to five digits 00000):");
		Scanner in = new Scanner(System.in);			
		String zipInput = in.next();
		Matcher zipMatcher = zipPattern.matcher(zipInput);	
		
		while (!zipMatcher.find() ) {
			System.out.println("You have entered an incorrect zip code.\n"
					+ "Please enter a zip code with 5 digits");
			zipInput = in.next();
			zipMatcher = zipPattern.matcher(zipInput);
		}
		
		int zipInt = Integer.parseInt(zipInput);
		
		in.close();
		
		return zipInt;
	}

}
