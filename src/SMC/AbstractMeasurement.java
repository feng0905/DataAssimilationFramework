package SMC;

import java.math.BigDecimal;

public abstract class AbstractMeasurement {
	// public abstract double distance(AbstractMeasurement measurement);
	public abstract BigDecimal weightUpdate(AbstractMeasurement measurement);
}
