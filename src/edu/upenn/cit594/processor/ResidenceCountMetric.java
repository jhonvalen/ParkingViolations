package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Residence;

public class ResidenceCountMetric implements ResidenceNumerator {

	protected int residenceCount = 0;
	
	@Override
	public int getNumerator(Residence r) {
		return residenceCount++;
	}
	
	
	
	

}
