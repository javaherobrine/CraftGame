package io.github.javaherobrine.ioStream;
import java.io.*;
import io.github.javaherobrine.*;
/**
 * S/L�󷨺ã�
 * @author Java_Herobrine
 */
public final class Save {
	private File saveDir;
	public void writePlayer() {
		
	}
	/**
	 * ����һ���յĴ浵
	 * @author Java_Herobrine
	 * @param f �浵�ļ������ڵ�·��
	 */
	public Save(File f) throws IOException{
		 if(f.isFile()) {
			 throw new IOException("�㲻����һ���ļ���浵");
		 }
		 this.saveDir=f;
	}
	public SavePacket getSavePacks() throws FileNotFoundException{
		File[] temp=saveDir.listFiles();
		for(int i=0;i<temp.length;i++) {
			if(temp[i].getName().equals("savepacks")) {
				return new SavePacket(temp[i]);
			}
		}
		throw new FileNotFoundException("û���ҵ����ݰ��ļ���");
	}
}
