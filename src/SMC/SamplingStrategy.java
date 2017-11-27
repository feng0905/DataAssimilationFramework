package SMC;

import dataAssimilationFramework.stateDefinition.AbstractState;

public abstract class SamplingStrategy
{ 
	/**
	 * 
	 * @param currentState
	 * @param measurement
	 * @return
	 */
	public abstract AbstractState sampling(AbstractState currentState,  AbstractMeasurement measurement);

}
