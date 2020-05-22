package io.github.javaherobrine;
import java.math.*;
/**
 * 经验球也是这个类实现entity接口版本
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
