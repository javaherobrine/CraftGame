package io.github.javaherobrine.net;
import io.github.javaherobrine.ioStream.*;
import java.io.IOException;
import java.net.*;
public class UDPClient {
	public static void main(String[] args) throws IOException{
		DatagramSocket ds=new DatagramSocket(8888);
		DatagramPacket dp=new DatagramPacket(new byte[4],4);
		ds.receive(dp);
		int i=IOUtils.byte4ToInt(dp.getData(),0);
		dp=new DatagramPacket(new byte[i],i);
		ds.receive(dp);
		System.out.println(new String(dp.getData()));
	}
}
