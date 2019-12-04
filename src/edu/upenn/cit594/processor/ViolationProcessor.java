package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.datamanagement.ViolationReader;

public class ViolationProcessor {
	
	protected PopulationProcessor populationProcessor;
	protected ViolationReader violationReader;
	protected List<Violation> violations;
	
	public ViolationProcessor(ViolationReader vr, PopulationProcessor pp) {
		this.populationProcessor = pp;
		this.violationReader = vr;
		this.violations = this.violationReader.getAllViolations();
	}
	
	public double totalFinesCalculation(ZipCode zip) {
		return zip.getFineAmount()/zip.getPopulation();
	}
	
	public HashMap<Integer, ZipCode> pairZipObjects() {
		HashMap<Integer, ZipCode> zipMap = new HashMap<Integer, ZipCode>();
		
 		for (Violation violation: violations) {
			int zip = violation.getZipCode();
			double zipFine = violation.getFine();
			int zipPop = this.populationProcessor.singleZipPopulation(zip);
			String plateState = violation.getVehicleState();
			
			if (zipPop != -1 && plateState.equalsIgnoreCase("PA")) {
				ZipCode zipObj = new ZipCode(zip, zipPop, zipFine);
				if (zipMap.containsKey(zip)) {
					double fineAmount = zipMap.get(zip).getFineAmount() + zipFine;
					zipMap.get(zip).setFineAmount(fineAmount);
				} else {
					zipMap.put(zip, zipObj);
				}
			}
		}
		return zipMap;
	} 
	
	public TreeSet<ZipCode> storeZipCodestoTree () {
		TreeSet<ZipCode> zipCodeTree = new TreeSet<ZipCode>();
		
		for (ZipCode zipCode : this.pairZipObjects().values()) {
			double finePerCapita = this.totalFinesCalculation(zipCode);
			finePerCapita = this.truncateDouble(finePerCapita, 4);
			zipCode.setFinePerCapita(finePerCapita);
			zipCodeTree.add(zipCode);
		}
		
		return zipCodeTree;
	}
	
	private double truncateDouble(double num, int decimalPlaces) {
		
		double result = 0;
		
		result = (int) (num * Math.pow(10, decimalPlaces));
		result = (double) result;
		result = result/Math.pow(10, decimalPlaces);
		
		return result;
	}
}
