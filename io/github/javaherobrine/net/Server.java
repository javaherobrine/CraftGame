package io.github.javaherobrine.net;
import java.io.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.ioStream.*;
public class Server implements Closeable {
	ServerSocket server;
	LinkedList<Client> clients=new LinkedList<>();
	@Override
	public void close() throws IOException {
		server.close();
	}
	public Server(ServerSocket server) {
		this.server=server;
	}
	public Client accept() throws IOException{
		Client c=new Client(server.accept());
		BufferedReader br=new BufferedReader(new InputStreamReader(c.is,"UTF-8"));
		boolean accepted=false;
		while(!accepted) {
			TransmissionFormat format;
			try {
				format=TransmissionFormat.valueOf(br.readLine());	
			}catch(Exception e) {
				c.os.write(IOUtils.intToByte4(1));
				continue;
			}
			if(format==TransmissionFormat.FINISH) {
				c.msg.connected=false;
				c.msg.format=null;
				c.msg.status=TransmissionStatus.CONTINUE;
				c.msg.id=-1;
				c.close();
				break;
			}else {
				clients.add(c);
				c.msg.connected=true;
				c.msg.format=format;
				c.msg.status=TransmissionStatus.ACCEPTED;
				c.os.write(IOUtils.intToByte4(0));
				c.os.write(IOUtils.intToByte4(clients.indexOf(c)));
				c.msg.id=clients.indexOf(c);
				accepted=true;
			}
		}
		return c;
	}
}