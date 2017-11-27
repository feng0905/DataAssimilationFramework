package dataAssimilationFramework.stateDefinition;

import java.util.Random;

public abstract class AbstractState implements Cloneable, StateInterface
{
	static Random rand; 
	/**
	 * To indicate if current is the initial state
	 * Initial State is the starting state 
	 */
	protected boolean isInitalState = true;
	
	/**
	 * The previous state
	 */
	public AbstractState previousState = null; 
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
	/**
	 * Add a short description to the state.
	 * @param des
	 */
	public abstract void setDescription( String des );
	
	public void setInititalState(boolean b){isInitalState = b;}
	
	public static abstract class AbstractMeasurementRandomComponent {
		public static Random getRandom() {
			if (AbstractState.rand == null) AbstractState.rand = new Random();
			return AbstractState.rand;
		}
	}
	
	public static abstract class AbstractTransitionRandomComponent {
		public static Random getRandom() {
			if (AbstractState.rand == null) AbstractState.rand = new Random();
			return AbstractState.rand;
		}
	}
	

		
	public static class StateFunctionNotSupportedException extends Exception {
		private static final long serialVersionUID = -7824749144583328188L;
	}

	

}
