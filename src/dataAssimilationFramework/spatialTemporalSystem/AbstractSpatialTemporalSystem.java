package dataAssimilationFramework.spatialTemporalSystem;

import java.util.List;
import java.util.Random;

import SMC.AbstractMeasurement;
import dataAssimilationFramework.stateDefinition.AbstractState.StateFunctionNotSupportedException;

public abstract class AbstractSpatialTemporalSystem  implements Cloneable{
	protected AbstractSpace space = null;  
	protected List<AbstractSensor> sensorList = null;
	public double stepLength;		//Step length should be assigned in constructor of any subclasses
	
	protected AbstractSpatialTemporalSystem() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract AbstractSpace createSystemSpace();
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	// currently using double: if precision is not enough, maybe changed to other types. 
	public abstract double distance(AbstractSpatialTemporalSystem sys);
	
	/**
	 * runSimulation: simply run the origin model by one time step 
	 * The deterministic transition function, i.e. s=f''(s).
	 * Note: this function is available only when the random component (v) can be separated out: s=f(s, v)=f'( f''(s), v ).
	 * @param timeStep: represent the duration time of one time step 
	 * @return the system state at next time point given the current state
	 * @throws StateFunctionNotSupportedException 
	 * @throws CloneNotSupportedException 
	 */
	//public abstract AbstractSpaceTemporalSystem transition();
	public abstract AbstractSpatialTemporalSystem runSimulation(double timeStep);
	/**
	 * runSimulationWithRandomNoise, run simulation by one time step and consider random component added
	 * The common transition model: s=f(s, v). 
	 * @param random  the random component
	 * @param timestep	specify the time step of the transition
	 * @return the system state at next time point given the current state
	 * @throws CloneNotSupportedException 
	 */
	//public abstract AbstractSpaceTemporalSystem transitionModel(AbstractTransitionRandomComponent, double timestep);
	public abstract AbstractSpatialTemporalSystem runSimulationWithRandomNoise(double timeStep, Random rand);
	
	/**
	 * Given a system space, return the measurement (what about sensors)
	 * @param Space
	 * @return
	 */
	public abstract AbstractMeasurement getSensorObservation(AbstractSpace Space);
	
	public abstract AbstractSpatialTemporalSystem propose(AbstractMeasurement measurement);

	/**
	 * @return the space
	 */
	public AbstractSpace getSpace() {
		return space;
	}
	
	public List<AbstractSensor> getSensors() {
		return sensorList;
	}
	
}