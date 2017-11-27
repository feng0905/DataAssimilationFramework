package dataAssimilationFramework.stateDefinition;

import java.math.BigDecimal;

import SMC.AbstractMeasurement;
import dataAssimilationFramework.stateDefinition.AbstractState.AbstractMeasurementRandomComponent;
import dataAssimilationFramework.stateDefinition.AbstractState.AbstractTransitionRandomComponent;
import dataAssimilationFramework.stateDefinition.AbstractState.StateFunctionNotSupportedException;

public interface StateInterface {
	/**
	 * The deterministic transition function, i.e. s=f''(s).
	 * Note: this function is available only when the random component (v) can be separated out: s=f(s, v)=f'( f''(s), v ). 
	 * @return the system state at next time point given the current state
	 * @throws StateFunctionNotSupportedException 
	 * @throws CloneNotSupportedException 
	 */
	public AbstractState transitionFunction( ) throws StateFunctionNotSupportedException, CloneNotSupportedException;

	/**
	 * The common transition model: s=f(s, v).
	 * @param random  the random component
	 * @return the system state at next time point given the current state
	 * @throws StateFunctionNotSupportedException
	 * @throws CloneNotSupportedException 
	 */
	public abstract AbstractState transitionModel( AbstractTransitionRandomComponent random) throws StateFunctionNotSupportedException;
	
	/**
	 * The deterministic measurement function, i.e. m=g''(s).
	 * Note: this function is available only when the random component (w) can be separated out: n=g(s, w)=g'( g''(s), w ). 
	 * @return the measurement given the current state
	 */
	public abstract AbstractMeasurement measurementFunction () throws StateFunctionNotSupportedException; 
		
	/**
	 * The transition probability density function.
	 * @param nextState the next system state
	 * @return the probability density of p(s_t+1 | s_t ), s_t is the current state, s_t+1 is the nextState
	 */
	public abstract BigDecimal transitionPdf(AbstractState nextState ) throws StateFunctionNotSupportedException; 


	public BigDecimal measurementPdf(AbstractMeasurement measurement) throws StateFunctionNotSupportedException;

	public double distance(AbstractState other);
	
	/**
	 * The common measurement model: m=g(s, w).
	 * @param random the random component
	 * @return the measurement given the current state
	 */
	public AbstractMeasurement measurementModel ( AbstractMeasurementRandomComponent random ) throws StateFunctionNotSupportedException; 
	
	/**
	 * The proposal probability density function. Current state is s_t, it returns q(s_t | s_t-1, m_t) 
	 * @param measurement the measurement, m_t
	 * @return the probability density of proposal distribution, q(s_t | s_t-1, m_t)
	 */
	public BigDecimal proposalPdf (AbstractMeasurement measurement ) throws StateFunctionNotSupportedException; 

	/**
	 * Add a noise to the current state
	 * @return a noised state
	 */
	public abstract AbstractState generateNoisedState() throws StateFunctionNotSupportedException; 
	
	/**
	 * Propose the next state from the current state and the measurement of the next state
	 * @param measurement the measurement of the next state
	 * @return a proposed state of the next state
	 */
	public abstract AbstractState propose(AbstractMeasurement measurement) throws StateFunctionNotSupportedException, Exception;             
	
	/**
	 * Draw a sample of  the random component for next state. That is, given s_(t+1) = f( s_t, v), draw a sample of v.
	 * @return
	 */
	public AbstractTransitionRandomComponent drawNextRandomComponentSample();	
}
