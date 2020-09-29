package io.github.javaherobrine;
import javax.script.*;
import java.util.*;
import java.util.stream.*;
import java.lang.reflect.*;
public class JavaScript {
	public static final ScriptEngine engine=new ScriptEngineManager().getEngineByName("javascript");
	public static Map parse(String json) throws ScriptException {
		return (Map)engine.eval("json("+json+")");
	}
	static {
		try {
			engine.eval("function json(json){return json}");
		} catch (ScriptException e) {
		}
	}
	public static String json(Object object) {
		final StringBuilder sb=new StringBuilder("{\n");
		Stream.of(object.getClass().getFields()).filter(field->{
			return field.canAccess(object)||Modifier.isTransient(field.getModifiers());
		}).forEach(field->{
			try {
				sb.append("\""+field.getName()+"\""+":");
				Object thisFie=field.get(object);
				if(thisFie instanceof Number) {
					sb.append("\""+thisFie.toString()+"\",\n");
				}else if(thisFie instanceof String) {
					sb.append("\""+thisFie+",\"");
				}else {
					sb.append("\""+json(thisFie)+",\n");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
			}
		});
		sb.delete(sb.length()-2,sb.length()-2);
		sb.append("}");
		return sb.toString();
	}
}