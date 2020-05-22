package io.github.javaherobrine;
import java.util.*;
public class Command{
	String command;
	/**
	 * 存储所有命令
	 */
	public static ArrayList<Command> commandLib;
	public Command(String comm){
		command=comm;
	}
	Commandble c;
	boolean[] bs;
	Class<?>[] values;
	
	/**
	 * 返回一个无法运行指令（空）<br>
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
	 * 注册一个命令（mod支持）
	 * @param commandName
	 * 命令的名字
	 * @param bs
	 * 与后面的values对应，对应的是可选的参数
	 * @param values
	 * 命令后面可填参数的类型
	 * 
	 */
	public static void regditCommand(String commandName,boolean[] bs,Class<?>[] values,Commandble c) {
		commandLib.add(new Command(commandName,bs,values,c));
	}
	public static interface Commandble{
		/**
		 * 运行命令
		 * @param values 命令填的参数
		 */
		void run(Object... values);
	}
}

