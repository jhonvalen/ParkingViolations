package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Residence;

public class MarketValueNumerator implements ResidenceNumerator {

	@Override
	public int getNumerator(Residence r) {
		return r.getResidenceMarketValue();
	}
	
}
