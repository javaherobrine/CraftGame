package io.github.javaherobrine;
import java.util.*;
public class CGMSField {
	public static HashMap<CGMSFile,String> CGMSFields=new HashMap<>();
	public static HashMap<CGMSMethod,String>CGMSVars=new HashMap<>();
	public CGMSFile cf;
	public String var;
	public CGMSMethod method;
	public CGMSField(CGMSFile cs,String str) {
		cf=cs;
		var=str;
		CGMSFields.put(cs, str);
	}
	public CGMSField(CGMSMethod cm,String s) {
		method=cm;
		var=s;
		CGMSVars.put(cm, s);
	}
	protected CGMSField() {
		
	}
}
