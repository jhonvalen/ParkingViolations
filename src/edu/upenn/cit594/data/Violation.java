package edu.upenn.cit594.data;

import java.util.Date;

public class Violation {
	
	protected Date timeStamp;
	protected double fine;
	protected String reason;
	protected long plateId;
	protected long zipCode;
	protected long violationId;
	protected String vehicleState;
	
	public Violation(Date timeStampParam, double fineParam, String reasonParam, long plateIdParam, 
			long violationIdParam, String vehicleStateParam, long zipCodeParam) {
		
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

	public long getPlateId() {
		return plateId;
	}

	public void setPlateId(long plateId) {
		this.plateId = plateId;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public long getViolationId() {
		return violationId;
	}

	public void setViolationId(long violationId) {
		this.violationId = violationId;
	}

	public String getVehicleState() {
		return vehicleState;
	}

	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}

}
