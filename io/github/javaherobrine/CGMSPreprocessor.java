package io.github.javaherobrine;
import java.io.*;
/**
 * Ԥ������
 * @author Java_Herobrine
 */
@Deprecated
public class CGMSPreprocessor {
	public void pretreatment(CGMSFile cf) throws IOException {
		boolean isDestory=false;
		BufferedReader br=new BufferedReader(new FileReader(cf));
		String temp;
		while((temp=br.readLine())!=null) {
			if(isDestory) {
				
			}
			isDestory=temp.equals("#destoryArea");
		}
	}
}
