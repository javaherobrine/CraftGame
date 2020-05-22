package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class InputThread extends Thread {
	private InputStream is;
	public volatile ByteArrayOutputStream ais=new ByteArrayOutputStream();
	public InputThread(InputStream is) {
		this.is=is;
	}
	public InputThread(Socket soc) throws IOException {
		this(soc.getInputStream());
	}
	private void reset(){
		NetStatus.mark=0;
		NetStatus.off=0;
		NetStatus.inputLength=-1;
		ais.reset();
	}
	private int temp;
	private void read() throws IOException {
		ais.write(is.read());
	}
	@Override
	public void run() {
		while(true) {
			if(NetStatus.isWrite) {
			}
			if(NetStatus.inputLength==-1) {
				continue;
			}
			try {
				if(NetStatus.mark>=NetStatus.inputLength){
					NetStatus.data=getData();
					if(NetStatus.mark==NetStatus.inputLength) {
						reset();
					}
				}
				read();
				NetStatus.mark+=1;
			}catch(SocketException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private byte[] getData() throws IOException {
		ByteArrayInputStream bis=new ByteArrayInputStream(ais.toByteArray());
		NetStatus.off+=NetStatus.inputLength;
		byte[] bs=new byte[NetStatus.off];
		bis.read(bs);
		return bs;
	}
	public byte[] readNBytes(int length){
		NetStatus.isWrite=false;
		NetStatus.inputLength=length;
		byte[] temp=NetStatus.getData();
		NetStatus.inputLength=-1;
		NetStatus.data=null;
		return temp;
	}
	public static void main(String[] args) {
		int i=1/0;
	}
}
