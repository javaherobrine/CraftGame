package io.github.javaherobrine;
import java.math.*;
/**
 * ������Ҳ�������ʵ��entity�ӿڰ汾
 * @author c6200
 *
 */
public class Level {
	public static final Command xp=new Command("xp");
	BigInteger level;
	BigInteger exp;
	public void Sort(Level l) {
		BigInteger bi=this.exp.add(l.exp);
		String str=bi.toString();
		
	}
	public Level(String level) {
		this.exp=new BigInteger(level);
	}
}
