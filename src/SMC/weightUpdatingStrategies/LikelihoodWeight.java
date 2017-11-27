package SMC.weightUpdatingStrategies;

import java.util.List;

import SMC.AbstractMeasurement;
import SMC.Particle;
import SMC.SamplingStrategy;
import SMC.WeightUpdatingStrategy;
import dataAssimilationFramework.stateDefinition.AbstractState.StateFunctionNotSupportedException;

/**
 * Calculate the weight by using measurementPdf method of AbstractState
 * @author Haidong Xue
 *
 */
public class LikelihoodWeight extends WeightUpdatingStrategy
{

	@Override
	protected void updateUnnormalizedWeights(List<Particle> particleSet, AbstractMeasurement measurement, SamplingStrategy sampler)
	{
		try
		{
			int i=0;
			for (Particle p : particleSet)
			{
				p.weight = p.weight.multiply(p.state.measurementPdf(measurement));
				i++;
			}
		}
		catch (StateFunctionNotSupportedException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
