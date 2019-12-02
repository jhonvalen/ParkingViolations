package edu.upenn.cit594.processor;

import java.util.Set;

import edu.upenn.cit594.data.Residence;
import edu.upenn.cit594.datamanagement.ResidenceReader;

public class ResidenceProcessor {
	
	protected ResidenceReader residenceReader;
	protected PopulationProcessor populationProcessor;
	protected Set<Residence> residences;
	
	public ResidenceProcessor(ResidenceReader residenceReaderParam, PopulationProcessor pp) {
		this.residenceReader = residenceReaderParam;
		this.populationProcessor = pp;
		this.residences = this.residenceReader.readResidences();
	}
	
	private long getResidenceMetric(ResidenceMetrics rn, int zip) {
		long metric = 0;
		for (Residence residence : this.residences) {
			if (residence.getResidenceZipCode() == zip) {
				metric += rn.getNumerator(residence);
			}
		}
		return metric;
	}
	
	public int residenceAvgCalculation (ResidenceMetrics rm, int zip) {
		long numerator = this.getResidenceMetric(rm, zip);
		long denominator = this.getResidenceMetric(new ResidenceCountMetric(), zip);
		
		if (numerator < 1 || denominator < 1) {
			return 0;
		}
		
		return (int) (numerator/denominator);
	}
	
	public int totalMarketValue(int zip) {
		long total = this.getResidenceMetric(new MarketValueMetric(), zip);
		int zipPop = this.populationProcessor.singleZipPopulation(zip);
		
		if (total < 1 || zipPop < 1) {
			return 0;
		}
		
		return (int) (total/zipPop);
	}

}
