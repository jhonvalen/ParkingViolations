package edu.upenn.cit594.data;

public class ZipCode {
	
	protected long zipCode;
	protected long population;
	
	public ZipCode(long zip, long pop) {
		this.zipCode = zip;
		this.population = pop;		
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

}
