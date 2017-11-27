package Experiment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class ExperimentReport {
	
	public ExperimentReport(String reportFilePath) {
		if (reportFilePath == "") return;
		try {
			writer = new PrintWriter(reportFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private PrintWriter writer = null;
	
	public void writeToReport(Object o) {
		writer.write(o.toString());
		
	}
	public void writeToReport(String S) {
		writer.write(S);
	}
	public void writeToReport(String S, int off, int len) {
		writer.write(S,off,len);
	}
	public void writeToReport(int i) {
		writer.write(i);
		
	}
	public void writeToReport(char[] c) {
		writer.write(c);
	}
	
	
}
