package SMC.specialParticleSystems;

import SMC.AbstractParticleSystem;
import SMC.resamplingStrategies.SystematicResampling;
import SMC.samplingStrategies.PriorSampling;
import SMC.weightUpdatingStrategies.LikelihoodWeight;


/**
 * The SMC system using the transition prior as the proposal, the likelihood as incremental weight.
 *  
 * @author Haidong
 *
 */
public class BootstrapFilter extends AbstractParticleSystem
{
	public BootstrapFilter()
	{
		super( new PriorSampling(), new LikelihoodWeight(), new SystematicResampling());
	}

}
