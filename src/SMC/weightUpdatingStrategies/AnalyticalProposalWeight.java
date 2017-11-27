package SMC.weightUpdatingStrategies;

import java.util.List;
import SMC.AbstractMeasurement;
import SMC.Particle;
import SMC.SamplingStrategy;
import SMC.WeightUpdatingStrategy;
import dataAssimilationFramework.stateDefinition.AbstractState.StateFunctionNotSupportedException;

public class AnalyticalProposalWeight extends WeightUpdatingStrategy
{

	@Override
	protected void updateUnnormalizedWeights(List<Particle> particleSet, AbstractMeasurement measurement, SamplingStrategy sampler)
	{
		try
		{
			for(Particle p:particleSet )
				p.weight = p.weight.multiply(p.state.proposalPdf(measurement));
		}
		catch (StateFunctionNotSupportedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}
