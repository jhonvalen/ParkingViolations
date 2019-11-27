package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import edu.upenn.cit594.data.Violation;

public class JSONViolationReader implements ViolationReader {
	
	protected String filename;
	
	public JSONViolationReader(String name) {
		this.filename = name;
	}
	
	private JSONArray readJSON() {
		JSONParser parser = new JSONParser();
		JSONArray violations = null;
		
		try {
			violations = (JSONArray)parser.parse(new FileReader(this.filename));
		} catch (FileNotFoundException e) {
			System.out.println("The json file " + this.filename + " was not found!");
			System.out.println("Make sure that the " + this.filename + " exists to continue!");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (ParseException e) {
			System.out.println("Parsing the JSON file failed!");
			System.out.println("Make sure that the JSON file is well formatted to continue!");
			System.exit(0);
		}
				
		return violations;
	}
	
	public List<Violation> getAllViolations() {
		ArrayList<Violation> violationList = new ArrayList<Violation>();
		JSONArray violations = readJSON();
		
		if (violations == null) return null;
		
		Iterator<?> iter = violations.iterator();
		
		while (iter.hasNext()) {			
			JSONObject violationJSON = (JSONObject) iter.next();
			
			String reason = violationJSON.get("violation").toString();
			String strPlateID = violationJSON.get("plate_id").toString();
			String strFine = violationJSON.get("fine").toString();
			String strTicketNum = violationJSON.get("ticket_number").toString();
			String state = violationJSON.get("state").toString();
			String strZip = violationJSON.get("zip_code").toString();
			String strDate = violationJSON.get("date").toString();
						
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
				
				violationList.add(new Violation(date, fine, reason, plateID, ticketNum, state, zip));
			}
			
		}

		return violationList;
	}

}
