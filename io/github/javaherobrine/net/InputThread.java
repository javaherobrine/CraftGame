package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
public class InputThread extends Thread {
	private InputStream is;
	public volatile int inputLength=-1;
	public volatile byte[] data=null;
	public volatile int off=0;
	public volatile int mark=0;
	public volatile ByteArrayOutputStream ais=new ByteArrayOutputStream();
	public InputThread(InputStream is) {
		this.is=is;
	}
	public InputThread(Socket soc) throws IOException {
		this(soc.getInputStream());
	}
	private void reset(){
		mark=0;
		off=0;
		inputLength=-1;
		ais.reset();
	}
	private int temp;
	private void read() throws IOException {
		ais.write(is.read());
	}
	@Override
	public void run() {
		while(true) {
			if(inputLength==-1) {
				try {
					sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					continue;
				}
			}
			try {
				if(mark>=inputLength){
					data=getData();
					if(mark==inputLength) {
						reset();
					}
				}
				read();
				mark+=1;
			}catch(SocketException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private byte[] getData() throws IOException {
		ByteArrayInputStream bis=new ByteArrayInputStream(ais.toByteArray());
		off+=inputLength;
		byte[] bs=new byte[off];
		bis.read(bs);
		return bs;
	}
	public synchronized byte[] readNBytes(int length){
		inputLength=length;
		data=null;
		interrupt();
		byte[] temp=getData0();
		inputLength=-1;
		return temp;
	}
	private synchronized byte[] getData0() {
		while(data==null) {}
		inputLength=-1;
		return data;
	}
}
