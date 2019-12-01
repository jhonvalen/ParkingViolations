package edu.upenn.cit594.data;

public class ZipCode implements Comparable<ZipCode>{
	
	protected int zipCode;
	protected int population;
	protected double fineAmount;
	protected double finePerCapita;

	public ZipCode(int zip, int pop, double fine) {
		this.zipCode = zip;
		this.population = pop;
		this.fineAmount = fine;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}

	@Override
	public int compareTo(ZipCode o) {
		return Integer.compare(this.getZipCode(), o.getZipCode());
	}

	public double getFinePerCapita() {
		return finePerCapita;
	}

	public void setFinePerCapita(double finePerCapita) {
		this.finePerCapita = finePerCapita;
	}
	
}
