package io.github.javaherobrine;
@Deprecated
public class CGMSComplex extends CGMSImag {
	public CGMSImag i;
	public Number n;
	public CGMSComplex(CGMSImag i,Number n) {
		if(n instanceof CGMSImag||i instanceof CGMSComplex) {
			throw new NumberFormatException("������ʽ����");
		}
		this.i=i;
		this.n=n;
	}
}
