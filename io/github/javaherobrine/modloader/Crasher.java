package io.github.javaherobrine.modloader;
import javax.swing.*;
import java.util.*;
public class Crasher {
	private static String str="";
	public static String SC_SYNCHRONIZED_MODS="";
	protected static void crash(String msg) {
		if(!str.equals("")) {
			JOptionPane.showMessageDialog(null, str, msg, JOptionPane.ERROR_MESSAGE, null);
			System.exit(-1);
		}
	}
	public static void addErrorMessage(String msg) {
		str+=(msg+"\n");
	}
	public static void check(String mods) {
		String[] strs=mods.split(",");
		String[] strss=SC_SYNCHRONIZED_MODS.split(";");
		Arrays.sort(strs);
		Arrays.sort(strss);
		if(Arrays.equals(strs, strss)) {
			return;
		}else {
			mods.replace(",", "\n");
			addErrorMessage("��������\n"+mods+"\n�ͻ��ˣ�\n"+SC_SYNCHRONIZED_MODS.replace(",","\n"));
			crash("�������Ϳͻ��˵�mod��ͬ��");
		}
	}
	public static boolean checkVersion(String version,String[] allowed) {
		for(int i=0;i<allowed.length;i++) {
			if(allowed[i].equals(version)) {
				return true;
			};
		}
		return false;
	}
}
