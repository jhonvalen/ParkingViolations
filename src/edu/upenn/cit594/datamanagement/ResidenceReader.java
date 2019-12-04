package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit594.data.Residence;
import edu.upenn.cit594.logging.Logger;

public class ResidenceReader {
	protected String filename;

	public ResidenceReader(String filename) {   
		this.filename = filename;
	}
	
	private String removeCommaBetweenQuotes(String s) {
		int countQuote = 0;
		int indexFirstQuote = s.indexOf("\"");
		if (indexFirstQuote==-1) {
			return s;
		}
		else {
			countQuote++;
		}
		String first = s.substring(0, indexFirstQuote+1);
		String stringAfterFirstQuote = s.substring(indexFirstQuote + 1);
		int indexSecondQuote = stringAfterFirstQuote.indexOf("\"");
		if (indexSecondQuote==-1) {
			return s;
		}
		else {
			countQuote++;
		}
		if (countQuote != 2) {
			return s;
		}
		String middle = stringAfterFirstQuote.substring(0,indexSecondQuote);
		if (middle.contains(",")) {
			middle = middle.replace(",", "");
		}
		String last = s.substring(indexFirstQuote+indexSecondQuote, s.length());
				
		return first+middle+last;
	}
	
	public Set<Residence> readResidences() {
		long currentTime = System.currentTimeMillis();
		Logger.getInstance().log(currentTime + " " + this.filename);
		
		Set<Residence> residences = new HashSet<Residence>(); 
		HashMap<String, Integer> headers = new HashMap<String, Integer>(); 
		Pattern fiveDigits = Pattern.compile("^[0-9]{5}$");
		try {    
			BufferedReader br = new BufferedReader(new FileReader(this.filename));
			String line=br.readLine();
			String[] firstRow = line.split(",");
			String zip_code = "zip_code";
			String market_value = "market_value";
			String livable_area = "total_livable_area";
			String objectid = "objectid";
			int count = 0;
			for (String header : firstRow) {
				if (header.equals(zip_code)) {
					headers.put(zip_code, count);
				}
				if (header.equals(market_value)) {
					headers.put(market_value, count);
				}
				if (header.equals(livable_area)) {
					headers.put(livable_area, count);
				}
				if (header.equals(objectid)) {
					headers.put(objectid, count);
				}
				count++; 
			}
			
			while ((line = br.readLine()) != null) {
				
//				if (line.contains("529789457")) {
//					System.out.println("break here");
//				}
				
				String lineAmended = removeCommaBetweenQuotes(line);
				String [] columnData = lineAmended.split(",");
				
				String name = columnData[headers.get(objectid)];  
				String strZipCode = columnData[headers.get(zip_code)];  
				String strZipCodeTrim = strZipCode.substring(0, Math.min(strZipCode.length(), 5));
				String strMarketValue = columnData[headers.get(market_value)];
				String strLivableArea = columnData[headers.get(livable_area)];

				Matcher matcher = fiveDigits.matcher(strZipCodeTrim);
				
				if ( matcher.find() && !name.equals("") && !strZipCode.equals("") && !strMarketValue.equals("") && 
						!strLivableArea.equals("")) {
					int zipCode = Integer.parseInt(strZipCodeTrim);
					int marketValue = (int) Double.parseDouble(strMarketValue);
					int livableArea = (int) Double.parseDouble(strLivableArea);
					Residence r = new Residence(name, marketValue, livableArea, zipCode);
					residences.add(r);
				}
			}
			
			br.close(); 
			
		}   catch (Exception e) {   
			System.out.println("Error reading Property file.");
			throw new IllegalStateException(e); 
			} 
		
	return residences;
	}
	
}