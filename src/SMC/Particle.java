package SMC;

import java.math.BigDecimal;

import dataAssimilationFramework.stateDefinition.AbstractState;

public class Particle
{
	public Particle(AbstractState state, BigDecimal weight )
	{
		this.state = state;
		this.weight = weight;
	}
	
	/**
	 * The system state
	 */
	public AbstractState state;
	
	/**
	 * The importance weight
	 */
	public BigDecimal weight; 

}
