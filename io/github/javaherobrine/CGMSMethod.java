package io.github.javaherobrine;
public class CGMSMethod {
	CGMSFile cf;
	String methodName;
	Class<CGMSField>[] fields;
	public CGMSMethod(CGMSFile cf,String methodName,Class<CGMSField>... fields) {
		this.cf=cf;
		this.methodName=methodName;
		this.fields=fields;
	}
}
