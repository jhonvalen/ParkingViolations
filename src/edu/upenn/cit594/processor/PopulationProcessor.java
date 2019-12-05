package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement.PopulationReader;

public class PopulationProcessor {
	
	protected PopulationReader populationReader;
	protected Map <Integer, Integer> zipPopulations;
	protected Map<Integer, Integer> results = new HashMap<>();
	
	public PopulationProcessor(PopulationReader populationReaderParam) {
		this.populationReader = populationReaderParam;
		this.zipPopulations = this.populationReader.readPopulations();
	}
	
	public int singleZipPopulation(int zip) {
		if (this.zipPopulations.containsKey(zip)) {
			return this.zipPopulations.get(zip);
		} else {
			return -1;
		}
	}
	
	private int totalZipPopulation() {
		int totalPopulation = 0;
		for (Integer zip : this.zipPopulations.keySet()) {
			totalPopulation += this.zipPopulations.get(zip);
		}
		return totalPopulation;
	}
	
	public int calculateTotalPopulation() {
		if (results.containsKey(1)) {
			return results.get(1);
		} else {
			int population = this.totalZipPopulation();
			results.put(1, population);
			return population;
		}
	}

}
