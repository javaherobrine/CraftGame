package io.github.javaherobrine.modloader;
import javax.swing.*;
public class Crasher {
	private static String str="";
	protected static void crash() {
		if(!str.equals("")) {
			JOptionPane.showMessageDialog(null, str, "mod���س����˵�����", JOptionPane.ERROR_MESSAGE, null);
			System.exit(-1);
		}
	}
	public static void addErrorMessage(String msg) {
		str+=(msg+"\n");
	}
}
