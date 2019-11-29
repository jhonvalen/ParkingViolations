package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Residence;

public class LivableAreaComparator implements ResidenceComparator{

	public LivableAreaComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNumerator(Residence r) {
		// TODO Auto-generated method stub
		return r.getResidenceLivableArea();
	}



	
	
}
