import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class PopulationReader {
	protected String filename;

	public PopulationReader(String filename) {   
		this.filename = filename;
	}
	
	public HashMap<String, Integer> readPopulations() {
		HashMap<String, Integer> populations = new HashMap<String, Integer>(); 
		try {    
		    FileReader f = new FileReader(filename);
			BufferedReader r = new BufferedReader(f);
			String line;
			while ((line = r.readLine()) != null) {   
				String [] columnData = line.split(" ");
				String zipCode = columnData[0];  
				int population = Integer.parseInt(columnData[1]);
				populations.put(zipCode, population);
			}
			f.close();
			r.close();
		}   catch (Exception e) {   
			System.out.println("Error reading Population file.");
			throw new IllegalStateException(e); 
			} 
	return populations;
	}
	
}
