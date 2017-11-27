package dataAssimilationFramework.spatialTemporalSystem;

import SMC.AbstractMeasurement;

public abstract class AbstractSpace implements Cloneable{
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	public abstract AbstractMeasurement getMeasurement();
	
}
