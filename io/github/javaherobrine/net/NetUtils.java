package io.github.javaherobrine.net;
import io.github.javaherobrine.ioStream.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
/**
 * 网络工具
 * @author Java_Herobrine
 */
public final class NetUtils extends IOUtils {
	public static final HttpClient DEFAULT_CLIENT=HttpClient.newHttpClient();
	/**
	 * 检测地址是否存在，网络是否连通
	 * @param host 地址
	 * @return 是否存在
	 * @throws UnknownHostException
	 * @throws IOException
	 */
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
	/**
	 * 一个邮件接口
	 * @param smtp smtp服务器地址
	 * @param reciver 接收方地址
	 * @param sender 发送方地址
	 * @param cc 标题
	 * @param uname 发送方邮箱名
	 * @param pwd 发送方邮箱密码
	 * @param data 正文
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public synchronized static void smtp(String smtp, String reciver, String sender,String fakeSender, String cc, String uname,
			String pwd,String data) throws UnknownHostException, IOException {
		uname = encode(uname);
		pwd = encode(pwd);
		Socket client=new Socket(smtp,25);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter bw = new PrintWriter(client.getOutputStream(), true);
			bw.println("helo jaro");
			System.out.println(br.readLine());
			bw.println("auth login");
			System.out.println(br.readLine());
			bw.println(uname);
			System.out.println(br.readLine());
			bw.println(pwd);
			System.out.println(br.readLine());
			bw.println("mail from:<"+sender+">");
			System.out.println(br.readLine());
			bw.println("rcpt to:<"+reciver+">");
			System.out.println(br.readLine());
			bw.println("data");
			System.out.println(br.readLine());
			bw.println("subject:"+cc);
			bw.println("from:"+fakeSender);
			bw.println("to:"+reciver);
			bw.println("Content-Type: text/plain;charset=\"gb2312\"");
			bw.println();
			bw.println(data);
			bw.println(".");
			bw.println("");
			System.out.println(br.readLine());
			bw.println("rest");
			bw.println("quit");
			bw.flush();
			System.out.println(br.readLine());
		} catch (UnknownHostException e) {
		} catch (IOException e) {
			System.out.println("网络错误");
		}
		client.close();
	}
	/**
	 * 根据指定的域名解析ip
	 */
	public static String dns(String host) throws UnknownHostException {
		return InetAddress.getByName(host).toString();
	}
	/**
	 * 用post请求向服务器发送数据
	 * @param url 服务器地址
	 * @param param post的内容，键值对格式
	 * @return 服务器返回的内容
	 */
	public static byte[] post(String url, String param) {
		byte[] response=null;
		try {
			HttpRequest req=HttpRequest.newBuilder().uri(new URI(url)).POST(HttpRequest.BodyPublishers.ofString(param)).build();
			response=DEFAULT_CLIENT.send(req, HttpResponse.BodyHandlers.ofByteArray()).body();
		}catch(IOException | URISyntaxException | InterruptedException e) {
		}
		return response;
	}
}
