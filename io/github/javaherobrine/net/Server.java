package io.github.javaherobrine.net;
import java.io.*;
import io.github.javaherobrine.*;
import java.net.*;
import java.util.*;
import io.github.javaherobrine.ioStream.*;
import io.github.javaherobrine.net.sync.*;
public class Server implements Closeable {
	private static final StringPrintStream STDOUT=new StringPrintStream(System.out,(str)-> {return "[Server]"+str;});
	ServerSocket server;
	public static Server thisServer;
	public LinkedList<Client> clients=new LinkedList<>();
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
				STDOUT.print("Client Transmission Format Not Support\r\n");
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
				STDOUT.print("A client connected\r\n");
			}
		}
		return c;
	}
	public ClientSideSynchronizeImpl.ServertSideSynchronizeImpl getImpl() throws IOException{
		return new ClientSideSynchronizeImpl(accept()).new ServertSideSynchronizeImpl();
	}
}