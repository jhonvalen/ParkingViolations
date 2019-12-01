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
	
	private int searchResidences(ResidenceNumerator rn, int zip) {
		int numerator = 0;
		for (Residence residence : this.residences) {
			if (residence.getResidenceZipCode() == zip) {
				numerator += rn.getNumerator(residence);
			}
		}
		return numerator;
	}
	
	public int residenceAvgCalculation (ResidenceNumerator rn, int zip) {
		int numerator = this.searchResidences(rn, zip);
		int denominator = this.searchResidences(new ResidenceCountMetric(), zip);
		
		return (int) (numerator/denominator);
	}
	
	public int mktValPerZip(int zip) {
		int total = this.searchResidences(new MarketValueNumerator(), zip);
		int zipPop = this.populationProcessor.singleZipPopulation(zip);
		return total/zipPop;
	}

}
