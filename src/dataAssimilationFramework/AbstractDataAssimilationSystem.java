package dataAssimilationFramework;


import SMC.AbstractMeasurement;
import SMC.AbstractParticleSystem;
import dataAssimilationFramework.spatialTemporalSystem.AbstractSpatialTemporalSystem;
import dataAssimilationFramework.stateDefinition.SystemState;


public abstract class AbstractDataAssimilationSystem <S extends AbstractSpatialTemporalSystem, T extends AbstractParticleSystem> {
	protected final SystemState<S> system;
	protected final T particleSystem;	
	
	public AbstractDataAssimilationSystem() {
		this.system = this.createSimulationSystem();
		this.particleSystem = this.createParticleSystem();
	}

	private void initializeParticleSystem (int particleNumber) {
		try {
			particleSystem.initializeParticleSet(system, particleNumber);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void updateDataAssimilationSystem(AbstractMeasurement measurement) {
		this.particleSystem.updateParticle(measurement);
	}
	
	public void runDataAssimilation(AbstractMeasurement[] measurementArray, int stepNumber, int partcleNumber) {
		//Initial particle set		
		initializeParticleSystem(partcleNumber);
		
		for( int t=1; t<=stepNumber; t++ ) {
			System.out.println("SMC -------------- Step" + t + " started !!!! ");

			// Assimilate data to the particle system
			updateDataAssimilationSystem(measurementArray[t-1]);	
			
			System.out.println("SMC -------------- particles finished");
		}
	
	}
	public void runDataAssimilation(SystemState<S>[] systemArray, int stepNumber, int partcleNumber) {

		//Initial particle set		
		initializeParticleSystem(partcleNumber);
		
		for( int t=1; t<=stepNumber; t++ ) {
			System.out.println("SMC -------------- Step" + t + " started !!!! ");

			// Assimilate data to the particle system
			updateDataAssimilationSystem(systemArray[t-1].measurementFunction());	
			
			System.out.println("SMC -------------- particles finished");
		}
	}
	abstract public SystemState<S> createSimulationSystem() ;
	abstract public T createParticleSystem() ;
}
