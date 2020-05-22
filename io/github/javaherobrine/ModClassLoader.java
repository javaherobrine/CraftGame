package io.github.javaherobrine;
import java.io.*;
/**
 * 有了这个类的文档，妈妈再也不用担心存档数据包不会写了！<br>
 * mod的类调用请使用反射，Java无法在运行时修改classpath参数
 * <button onclick="window.location.href='https://javaherobrine.gitee.io/dev'">在线查看文档</button>
 * @author Java_Herobrine 请给我来段乱码
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
