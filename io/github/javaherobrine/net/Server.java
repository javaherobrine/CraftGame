package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.ioStream.*;
import io.github.javaherobrine.net.sync.*;
public abstract class Server implements Closeable {
	ServerSocket server;
	Thread hook=new Thread(()->{
		try {
			server.close();
		} catch (IOException e) {}
	});
	{
		Runtime.getRuntime().addShutdownHook(hook);
	}
	public LinkedList<ServerSideClient> clients=new LinkedList<>();
	@Override
	public void close() throws IOException {
		Runtime.getRuntime().removeShutdownHook(hook);
		server.close();
	}
	public Server(ServerSocket i) {
		this.server=i;
	}
	public class ServerThread extends Thread{
		@Override
		public void run() {
			while(true) {
				try {
					new Thread(Server.this.getImpl()).start();;
				} catch (IOException e) {}
			}
		}
	}
//	public synchronized ServerSideClient accept() throws IOException{
//		ServerSideClient c=new ServerSideClient(server.accept(),this);
//		BufferedReader br=new BufferedReader(new InputStreamReader(c.is,"UTF-8"));
//		boolean accepted=false;
//		while(!accepted) {
//			TransmissionFormat format;
//			try {
//				format=TransmissionFormat.valueOf(br.readLine());	
//			}catch(Exception e) {
//				c.os.write(IOUtils.intToByte4(1));
//				continue;
//			}
//			if(format==TransmissionFormat.RECONNECT) {
//				int id=Integer.parseInt(br.readLine());
//				ServerSideClient oldClient=clients.remove(id);
//				c.msg=oldClient.msg;
//				clients.add(id,c);
//			}
//			if(format==TransmissionFormat.FINISH) {
//				c.os.write(IOUtils.intToByte4(-10));
//				c.msg.connected=false;
//				c.msg.format=null;
//				c.msg.status=TransmissionStatus.CONTINUE;
//				c.msg.id=-1;
//				c.close();
//				break;
//			}else {
//				c.os.write(IOUtils.intToByte4(0));
//				String[] cmods=br.readLine().split(",");
//				Arrays.sort(cmods);
//				String[] smods=ModLoader.loader.toString().split(",");
//				Arrays.sort(smods);
//				if(Arrays.equals(cmods, smods)) {
//					c.os.write(IOUtils.intToByte4(1));
//					clients.add(c);
//					c.msg.connected=true;
//					c.msg.format=format;
//					c.msg.status=TransmissionStatus.ACCEPTED;
//					c.os.write(IOUtils.intToByte4(clients.indexOf(c)));
//					c.msg.id=clients.indexOf(c);
//					c.msg.mods=cmods;
//					accepted=true;
//					/*if(c.msg.format==TransmissionFormat.OBJECT) {
//						c.in=new ObjectInputStream(c.is);
//						c.out=new ObjectOutputStream(c.os);
//					}else */if(c.msg.format==TransmissionFormat.JSON) {
//						c.in=new JSONInputStream(c.is);
//						c.out=new JSONOutputStream(c.os);
//					}
//				}else {
//					c.os.write(IOUtils.intToByte4(-1));
//				}
//			}
//		}
//		return c;
//	}
	public DefaultSynchronizeImpl getImpl() throws IOException{
		return new DefaultSynchronizeImpl(accept());
	}
	public abstract ServerSideClient accept() throws IOException;
}