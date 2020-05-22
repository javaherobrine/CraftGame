package io.github.javaherobrine.net;
import io.github.javaherobrine.ioStream.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;
public final class NetUtils extends IOUtils {
	public static boolean icmp(String host) throws UnknownHostException, IOException {
		String[] temps = host.split("//");
		if (temps.length == 2) {
			return InetAddress.getByName(temps[1].split("/")[0]).isReachable(10000);
		} else {
			return InetAddress.getByName(temps[0].split("/")[0]).isReachable(10000);
		}
	}
	private NetUtils() {
	}
	public synchronized static void smtp(String smtp, String reciver, String sender, String cc, String uname,
			String pwd) {
		uname = encode(uname);
		pwd = encode(pwd);
		Client c = new Client();
		try {
			c.linkServer(smtp, 25);
			BufferedReader br = new BufferedReader(new InputStreamReader(c.client.getInputStream()));
			PrintWriter bw = new PrintWriter(c.client.getOutputStream(), true);
			bw.println("helo javaherobrine");
			System.out.println(br.readLine());
			bw.println("auth login");
			System.out.println(br.readLine());
			bw.println(uname);
			System.out.println(br.readLine());
			bw.println(pwd);
			System.out.println(br.readLine());
			bw.println("mail from:<javaherobrine@qq.com>");
			System.out.println(br.readLine());
			bw.println("rcpt to:<c620009897@163.com>");
			System.out.println(br.readLine());
			bw.println("data");
			System.out.println(br.readLine());
			bw.println("subject:≤‚ ‘” º˛");
			bw.println("from:javaherobrine@qq.com");
			bw.println("to:c620009897@163.com");
			bw.println("Content-Type: text/plain;charset=\"gb2312\"");
			bw.println();
			bw.println("Hi£¨’‚ «≤‚ ‘Java SMTPµƒ” º˛£¨…æµÙº¥ø…");
			bw.println(".");
			bw.println("");
			System.out.println(br.readLine());
			bw.println("rest");
			bw.println("quit");
			bw.flush();
			System.out.println(br.readLine());
		} catch (UnknownHostException e) {
		} catch (IOException e) {
			System.out.println("Õ¯¬Á¥ÌŒÛ");
		}
	}
	public static String dns(String host) throws UnknownHostException {
		return InetAddress.getByName(host).toString();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		// smtp("smtp.qq.com",null,null,null,"javaherobrine@qq.com","fkvnchacfdkubffc");
		// System.out.println(dns("sss.i0x0.cn"));
	}
	public static String post(String url, String param) {
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn=(HttpURLConnection) realUrl.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			OutputStreamWriter out=new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(param);
			out.flush();
			BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		}catch(IOException e) {
		}
		return result;
	}
	public void UDPSObjectOutputStream(Object obj,Client c) throws IOException {
		byte[] temp=objectToByteArray(obj);
		byte[] temp2=intToByte4(temp.length);
		c.dp.setLength(4);
		c.dp.setData(temp2);
		c.socket.send(c.dp);
		c.dp.setLength(temp.length);
		c.dp.setData(temp);
		c.socket.send(c.dp);
	}
	public Object UDPObjectInputStream(Client c) throws IOException{	
		DatagramPacket dp=new DatagramPacket(new byte[4],4);
		c.socket.receive(dp);
		int i=byte4ToInt(dp.getData(),0);
		dp.setLength(i);
		dp.setData(new byte[i]);
		c.socket.receive(dp);
		try {
			return byteArrayToObject(dp.getData());
		} catch (ClassNotFoundException e) {
			throw new IOException(e.getException());
		}
	}
}
