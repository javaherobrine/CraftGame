package io.github.javaherobrine;
import javax.script.*;
/**
 * 为CGMS抛出的异常和CGMS语法错误的异常
 * @author Java_Herobrine
 */
public class CGMSException extends ScriptException {
	public CGMSException(String msg) {
		super(msg);
	}
}
