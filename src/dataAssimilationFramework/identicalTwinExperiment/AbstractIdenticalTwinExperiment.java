package dataAssimilationFramework.identicalTwinExperiment;

import Experiment.Experiment;
import SMC.AbstractMeasurement;
import SMC.AbstractParticleSystem;
import dataAssimilationFramework.spatialTemporalSystem.AbstractSpatialTemporalSystem;
import dataAssimilationFramework.stateDefinition.SystemState;


public abstract class AbstractIdenticalTwinExperiment <S extends AbstractSpatialTemporalSystem, T extends AbstractParticleSystem> extends Experiment
{ 
	protected static double simulationTime = 0;
	protected final double stepLength;
	protected int particleNumber;					// add this variable to save multiple results.
	protected int stepNumber;
	protected SystemState<S> realSystem; 			// a single simulation with real setting
	protected SystemState<S> simulatedSystem; 		// a single simulation with wrong setting
	protected final T particleSystem; 					// a particle system containing a set of simulations (particles)
	
	
	public AbstractIdenticalTwinExperiment(double stepLength) {
		super("");
		this.stepLength = stepLength;
		realSystem = this.createRealSystem();
		simulatedSystem = this.createSimulatedSystem();
		particleSystem = this.createParticleSystem();
		
	}

	public void runDataAssimilationExperiement( int stepNumber, int particleNumber ) throws Exception
	{
		this.stepNumber = stepNumber;
		this.particleNumber = particleNumber;
		//Initialization
		initializeExperiment(particleNumber);
		
		//Run experiments for @stepNumber steps
		runExperiment(stepNumber);
		
	}
	
	//Initialize the experiment 
	protected void initializeExperiment(int particleNumber) {
		realSystem.setDescription("t0_Real");
		System.out.println("SMC --------------  Real system created!!! ");
		//System.out.println("Memory usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000.0 + "MB");
		simulatedSystem.setDescription("t0_Sim");
		System.out.println("SMC --------------  Simulated system created!!! ");
		// Create the initial particle set from the simulated system 
		try {
			particleSystem.initializeParticleSet(simulatedSystem, particleNumber);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	protected void runExperiment(int stepNumber) throws Exception{
		for( int t=1; t<=stepNumber; t++ )
		{
			System.out.println("SMC -------------- Step" + t + " started !!!!!!!!!!!! ");
			// The real system at time t
			realSystem = realSystem.transitionFunction();
			realSystem.setDescription("t"+t+"_"+"Real");
			
			System.out.println("SMC -------------- real sys finished");
			//System.gc();
			//System.out.println("Memory usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000.0 + "MB");
			
			//The simulated system at time t
			simulatedSystem = simulatedSystem.transitionFunction();
			simulatedSystem.setDescription("t"+t+"_"+"Sim");

			System.out.println("SMC -------------- sim sys finished");
			//System.gc();
			//System.out.println("Memory usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000.0 + "MB");
			
			// Measurement from the real system
			AbstractMeasurement measurement = realSystem.measurementFunction();
			System.out.println("SMC -------------- measurement finished");
			//System.out.println("Memory usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000.0 + "MB");
			
			// Assimilate data to the particle system
			particleSystem.updateParticle(measurement); 
			particleSystem.setDescription("t"+t); // add a description for the state for each particle
			System.out.println("SMC -------------- particles finished");
			//System.out.println("Memory usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000.0 + "MB");
			
			
			// Report experiment results
			reportOnStep( t );
			simulationTime += stepLength;
		}
	}
	protected abstract void reportOnStep(int step) throws Exception;
	abstract protected SystemState<S> createRealSystem();
	abstract protected SystemState<S> createSimulatedSystem();
	abstract protected T createParticleSystem();

	public static double getSimulationTime() {
		// TODO Auto-generated method stub
		return simulationTime;
	}
}
