package io.github.javaherobrine.net;
import io.github.javaherobrine.ioStream.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
/**
 * ���繤��
 * @author Java_Herobrine
 */
public final class NetUtils extends IOUtils {
	public static final HttpClient DEFAULT_CLIENT=HttpClient.newHttpClient();
	/**
	 * ����ַ�Ƿ���ڣ������Ƿ���ͨ
	 * @param host ��ַ
	 * @return �Ƿ����
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
	 * һ���ʼ��ӿ�
	 * @param smtp smtp��������ַ
	 * @param reciver ���շ���ַ
	 * @param sender ���ͷ���ַ
	 * @param cc ����
	 * @param uname ���ͷ�������
	 * @param pwd ���ͷ���������
	 * @param data ����
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
			System.out.println("�������");
		}
		client.close();
	}
	/**
	 * ����ָ������������ip
	 */
	public static String dns(String host) throws UnknownHostException {
		return InetAddress.getByName(host).toString();
	}
	/**
	 * ��post�������������������
	 * @param url ��������ַ
	 * @param param post�����ݣ���ֵ�Ը�ʽ
	 * @return ���������ص�����
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
