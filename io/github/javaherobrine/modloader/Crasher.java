package io.github.javaherobrine.modloader;
import javax.swing.*;
public class Crasher {
	private static String str="";
	protected static void crash() {
		if(!str.equals("")) {
			JOptionPane.showMessageDialog(null, str, "mod加载出现了点问题", JOptionPane.ERROR_MESSAGE, null);
			System.exit(-1);
		}
	}
	public static void addErrorMessage(String msg) {
		str+=(msg+"\n");
	}
}
