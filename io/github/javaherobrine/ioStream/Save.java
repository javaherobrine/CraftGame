package io.github.javaherobrine.ioStream;
import java.io.*;
import io.github.javaherobrine.*;
/**
 * S/L大法好！
 * @author Java_Herobrine
 */
public final class Save {
	private File saveDir;
	public void writePlayer() {
		
	}
	/**
	 * 构造一个空的存档
	 * @author Java_Herobrine
	 * @param f 存档文件夹所在的路径
	 */
	public Save(File f) throws IOException{
		 if(f.isFile()) {
			 throw new IOException("你不能在一个文件里存档");
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
		throw new FileNotFoundException("没有找到数据包文件夹");
	}
}
