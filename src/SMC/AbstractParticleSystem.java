package SMC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dataAssimilationFramework.stateDefinition.AbstractState;


public abstract class AbstractParticleSystem {
	protected List<Particle> particleSet;
	protected final SamplingStrategy sampler;
	protected final WeightUpdatingStrategy weightUpdater;
	protected final ResamplingStrategy resampler;

	
	protected Particle bestParticleBeforeResampling=null;
	
	public AbstractParticleSystem( SamplingStrategy sampler, WeightUpdatingStrategy weightUpdater, ResamplingStrategy resampler)
	{
		this.sampler = sampler;
		this.weightUpdater = weightUpdater;
		this.resampler = resampler;	
	}
	
	public SamplingStrategy getSampler() {
		return sampler;
	}

	public WeightUpdatingStrategy getWeightUpdater() {
		return weightUpdater;
	}

	public ResamplingStrategy getResampler() {
		return resampler;
	}

	public void initializeParticleSet(AbstractState state, int particleNumber) throws CloneNotSupportedException {
		particleSet = new ArrayList<Particle>();
		for(int i=0; i<particleNumber; i++)
		{
			AbstractState s=null;
			// simulatedSystem.setAsInititalState();
			s = (AbstractState) state.clone();
			
			s.setDescription("t0_"+"Particle" + i);

			particleSet.add(new Particle(s, BigDecimal.valueOf(1.0/particleNumber)));				

			System.out.println("SMC -------------- Particle" + i + " created!!! ");
			System.out.println("Memory usage: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000.0 + "MB");
		}
	}
	

	public void updateParticle( AbstractMeasurement measurement )
	{
		//System.out.print("Sampling: ");
		//start = System.currentTimeMillis();
		for ( Particle p:particleSet)
		{
			//System.out.print(i++ + " ");
			AbstractState temp = p.state;
			p.state = sampler.sampling(p.state, measurement);
			p.state.previousState = temp;
			p.state.previousState.previousState = null;  // very important, or it forms a linked list, and consume memory fast
		}
				
		//WeightUpdating
		weightUpdater.updateWeights(particleSet, measurement, sampler);		
		//Record the best sample
		bestParticleBeforeResampling = this.getHighestWeightParticle();	
		//Re-sampling
		particleSet = resampler.resampling(particleSet);
	}
		
	public Particle getBestParticleBeforeResampling() {return bestParticleBeforeResampling;}

	/**
	 * 
	 * @return the particle with the highest weight. Note: it is meaningless after a resampling
	 */
	public Particle getHighestWeightParticle()
	{
		Particle maxP = null;
		BigDecimal max = BigDecimal.ZERO;
		for( Particle p  : particleSet)
		{
			if( max.compareTo(p.weight)<0)
			{
				maxP = p;
				max = p.weight;
			}
		}
		return maxP;
	}

	public List<Particle> getParticleSet()
	{
		return this.particleSet;
	}
	
	public void setDescription(String des)
	{
		int c=0;
		for(Particle p: this.particleSet)
		{
			p.state.setDescription(des+"_Particle"+c);
			c++;
		}
		
	}
}
