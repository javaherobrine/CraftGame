package io.github.javaherobrine;
import java.util.*;
/**
* 由CGMS的元语句#ops传入参数
 */
@Deprecated
public class CGMSOptions {
	public static HashMap<CGMSFile,String[]> map=new HashMap<>();
	public static void regditCGMS(CGMSFile cf,String mainClass,String mainMethod) {
		map.put(cf,new String[]{mainClass,mainMethod});
	}
}
