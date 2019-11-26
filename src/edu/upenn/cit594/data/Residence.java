package edu.upenn.cit594.data;

public class Residence {
	
	protected double totalLivableArea;
	protected double marketValue;
	protected long zipCode;
	protected String buildingCode;
	
	public Residence() {
		
	}

	public double getTotalLivableArea() {
		return totalLivableArea;
	}

	public void setTotalLivableArea(double totalLivableArea) {
		this.totalLivableArea = totalLivableArea;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

}
