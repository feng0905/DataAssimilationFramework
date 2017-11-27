package SMC.specialParticleSystems;

import java.math.BigDecimal;

import SMC.AbstractParticleSystem;
import SMC.resamplingStrategies.SystematicResampling;
import SMC.samplingStrategies.ProposalSampling;
import SMC.weightUpdatingStrategies.KernelEstimationProposalWeight;
import SMC.weightUpdatingStrategies.KernelEstimationProposalWeight.KernelFunction;

/**
 * The SMC system using SenSim proposal (as proposed in Haidong's PhD dissertation), KernelEstimation weight updating.
 * @author Haidong
 *
 */
public class SenSimFilter extends AbstractParticleSystem
{
	private static int sampleNumberForEstimation;
	private static KernelFunction kernelFunction;
	private static BigDecimal bandWidth;
	
	public SenSimFilter(int particleNumberForEstimation, KernelFunction kernelFunction, BigDecimal bandWidth )
	{
		super(new ProposalSampling(), new KernelEstimationProposalWeight(particleNumberForEstimation, kernelFunction, bandWidth), new SystematicResampling());
		//super(new ProposalSampling(), new LikelihoodWeight(), new SystematicResampling(), particleSet);
	}
	
	private SenSimFilter()
	{
		super(new ProposalSampling(), new KernelEstimationProposalWeight(sampleNumberForEstimation, kernelFunction, bandWidth), new SystematicResampling());
	}
	 
	public void updateKernelEstimationProposalWeight(int particleNumberForEstimation,KernelFunction kernelFunction,BigDecimal bandWidth) {
		((KernelEstimationProposalWeight)this.getWeightUpdater()).updateKernel(particleNumberForEstimation, kernelFunction, bandWidth);
	}
	
}
