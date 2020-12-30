package io.github.javaherobrine.math;
import java.math.*;
public final class FloatingPointMath {
	private FloatingPointMath() {}
	public static double plus(double d1,double d2) {
		return BigDecimal.valueOf(d1).add(BigDecimal.valueOf(d2)).doubleValue();
	}
}
