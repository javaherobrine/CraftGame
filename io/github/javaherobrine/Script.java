package io.github.javaherobrine;
import java.io.*;
public class Script implements Serializable{
	String cgms;
	@Override
	/**
	 * CGMS的反编译器（主要用于游戏的命令输出）
	 */
	public String toString() {
		return "";
	}
	public Script(String cgms) throws CGMSException {
		CGMSGrammerCheck.check(cgms);
		this.cgms=cgms;
	}
}
