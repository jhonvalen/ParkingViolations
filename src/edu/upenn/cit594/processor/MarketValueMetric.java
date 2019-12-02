package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Residence;

public class MarketValueMetric implements ResidenceMetrics {

	@Override
	public int getNumerator(Residence r) {
		return r.getResidenceMarketValue();
	}
	
}
