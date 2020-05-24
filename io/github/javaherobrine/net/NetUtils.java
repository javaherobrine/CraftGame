package io.github.javaherobrine.net;
import io.github.javaherobrine.ioStream.*;
import java.io.*;
import java.net.*;
/**
 * ���繤��
 * @author Java_Herobrine
 */
public final class NetUtils extends IOUtils {
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
	 */
	public synchronized static void smtp(String smtp, String reciver, String sender, String cc, String uname,
			String pwd,String data) {
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
			bw.println("mail from:<"+sender+">");
			System.out.println(br.readLine());
			bw.println("rcpt to:<"+reciver+">");
			System.out.println(br.readLine());
			bw.println("data");
			System.out.println(br.readLine());
			bw.println("subject:"+cc);
			bw.println("from:"+sender);
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
}
