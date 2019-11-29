package edu.upenn.cit594.data;

import java.util.Date;

public class Violation {
	
	protected Date timeStamp;
	protected double fine;
	protected String reason;
	protected int plateId;
	protected int zipCode;
	protected int violationId;
	protected String vehicleState;
	
	public Violation(Date timeStampParam, double fineParam, String reasonParam, int plateIdParam, 
			int violationIdParam, String vehicleStateParam, int zipCodeParam) {
		
		this.timeStamp = timeStampParam;
		this.fine = fineParam;
		this.reason = reasonParam;
		this.plateId = plateIdParam;
		this.zipCode = zipCodeParam;
		this.violationId = violationIdParam;
		this.vehicleState = vehicleStateParam;		
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getPlateId() {
		return plateId;
	}

	public void setPlateId(int plateId) {
		this.plateId = plateId;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public long getViolationId() {
		return violationId;
	}

	public void setViolationId(int violationId) {
		this.violationId = violationId;
	}

	public String getVehicleState() {
		return vehicleState;
	}

	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}

}
