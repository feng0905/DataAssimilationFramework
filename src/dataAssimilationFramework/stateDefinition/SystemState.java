package dataAssimilationFramework.stateDefinition;


import java.math.BigDecimal;

import SMC.AbstractMeasurement;
import dataAssimilationFramework.spatialTemporalSystem.AbstractSpatialTemporalSystem;

public class SystemState <T extends AbstractSpatialTemporalSystem> extends AbstractState implements Cloneable{
	protected T simSystem = null;


	public SystemState (T simsystem) {
		this.simSystem = simsystem;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SystemState<T> clone()  
	{
		SystemState<T> obj = null;
		try {
			 obj = (SystemState<T>) super.clone();
			 obj.simSystem = (T) getSimSystem().clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	public T getSimSystem() {
		return simSystem;
	}
	
	/* (non-Javadoc)
	 * @see smc.AbstractState#transitionFunction()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SystemState<T> transitionFunction() throws CloneNotSupportedException {
		// TODO Auto-generated method stub			
		simSystem.runSimulation(simSystem.stepLength);
		return this;
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#transitionModel(smc.AbstractState.AbstractTransitionRandomComponent)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SystemState<T> transitionModel(AbstractTransitionRandomComponent random) throws StateFunctionNotSupportedException {
		// TODO Auto-generated method stub
		simSystem.runSimulationWithRandomNoise(simSystem.stepLength, AbstractTransitionRandomComponent.getRandom()); 
	    return this;
	}
	
	@Override
	public AbstractMeasurement measurementFunction() {
		return simSystem.getSensorObservation(simSystem.getSpace());
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#transitionPdf(smc.AbstractState)
	 */
	@Override
	public BigDecimal transitionPdf(AbstractState nextState)
			throws StateFunctionNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see smc.AbstractState#distance(smc.AbstractState)
	 */
	@Override
	public double distance(AbstractState sample) {	
		return simSystem.distance(((SystemState)sample).getSimSystem());
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String des) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#measurementModel(smc.AbstractState.AbstractMeasurementRandomComponent)
	 */
	@Override
	public AbstractMeasurement measurementModel(
			AbstractState.AbstractMeasurementRandomComponent random)
			throws StateFunctionNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#proposalPdf(smc.AbstractMeasurement)
	 */
	@Override
	public BigDecimal proposalPdf(AbstractMeasurement measurement)
			throws StateFunctionNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#generateNoisedState()
	 */
	@Override
	public AbstractState generateNoisedState()
			throws StateFunctionNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#propose(smc.AbstractMeasurement)
	 */
	@Override
	public AbstractState propose(AbstractMeasurement measurement)
			throws StateFunctionNotSupportedException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see smc.AbstractState#drawNextRandomComponentSample()
	 */
	@Override
	public AbstractTransitionRandomComponent drawNextRandomComponentSample() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * The measurement probability density function.
	 * @param measurement the measurement, m_t
	 * @return the probability density of measurement distribution, p(m_t|s_t) 
	 */
	public BigDecimal measurementPdf( AbstractMeasurement measurement ) throws StateFunctionNotSupportedException {
		return this.measurementFunction().weightUpdate(measurement);
	}
	
}
