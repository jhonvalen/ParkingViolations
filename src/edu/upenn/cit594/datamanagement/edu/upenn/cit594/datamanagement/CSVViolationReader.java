package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import edu.upenn.cit594.data.Violation;

public class CSVViolationReader {
	
	protected String filename;
	
	public CSVViolationReader(String name) {
		this.filename = name;
	}
	
	public List<Violation> getAllViolations() {
		File file = new File(this.filename);
		ArrayList<Violation> violations = new ArrayList<Violation>();
		boolean exists = file.exists();
		boolean read = file.canRead();
		if (exists == true && read == true) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(this.filename));
				String line = "";
				while ((line = br.readLine()) != null) {
					String[] violationData = line.split(",", -1);
					
					String strDate = violationData[0];
					String strFine = violationData[1];
					String reason = violationData[2];
					String strPlateID = violationData[3];
					String state = violationData[4];
					String strTicketNum = violationData[5];
					String strZip = violationData[6];
					
					if (!reason.equals("") && !strPlateID.equals("") && !strFine.equals("") && !strTicketNum.equals("") &&
							!state.equals("") && !strZip.equals("") && !strDate.equals("")) {
						long plateID = Long.parseLong(strPlateID);
						double fine = Double.parseDouble(strFine);
						long ticketNum = Long.parseLong(strTicketNum);
						long zip = Long.parseLong(strZip);
						Date date = null;
						
						// SimpleDateFormat used to convert string to Date in desired format
						try {
							date = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").parse(strDate);
						} catch (java.text.ParseException e) {
							e.printStackTrace();
						}
												
						violations.add(new Violation(date, fine, reason, plateID, ticketNum, state, zip));
					}
				}
				
				br.close();
			} catch (IOException e) {
				System.out.println("The text file " + this.filename + " was not found!");
				System.out.println("Make sure that the " + this.filename + " file exists to continue!");
				System.exit(0);
			}
		} else {
			violations = null;
		}
		return violations;
	}	
	
}
