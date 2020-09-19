package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
public class Server implements Closeable {
	public static HashMap<String,Socket> sockets=new HashMap<>();
	public int serverPort;
	public ServerSocket s;
	public ArrayList<Client> clients=new ArrayList<>();
	public void open() throws IOException {
		this.s = new ServerSocket(this.serverPort);
	}
	public Server(int port) {
		this.serverPort = port;
	}
	public void close() throws IOException {
		this.s.close();
	}
	public static void disconnection(String playerName) throws IOException {
		sockets.get(playerName).close();
	}
	public Client accept() throws IOException {
		return shakeHands(Client.initClientFromSocket(s.accept()));
	}
	public static Client shakeHands(Client c,int id) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(c.tis,"UTF-8"));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(c.tos,"UTF-8"));
		DataOutputStream dos=new DataOutputStream(c.tos);
		TransmissionFormat[] allFormats=TransmissionFormat.values();
		while(true) {
			try {
				TransmissionFormat f=TransmissionFormat.valueOf(br.readLine());
				if(f==TransmissionFormat.FINISH) {
					c.msg.connected=false;
				}
				if(Arrays.binarySearch(allFormats, f)>0) {
					c.msg.format=f;
					bw.write(TransmissionStatus.ACCEPTED.toString());
					bw.newLine();
					bw.flush();
					break;
				}else {
					bw.write(TransmissionStatus.CONTINUE.toString());
					bw.newLine();
					bw.flush();
				}
			}catch(Exception e) {}
		}
		c.msg.id=id;
		dos.writeInt(id);
		return c;
	}
	public Client shakeHands(Client c) throws IOException {
		DataInputStream dis=new DataInputStream(c.tis);
		int id=dis.readInt();
		if(id!=-1) {
			Client temp=clients.get(id);
			c.msg=temp.msg;
			c.msg.connected=true;
			clients.remove(id);
			clients.add(id,c);
			return c;
		}
		clients.add(c);
		return shakeHands(c,clients.indexOf(c));
	}
}