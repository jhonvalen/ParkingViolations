package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.upenn.cit594.data.Residence;
import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.datamanagement.ResidenceReader;

public class ResidenceProcessor {
	
	protected ResidenceReader residenceReader;
	protected ViolationProcessor violationProcessor;
	protected PopulationProcessor populationProcessor;
	protected Set<Residence> residences;
	private Map<Integer, Long> avgMarketMap = new HashMap<Integer, Long>();
	private Map<Integer, Long> avgLivingMap = new HashMap<Integer, Long>();
	private Map<Integer, Long> totalMarketMap = new HashMap<Integer, Long>();
	private Map<Integer, Double> correlationMap = new HashMap<Integer, Double>();
	
	public ResidenceProcessor(ResidenceReader residenceReaderParam, ViolationProcessor vp, PopulationProcessor pp) {
		this.residenceReader = residenceReaderParam;
		this.violationProcessor = vp;
		this.populationProcessor = pp;
		this.residences = this.residenceReader.readResidences();
	}
	
	private long getResidenceMetric(ResidenceMetrics rn, int zip) {
		long metric = 0;
		for (Residence residence : this.residences) {
			if (residence.getZipCode() == zip) {
				metric += rn.getMetric(residence);
			}
		}
		return metric;
	}
	
	private int residenceAvgCalculation (ResidenceMetrics rm, int zip) {
		long numerator = this.getResidenceMetric(rm, zip);
		long denominator = this.getResidenceMetric(new ResidenceCountMetric(), zip);
		
		if (numerator < 1 || denominator < 1) {
			return 0;
		}
		
		return (int) (numerator/denominator);
	}
		
	private int totalMarketValue(int zip) {
		long total = this.getResidenceMetric(new MarketValueMetric(), zip);
		int zipPop = this.populationProcessor.singleZipPopulation(zip);
		
		if (total < 1 || zipPop < 1) {
			return 0;
		}
		
		return (int) (total/zipPop);
	}
	
	private double calculatePopulationDensity(int zip) {
		int population = this.populationProcessor.singleZipPopulation(zip);
		long livingArea = this.residenceAvgCalculation(new LivableAreaMetric(), zip);
		
		if (population < 1 || livingArea < 1) {
			return 0;
		}
		
		return (population/livingArea);
	}
	
	private Map<Integer, Double[]> getPopDensityAvgFine() {
		HashMap<Integer, Double[]> zipDenseFineMap = new HashMap<Integer, Double[]>();
		
		for (ZipCode zipCode : this.violationProcessor.pairZipObjects().values()) {
			int zipNum = zipCode.getZipCode();
			double zipPopulationDensity = this.calculatePopulationDensity(zipNum);
			double zipTotalFine = zipCode.getFineAmount();
			long residentCount = this.getResidenceMetric(new ResidenceCountMetric(), zipNum);
			double zipAvgFine = (zipTotalFine/residentCount);
			Double[] zipArray = new Double[] {zipPopulationDensity, zipAvgFine};
			zipDenseFineMap.put(zipNum, zipArray);
		}
		
		return zipDenseFineMap;
	}
	
	private double calculateCorrelation() {
		double sumPopulation = 0, sumFine = 0, sumBoth = 0, sqPopulation = 0, sqFine = 0;
		int sampleSize = this.getPopDensityAvgFine().size();
		for (Double[] zipPair : this.getPopDensityAvgFine().values()) {
			double populationDensity = zipPair[0];
			double avgFine = zipPair[1];
			sumPopulation += populationDensity;
			sumFine += avgFine;
			sumBoth += (populationDensity * avgFine);
			sqPopulation += Math.pow(populationDensity, 2);
			sqFine += Math.pow(avgFine, 2);
		}
		
		double covariance = (sumBoth/sampleSize - sumPopulation * sumFine / sampleSize / sampleSize);
		double standardErrorX = Math.sqrt(sqPopulation/sampleSize - sumPopulation * sumPopulation/sampleSize / sampleSize);
		double standardErrorY = Math.sqrt(sqFine/sampleSize - sumFine * sumFine/sampleSize / sampleSize);
		
		double correlation = covariance/(standardErrorX * standardErrorY);
		
		return correlation;
	}
	
	
	// memoization methods
	
	public long calculateAvgMarketValue(int zip) {
		if (avgMarketMap.containsKey(zip)) {
			return avgMarketMap.get(zip);
		} else {
			long marketValue = this.residenceAvgCalculation(new MarketValueMetric(), zip);
			avgMarketMap.put(zip, marketValue);
			return marketValue;
		}
	}
	
	public long calculateAvgLivingArea(int zip) {
		if (avgLivingMap.containsKey(zip)) {
			return avgLivingMap.get(zip);
		} else {
			long livingArea = this.residenceAvgCalculation(new LivableAreaMetric(), zip);
			avgLivingMap.put(zip, livingArea);
			return livingArea;
		}
	}
	
	public long calculateTotalMarketValue(int zip) {
		if (totalMarketMap.containsKey(zip)) {
			return totalMarketMap.get(zip);
		} else {
			long totalMarketValue = this.totalMarketValue(zip);
			totalMarketMap.put(zip, totalMarketValue);
			return totalMarketValue;
		}
	}
	
	public double calculateFinePopulationCorrelation() {
		if (correlationMap.containsKey(1)) {
			return correlationMap.get(1);
		} else {
			double finePopCorrelation = this.calculateCorrelation();
			correlationMap.put(1, finePopCorrelation);
			return finePopCorrelation;
		}
	}
	
}
