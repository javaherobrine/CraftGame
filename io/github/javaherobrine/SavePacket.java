package io.github.javaherobrine;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
/**
 * �浵���ݰ�
 * @author Java_Herobrine
 */
public class SavePacket {
	public File packFileDir;
	public SavePacket(File packFileDir) {
		this.packFileDir=packFileDir;
	}
	/**
	 * ��ʵ�ϣ����mod�ļ��غ�����ģ��ر���
	 * @throws IOException �����ȡʱ�ڴ�����
	 * @throws ClassNotFoundException ���modָ�������಻����
	 * @throws SecurityException 
	 * @throws NoSuchMethodException ���δ�̳�ModTemplate
	 * @throws InvocationTargetException ��װ�쳣
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
