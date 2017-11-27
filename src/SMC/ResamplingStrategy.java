package SMC;

import java.util.List;

public abstract class ResamplingStrategy
{
	public abstract List<Particle> resampling( List<Particle> particleSet );
}
