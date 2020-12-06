package io.github.javaherobrine;
import java.net.*;
public class JarClassLoader extends URLClassLoader {
	public JarClassLoader(URL url) {
		super(new URL[] {url});
	}
}
