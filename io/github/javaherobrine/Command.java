package io.github.javaherobrine;
import java.util.*;
public class Command{
	String command;
	/**
	 * �洢��������
	 */
	public static ArrayList<Command> commandLib;
	public Command(String comm){
		command=comm;
	}
	Commandble c;
	boolean[] bs;
	Class<?>[] values;
	
	/**
	 * ����һ���޷�����ָ��գ�<br>
	 * @see io.github.javaherobrine.Command
	*/
	public Command() {
	}
	/**
	 * @author Java_Herobrine
	 * @param Command
	 */
	public static void RunCommand(Command c) {
		
	}
	public Command(String s,boolean[] bs,Class<?>[] values,Commandble c) {
		this.bs=bs;
		this.command=s;
		this.values=values;
		this.c=c;
	}
	/**
	 * ע��һ�����mod֧�֣�
	 * @param commandName
	 * ���������
	 * @param bs
	 * ������values��Ӧ����Ӧ���ǿ�ѡ�Ĳ���
	 * @param values
	 * �������������������
	 * 
	 */
	public static void regditCommand(String commandName,boolean[] bs,Class<?>[] values,Commandble c) {
		commandLib.add(new Command(commandName,bs,values,c));
	}
	public static interface Commandble{
		/**
		 * ��������
		 * @param values ������Ĳ���
		 */
		void run(Object... values);
	}
}

