package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Residence;

public class ResidenceCountMetric implements ResidenceMetrics {

	@Override
	public int getNumerator(Residence r) {
		return 1;
	}
	
	
	
	

}
