package io.github.javaherobrine;
import java.util.*;
@Deprecated
public class CGMSField {
	public static HashMap<CGMSFile,String> CGMSFields=new HashMap<>();
	public static HashMap<CGMSMethod,String>CGMSVars=new HashMap<>();
	public CGMSFile cf;
	public String var;
	public CGMSMethod method;
	protected CGMSField() {
	}
}
