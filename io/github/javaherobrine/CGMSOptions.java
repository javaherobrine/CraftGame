package io.github.javaherobrine;
import java.util.*;
/**
* ��CGMS��Ԫ���#ops�������
 */
@Deprecated
public class CGMSOptions {
	public static HashMap<CGMSFile,String[]> map=new HashMap<>();
	public static void regditCGMS(CGMSFile cf,String mainClass,String mainMethod) {
		map.put(cf,new String[]{mainClass,mainMethod});
	}
}
