package edu.upenn.cit594.processor;

import java.util.Set;

import edu.upenn.cit594.data.Residence;
import edu.upenn.cit594.datamanagement.ResidenceReader;

public class ResidenceProcessor {
	
	protected ResidenceReader residenceReader;
	protected Set<Residence> residences;
	
	public ResidenceProcessor(ResidenceReader residenceReaderParam) {
		this.residenceReader = residenceReaderParam;
		this.residences = this.residenceReader.readResidences();
	}
	
	public int searchResidences(ResidenceComparator rc, int zip) {
		int numerator = 0;
		int denominator = 0;
		for (Residence residence : this.residences) {
			if (residence.getResidenceZipCode() == zip) {
				numerator += rc.getNumerator(residence);
				denominator++;
			}
		}
		
		return (int) numerator/denominator;
	}

}
