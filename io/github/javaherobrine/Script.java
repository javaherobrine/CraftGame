package io.github.javaherobrine;
import java.io.*;
public class Script implements Serializable{
	String cgms;
	@Override
	/**
	 * CGMS�ķ�����������Ҫ������Ϸ�����������
	 */
	public String toString() {
		return "";
	}
	public Script(String cgms) throws CGMSException {
		CGMSGrammerCheck.check(cgms);
		this.cgms=cgms;
	}
}
