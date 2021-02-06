package io.github.javaherobrine.math;
import java.math.*;
public strictfp final class FloatingPointMath {
	public static final int DEFAULT_DIVIDE_SCALE=10;
	private FloatingPointMath() {}
	public static double plus(double d1,double d2) {
		return BigDecimal.valueOf(d1).add(BigDecimal.valueOf(d2)).doubleValue();
	}
	public static double minus(double d1,double d2) {
		return BigDecimal.valueOf(d1).subtract(BigDecimal.valueOf(d2)).doubleValue();
	}
	public static double times(double d1,double d2) {
		return BigDecimal.valueOf(d1).multiply(BigDecimal.valueOf(d2)).doubleValue();
	}
	public static double divide(double d1,double d2) {
		return BigDecimal.valueOf(d1).divide(BigDecimal.valueOf(d2),DEFAULT_DIVIDE_SCALE,RoundingMode.HALF_UP).doubleValue();
	}
	public static double divide(double d1,double d2,int scale) {
		return BigDecimal.valueOf(d1).divide(BigDecimal.valueOf(d2),scale,RoundingMode.HALF_UP).doubleValue();
	}
	public static double divideWithoutScale(double d1,double d2) throws ArithmeticException{
		return BigDecimal.valueOf(d1).divide(BigDecimal.valueOf(d2)).doubleValue();
	}
	public static double sqrtUnlimited(double d) {
		return BigDecimal.valueOf(d).sqrt(MathContext.UNLIMITED).doubleValue();
	}
	public static double sqrtDecimal128(double d) {
		return new BigDecimal(Double.toString(d),MathContext.DECIMAL128).sqrt(MathContext.DECIMAL128).doubleValue();
	}
	public static double sqrtDecimal64(double d) {
		return Math.sqrt(d);
	}
	public static double sqrtDecimal32(double d) {
		return (float)Math.sqrt((float)d);
	}
}
