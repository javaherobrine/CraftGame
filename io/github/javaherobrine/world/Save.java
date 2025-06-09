package io.github.javaherobrine.world;
import java.io.*;
import io.github.javaherobrine.blocks.*;
import io.github.javaherobrine.*;
public class Save {
	private String saveFolder;
	public Save(File input) {
		saveFolder=input.getAbsolutePath();
	}
	public Chunk readChunk(int dimension,int x,int y) throws IOException{
		File f=new File(saveFolder+"/chunks/"+dimension+"-"+x+","+y+".dat");
		if(!f.exists()) {
			return null;
		}
		BufferedReader reader=new BufferedReader(new FileReader(f));
		Chunk chk=new Chunk();
		int ch=reader.read();
		while(ch!=-1) {
			int p=GameUtils.readUint(reader);
			int q=GameUtils.readUint(reader);
			int r=GameUtils.readUint(reader);
			chk.chunk[p][q][r]=Block.load(reader.readLine());
		}
		reader.close();
		return chk;
	}
	public void writeChunk(Chunk chk,int dimension,int x,int y) throws IOException{
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(saveFolder+"/chunks/"+dimension+"-"+x+","+y+".dat")));
		for(int p=0;p<16;p++) {
			for(int q=0;q<16;q++) {
				for(int r=0;r<256;r++) {
					Block b=chk.chunk[p][q][r];
					if(b!=null) {
						pw.printf("(%d,%d,%d) %s %s\n", p,q,r,b.getClass().getName(),b.toString());
					}
				}
			}
		}
		pw.close();
		if(pw.checkError()) {
			throw new IOException("2333");
		}
	}
}
