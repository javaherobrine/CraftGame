package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.ioStream.IOUtils;
public class Client extends ServerClientInterface{
	DatagramSocket socket;
	Socket client;
	DatagramPacket dp;
	DatagramPacket dp2;
	public Client() {
	}
	public static String getIP0(){  
        try {
            String localip = null;  
            String netip = null;  
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();  
            InetAddress ip = null;  
            boolean finded = false;  
            while (netInterfaces.hasMoreElements() && !finded) {  
                NetworkInterface ni = netInterfaces.nextElement();  
                Enumeration<InetAddress> address = ni.getInetAddresses();  
                while (address.hasMoreElements()) {  
                   ip = address.nextElement();  
                   if(!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()) {  
                        netip = ip.getHostAddress();  
                        finded = true;  
                        break;  
                    }else if(ip.isSiteLocalAddress() && !ip.isLoopbackAddress()) {  
                        localip = ip.getHostAddress();  
                    }  
                }  
            }  
            if (netip != null && !"".equals(netip)) {  
                return netip;  
            } else {  
                return localip;  
            }  
        } catch (Exception e) {
            return "";
        }
    }
	public static InetAddress getIP() throws UnknownHostException {
		return InetAddress.getByName(getIP0());
	}
	public void linkServer(String url,int port) throws UnknownHostException, IOException {
		this.client=new Socket(url,port);
		this.client.setKeepAlive(true);
	}
	public void disconnect() throws IOException {
		this.client.close();
	}
	public void linkServerByUDP(int port,String url,int serverPort) throws SocketException, UnknownHostException {
		socket=new DatagramSocket(port);
		dp=new DatagramPacket(null, 0, InetAddress.getByName(url), serverPort);
	}
	public static void main(String[] args) throws IOException {
		Client c=new Client();
		c.linkServer("localhost",8888);
		c.client.setKeepAlive(true);
		OutputThread ot=new OutputThread(c.client);
		ot.start();
		InputThread it=new InputThread(c.client);
		it.start();
		ot.write("asduhfsdfosfvdjfsd".getBytes());
		System.out.println(new String(it.readNBytes(IOUtils.byte4ToInt(it.readNBytes(4),0))));
	}
}
