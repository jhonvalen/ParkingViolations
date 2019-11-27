package edu.upenn.cit594.data;

public class Residence {
	
	private final int residenceMarketValue;
	private final int residenceLivableArea;
	private final int residenceZipCode;
	private final String residenceName;
	
	public Residence(String residenceName, int residenceMarketValue, int residenceLivableArea, int residenceZipCode) {
		this.residenceName = residenceName;   
		this.residenceMarketValue = residenceMarketValue;   
		this.residenceLivableArea = residenceLivableArea;
		this.residenceZipCode = residenceZipCode;
	}
	
	public int getResidenceMarketValue() { return residenceMarketValue; }  
	public int getResidenceLivableArea() { return residenceLivableArea; }  
	public int getResidenceZipCode() { return residenceZipCode; }  
	public String getResidenceName() { return residenceName; }  
	
	
}
