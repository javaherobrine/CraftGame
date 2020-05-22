package io.github.javaherobrine;
import java.io.*;
/**
 * �����������ĵ���������Ҳ���õ��Ĵ浵���ݰ�����д�ˣ�<br>
 * mod���������ʹ�÷��䣬Java�޷�������ʱ�޸�classpath����
 * <button onclick="window.location.href='https://javaherobrine.gitee.io/dev'">���߲鿴�ĵ�</button>
 * @author Java_Herobrine �������������
 */
public class ModClassLoader extends ClassLoader {
	public Class<?> loadClass(String path,String name){
		byte[] classData;
		try {
			FileInputStream fi=new FileInputStream(path);
			classData=fi.readAllBytes();
			fi.close();
			return defineClass(name,classData,0,classData.length);
		} catch (IOException e) {
			return null;
		}
	}
}
