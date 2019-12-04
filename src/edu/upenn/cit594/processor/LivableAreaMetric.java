package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Residence;

public class LivableAreaMetric implements ResidenceMetrics{

	@Override
	public int getMetric(Residence r) {
		return r.getResidenceLivableArea();
	}	
}
