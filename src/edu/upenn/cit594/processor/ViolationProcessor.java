package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.datamanagement.ViolationReader;

public class ViolationProcessor {
	
	protected PopulationProcessor populationProc;
	protected ViolationReader violationReader;
	protected List<Violation> violations;
	
	public ViolationProcessor(ViolationReader vr, PopulationProcessor pp) {
		this.populationProc = pp;
		this.violationReader = vr;
		this.violations = this.violationReader.getAllViolations();
	}
	
	public double totalFinesCalculation(ZipCode zip) {
		return zip.getFineAmount()/zip.getPopulation();
	}
	
	private HashMap<Integer, ZipCode> pairZipObjects() {
		HashMap<Integer, ZipCode> zipMap = new HashMap<Integer, ZipCode>();
		
		for (Violation violation: violations) {
			int zip = violation.getZipCode();
			int zipPop = this.populationProc.singleZipPopulation(zip);
			double zipFine = violation.getFine();
			
			ZipCode zipObj = new ZipCode(zip, zipPop, zipFine);
			
			if (zipMap.containsKey(zip)) {
				int population = zipMap.get(zip).getPopulation() + zipPop;
				double fineAmount = zipMap.get(zip).getFineAmount() + zipFine;
				zipMap.get(zip).setFineAmount(fineAmount);
				zipMap.get(zip).setPopulation(population);
			} else {
				zipMap.put(zip, zipObj);
			}
		}
		
		return zipMap;
	} 
	
	public TreeSet<ZipCode> zipCodeFinePerCapita () {
		TreeSet<ZipCode> zipCodeTree = new TreeSet<ZipCode>();
		
		for (ZipCode zipCode : this.pairZipObjects().values()) {
			double finePerCapita = this.totalFinesCalculation(zipCode);
			zipCode.setFinePerCapita(finePerCapita);
			zipCodeTree.add(zipCode);
		}
		
		return zipCodeTree;
	}

}
