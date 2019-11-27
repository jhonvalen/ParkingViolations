import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ResidenceReader {
	protected String filename;

	public ResidenceReader(String filename) {   
		this.filename = filename;
	}
	
	
	public Set<Residence> readResidences() {
		Set<Residence> residences = new HashSet<Residence>(); 
		HashMap<String, Integer> headers = new HashMap<String, Integer>(); 
		try {    
			File file = new File (filename);
			Scanner in = new Scanner(file);
			String rowData = in.nextLine();
			String[] firstRow = rowData.split(",");
			
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
			
			while (in.hasNextLine()) { 
				rowData = in.nextLine();
				String [] columnData = rowData.split(",");
				String name = columnData[headers.get(objectid)];  
				String zipCode = columnData[headers.get(zip_code)];  
				String zipCodeTrim = zipCode.substring(0, Math.min(zipCode.length(), 5));
				String marketValue = columnData[headers.get(market_value)];
				String livableArea = columnData[headers.get(livable_area)];
				Residence r = new Residence(name, marketValue, livableArea, zipCodeTrim);
				residences.add(r);
			}

			in.close(); 
		}   catch (Exception e) {   
			System.out.println("Error reading Property file.");
			throw new IllegalStateException(e); 
			} 
		for (Residence r : residences) {
			System.out.println(r.getResidenceName() + " is in zip code  " + r.getResidenceZipCode() + ". Market value is  " + r.getResidenceMarketValue() + " and livable area is  " + r.getResidenceLivableArea());
		}
	return residences;
	}
	
}
