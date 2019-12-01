package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Residence;

public class LivableAreaNumerator implements ResidenceNumerator{

	public LivableAreaNumerator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNumerator(Residence r) {
		return r.getResidenceLivableArea();
	}	
}
