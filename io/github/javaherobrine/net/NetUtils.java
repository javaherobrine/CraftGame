package io.github.javaherobrine.net;
import io.github.javaherobrine.ioStream.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
public final class NetUtils extends IOUtils {
	public static final HttpClient DEFAULT_CLIENT=HttpClient.newHttpClient();
	public static boolean icmp(String host) throws UnknownHostException, IOException {
		String[] temps = host.split("//");
		if (temps.length == 2) {
			return InetAddress.getByName(temps[1].split("/")[0]).isReachable(10000);
		} else {
			return InetAddress.getByName(temps[0].split("/")[0]).isReachable(10000);
		}
	}
	private NetUtils() {}
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
