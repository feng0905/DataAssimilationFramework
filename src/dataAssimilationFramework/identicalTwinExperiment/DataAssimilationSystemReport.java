package dataAssimilationFramework.identicalTwinExperiment;

import java.util.ArrayList;
import java.util.List;

import Experiment.ExperimentReport;

public class DataAssimilationSystemReport extends ExperimentReport {
	protected String experimentName = "DataAssimilationSystemReport" ;
	protected List<ParticleSystemError> errorReports = new ArrayList<>();
	protected List<ParticleSystemError> speedErrorReports = new ArrayList<>();
	protected List<ParticleSystemStateReport> stateReports = new ArrayList<>();
	public DataAssimilationSystemReport(String filePath, String exprimentName) {
		super(filePath);
		this.experimentName = exprimentName;
		//this.particleNumber = particlenumber;
	}

	public DataAssimilationSystemReport(String reportFilePath) {
		super(reportFilePath);
		// TODO Auto-generated constructor stub
	}

	public void setExperimentName(String expName) {
		this.experimentName = expName;
	}
	
	public void addErrorRecord(ParticleSystemError errReport) {
		errorReports.add(errReport);
	}
	public void addErrorRecord(double curTime,double simErr, double bestErr, double filteredErr) {
		errorReports.add(new ParticleSystemError(curTime, simErr, bestErr, filteredErr));
	}
	void addStateRecord(ParticleSystemStateReport stateReport) {
		stateReports.add(stateReport);
	}
	public String getFormattedErrorReport() {
		String formattedReport = experimentName;
		formattedReport += "\nTime\tSimulated Error\tBest Particle Error\tFiltered Error\n";
		for( ParticleSystemError r : errorReports)
			formattedReport += (r.currentTime + "\t" + String.format("%2.4f", r.simError) + "\t" + String.format("%2.4f", r.bestParticleError)+ "\t" + String.format("%2.4f", r.filteredError))+ "\n";
		
		return formattedReport;
	}

	public String getFormattedSpeedErrorReport() {
		String formattedReport = experimentName;
		formattedReport += "\nTime\tSimulated Error\tBest Particle Error\tFiltered Error\n";
		for( ParticleSystemError r : speedErrorReports)
			formattedReport += (r.currentTime + "\t" + String.format("%2.4f", r.simError) + "\t" + String.format("%2.4f", r.bestParticleError)+ "\t" + String.format("%2.4f", r.filteredError))+ "\n";
		
		return formattedReport;
	}
	
	public static <T extends DataAssimilationSystemReport>  T averageReport (List<T> listSystemReports) {
		if (listSystemReports.size() <= 0) {
			return null;
		}
		T avgReport = listSystemReports.get(0);
		for (int i=1;i<listSystemReports.size();i++) {
			//error report
			for (int j=0;j<listSystemReports.get(i).errorReports.size();j++) {
				avgReport.errorReports.get(j).bestParticleError += listSystemReports.get(i).errorReports.get(j).bestParticleError;
				avgReport.errorReports.get(j).currentTime += listSystemReports.get(i).errorReports.get(j).currentTime;
				avgReport.errorReports.get(j).filteredError += listSystemReports.get(i).errorReports.get(j).filteredError;
				avgReport.errorReports.get(j).simError += listSystemReports.get(i).errorReports.get(j).simError;
				
				avgReport.speedErrorReports.get(j).bestParticleError += listSystemReports.get(i).errorReports.get(j).bestParticleError;
				avgReport.speedErrorReports.get(j).currentTime += listSystemReports.get(i).errorReports.get(j).currentTime;
				avgReport.speedErrorReports.get(j).filteredError += listSystemReports.get(i).errorReports.get(j).filteredError;
				avgReport.speedErrorReports.get(j).simError += listSystemReports.get(i).errorReports.get(j).simError;
			}
			//state report: to be implemented
	
		}
		
		for (int j=0;j<avgReport.errorReports.size();j++) {
			avgReport.errorReports.get(j).bestParticleError /= listSystemReports.size();
			avgReport.errorReports.get(j).currentTime /= listSystemReports.size();
			avgReport.errorReports.get(j).filteredError /= listSystemReports.size();
			avgReport.errorReports.get(j).simError /= listSystemReports.size();
			
			avgReport.speedErrorReports.get(j).bestParticleError /= listSystemReports.size();
			avgReport.speedErrorReports.get(j).currentTime /= listSystemReports.size();
			avgReport.speedErrorReports.get(j).filteredError /= listSystemReports.size();
			avgReport.speedErrorReports.get(j).simError /= listSystemReports.size();
		}
		
		return avgReport;
	}
	
	
	public class ParticleSystemStateReport{
		List<String> particleStates;
		private String realsysState;
		String simulatedState;
		String bestParticleState;
		public ParticleSystemStateReport() {
			particleStates = new ArrayList<>();
		}
		public String getFormattedReport(int particleNumber) {
			String formattedReport = "Particles:\n"+ particleNumber+"\n";
			for (String particleString : particleStates) {
				formattedReport += particleString + "\n";
			}
			formattedReport += "Real System State:\n" + getRealsysState();
			formattedReport += "\nSimulated System State:\n" + simulatedState;
			formattedReport += "\nBest Particle System State:\n" + bestParticleState;
			return formattedReport;
		}
		public String getRealsysState() {
			return realsysState;
		}
		public void setRealsysState(String realsysState) {
			this.realsysState = realsysState;
		}
		public void setBestParticleState(String bestState) {
			// TODO Auto-generated method stub
			this.bestParticleState = bestState;
		}
		public void setSimsysState(String simsysState) {
			this.simulatedState = simsysState;
		}
		public void addParticleState(String parState) {
			this.particleStates.add(parState);
		}
	}
	
	public class ParticleSystemError{
		public double currentTime;  // the current time
		public double simError; // the distance between real system and simulated system
		public double bestParticleError; // the distance from real system and the best particle
		public double filteredError; 	// weighted average error of all particles 
		public ParticleSystemError(double curTime,double simErr, double bestErr, double filteredErr) {
			this.currentTime = curTime;
			this.simError = simErr;
			this.bestParticleError = bestErr;
			this.filteredError = filteredErr;
		}
	}

	public void addSpeedErrorRecord(double curTime, double simErr, double bestErr,
			double filteredErr) {
		// TODO Auto-generated method stub
		speedErrorReports.add(new ParticleSystemError(curTime, simErr, bestErr, filteredErr));
	}
	
}
