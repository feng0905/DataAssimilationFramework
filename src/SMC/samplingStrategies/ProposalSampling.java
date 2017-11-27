package SMC.samplingStrategies;

import SMC.AbstractMeasurement;
import SMC.SamplingStrategy;
import dataAssimilationFramework.stateDefinition.AbstractState;
import dataAssimilationFramework.stateDefinition.AbstractState.StateFunctionNotSupportedException;

public class ProposalSampling extends SamplingStrategy
{
	@Override
	public AbstractState sampling(AbstractState currentState, AbstractMeasurement measurement)
	{
		try
		{
			return currentState.propose(measurement);
		}
		catch (StateFunctionNotSupportedException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
