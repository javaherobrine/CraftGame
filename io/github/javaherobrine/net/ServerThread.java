package io.github.javaherobrine.net;
import java.net.*;
import io.github.javaherobrine.ioStream.*;
import java.io.*;
/**
 * �����Զ����տͻ������������߳�
 * @author Java_Herobrine
 */
public class ServerThread extends Thread{
	Server s;
	public void run() {
		Socket temp;
		while(true) {
			try {
				temp=s.s.accept();
				InputThread it=new InputThread(temp);
				it.start();
				OutputThread ot=new OutputThread(temp);
				ot.start();
				Server.sockets.put(new String(it.readNBytes((IOUtils.byte4ToInt(it.readNBytes(4),0)))),temp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
