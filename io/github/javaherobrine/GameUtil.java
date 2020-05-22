package io.github.javaherobrine;
import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.*;
import java.util.*;
import java.util.concurrent.*;
public class GameUtil{
	public static Image getImage(String path) throws IOException {
		BufferedImage bi=null;
		Exception e=null;
			URL u=GameUtil.class.getClassLoader().getResource(path);
			bi=ImageIO.read(u);
			return bi;
		}
	public static Font getFont(String path) throws FontFormatException, IOException {
		return Font.createFont(0, new File(path));
	}
}