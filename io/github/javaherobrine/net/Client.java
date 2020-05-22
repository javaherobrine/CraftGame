package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
//import io.github.javaherobrine.JarMod;
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
	//public static boolean sort(ArrayList<String> a) {
		//Object[] s=a.toArray();
		//Object[] s2=JarMod.temp.toArray();
		//Arrays.sort(s);
		//Arrays.sort(s2);
		//return Arrays.equals(s,s2);
	//}
	public void disconnect() throws IOException {
		this.client.close();
	}
	public void linkServerByUDP(int port,String url,int serverPort) throws SocketException, UnknownHostException {
		socket=new DatagramSocket(port);
		dp=new DatagramPacket(null, 0, InetAddress.getByName(url), serverPort);
	}
	/**
	 * �����ݵķ�ʽ���޸�NetStatus.outputDataΪ����<br />
	 * �������ݵķ�ʽ���޸�NetStatus.inputLengthΪ���ճ��ȣ�Ȼ��NetStatus.getData();��������ɺ�NetStatus.inputLength��Ϊ-1��NetStatus.data��Ϊnull
	 * @param args 2333
	 * @throws IOException 2333
	 */
	public static void main(String[] args) throws IOException {
		Client c=new Client();
		c.linkServer("localhost",8888);
		c.client.setKeepAlive(true);
		new OutputThread(c.client).start();
		new InputThread(c.client).start();
		NetStatus.isWrite=true;
		NetStatus.outputData="Jaro".getBytes();
		NetStatus.isWrite=false;
		NetStatus.inputLength=4;
		int temp=IOUtils.byte4ToInt(NetStatus.getData(),0);
		NetStatus.data=null;
		NetStatus.inputLength=temp;
		byte[] bs=NetStatus.getData();
		System.out.println("Get OutputStream Object");
		System.out.println("Get InputStream Object");
	}
}
