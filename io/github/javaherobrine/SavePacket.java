package io.github.javaherobrine;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
/**
 * 存档数据包
 * @author Java_Herobrine
 */
public class SavePacket {
	public File packFileDir;
	public SavePacket(File packFileDir) {
		this.packFileDir=packFileDir;
	}
	/**
	 * 事实上，这和mod的加载很像，真的，特别像
	 * @throws IOException 如果读取时内存已满
	 * @throws ClassNotFoundException 如果mod指定的主类不存在
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 如果未继承ModTemplate
	 * @throws InvocationTargetException 封装异常
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void loadMods() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		BufferedReader br;
		File[] temp;
		boolean isModCanLoad;
		File fff;
		File[] packs=packFileDir.listFiles();
		for(int i=0;i<packs.length;i++) {
			String s;
			temp=packs[i].listFiles();
			for(int j=0;j<packs.length;j++) {
				if(temp[j].getName().equals("options.txt")) {
					br=new BufferedReader(new FileReader(temp[j]));
					for(s=br.readLine().trim();s!=null;s=br.readLine().trim()) {
						if(s.isEmpty()) {
							continue;
						}else if(s.equals("type:")) {
							if(s.endsWith("lib")||s.endsWith("ext")) {
								continue;
							}else{
								isModCanLoad=true;
							}
						}else if(s.equals("main-class")){
							Class.forName(new StringBuilder(s).delete(0,9).toString()).getMethod("run",null).invoke(null,null);
						}
					}
				}
			}
		}
	}
}
