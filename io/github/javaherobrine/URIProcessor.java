package io.github.javaherobrine;
import java.awt.*;
import java.io.*;
import java.net.*;
public final class URIProcessor {
	public static final Desktop UTIL=Desktop.getDesktop();
	private URIProcessor() {}
	public static final boolean isURL(URI uri) {
		try {
			uri.toURL();
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}
	public static final void open(URI uri) throws IOException {
		UTIL.browse(uri);
	}
	public static final void mailto(String addr) throws IOException {
		UTIL.mail(URI.create("mailto:"+addr));
	}
}
