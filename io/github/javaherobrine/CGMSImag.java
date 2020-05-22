package io.github.javaherobrine;
import java.math.*;
public class CGMSImag extends Number{
	BigInteger bi;
	volatile BigDecimal bd;
	@Deprecated
	@Override
	public synchronized int intValue() {
		throw new NumberFormatException("����һ������");
	}
	@Override
	@Deprecated
	public long longValue() {
		throw new NumberFormatException("����һ������");
	}
	@Override
	@Deprecated
	public float floatValue() {
		throw new NumberFormatException("����һ������");
	}
	@Override
	@Deprecated
	public double doubleValue() {
		throw new NumberFormatException("����һ������");
	}
	public CGMSImag(String val) {
		if(!val.endsWith("i")) {
			throw new NumberFormatException("����Ĳ���һ������");
		}else {
			char[] cs=val.toCharArray();
			val=new String(cs,0,cs.length-2);
			if(val.split(".").length==2) {
				this.bd=new BigDecimal(val);
			}else {
				this.bi=new BigInteger(val);
			}
		}
	}
	public CGMSImag(BigInteger i) {
		this.bi=i;
	}
	public CGMSImag(BigDecimal bd) {
		this.bd=bd;
	}
	protected CGMSImag() {}
	public CGMSComplex toComplex() {
		return new CGMSComplex(this,0);
	}
}
