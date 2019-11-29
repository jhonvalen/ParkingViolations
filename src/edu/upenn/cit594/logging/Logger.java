package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Logger uses the Singleton Pattern to allow the same Logger object to be used across classes
 *  
 * @author Jhon Valencia
 *
 */

public class Logger {
	
	private static PrintWriter out;
	protected static String filename;
	private static Logger instance;
	
	private Logger(String fname) {
		
		try {
			File f = new File(filename);
			if (f.exists()) {
				out = new PrintWriter(new FileOutputStream(filename, false));
			} else {
				out = new PrintWriter(f);
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file name was not found!" + "\n" 
			+ "Please entere a valid file name to continue!");
		}
		
	}
		
	// accessor method for use with other classes
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger(filename);
		} 
		return instance;
	}
	
	public void log(String msg) {
		out.println(msg);
		out.flush();
	}
	
	// set method used to change the filename variable to command line argument
	public static void setFileName(String f) {
		filename = f;
	}	
}
