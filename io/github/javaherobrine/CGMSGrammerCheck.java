package io.github.javaherobrine;
import java.io.*;
import java.util.*;
public final class CGMSGrammerCheck {
	private static HashSet<String> varsInClass=new HashSet<>();
	public static void check(CGMSFile cf) throws CGMSException, IOException{
		int mark=1;
		BufferedReader br=new BufferedReader(new FileReader(cf));
		String temp;
		String temp2;
		StringBuilder cause=new StringBuilder("");
		while((temp=br.readLine())!=null) {
			try {
				check(temp);
				mark+=1;
			}catch(CGMSException e) {
				cause.append(e.getMessage()).append(cf.getName()).append(":").append(mark);
			}
		}
		temp2=cause.toString();
		if(!temp2.equals("")) {
			throw new CGMSException(temp2);
		}
	}
	public static void check(String cgmscript) throws CGMSException{
		cgmscript.trim();
		if(cgmscript.startsWith("#")) {
			String[] temp=cgmscript.split(",");
			if(temp[temp.length-1].split("\'").length==3) {
				throw new CGMSException("元语句的末尾不能跟东西");
			}
		}
	}
}
