package edu.upenn.cit594.data;

public class Residence {

	private final int marketValue;
	private final int livableArea;
	private final int zipCode;
	private final String name;
	
	public Residence(String name, int marketValue, int livableArea, int zipCode) {
		this.name = name;   
		this.marketValue = marketValue;   
		this.livableArea = livableArea;
		this.zipCode = zipCode;
	}
	
	public int getMarketValue() { return marketValue; }  
	public int getLivableArea() { return livableArea; }  
	public int getZipCode() { return zipCode; }  
	public String getName() { return name; }  
	
	
}

