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
		} catch (ScriptException e) {}
	}
	public static String json(Object object) {
		return json(object,new StringBuilder());
	}
	public static String json(Object object,StringBuilder sb) {
		if(object instanceof CharSequence) {
			sb.append("\""+object.toString()+"\"");
		}else if(object instanceof Object[]) {
			if(object instanceof Number[]) {
				sb.append("[\n");
				for(Number o:(Number[])object) {
					sb.append(o.toString());
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}
			sb.append("[\n");
			for(Object o:(Object[])object) {
				sb.append(json(o));
				sb.append(",");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append("\n],");
		}else {
			if(object instanceof byte[]) {
				sb.append("[\n");
				for(byte b:(byte[])object) {
					sb.append(Byte.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else if(object instanceof short[]) {
				sb.append("[\n");
				for(short b:(short[])object) {
					sb.append(Short.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else if(object instanceof int[]) {
				sb.append("[\n");
				for(int b:(int[])object) {
					sb.append(Integer.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else if(object instanceof long[]) {
				sb.append("[\n");
				for(long b:(long[])object) {
					sb.append(Long.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else if(object instanceof float[]) {
				sb.append("[\n");
				for(float b:(float[])object) {
					sb.append(Float.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else if(object instanceof double[]) {
				sb.append("[\n");
				for(double b:(double[])object) {
					sb.append(Double.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else if(object instanceof boolean[]) {
				sb.append("[\n");
				for(boolean b:(boolean[])object) {
					sb.append(Boolean.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else if(object instanceof char[]) {
				sb.append("[\n");
				for(char b:(char[])object) {
					sb.append(Character.toString(b));
					sb.append(",");
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("\n],");
				return sb.toString();
			}else {
				sb.append("{\n");
				Stream.of(object.getClass().getFields()).filter(field->{
					return !Modifier.isFinal(field.getModifiers())&&(Modifier.isStatic(field.getModifiers())||field.canAccess(object)||Modifier.isTransient(field.getModifiers()));
				}).forEach(field->{
					try {
						sb.append("\""+field.getName()+"\""+":");
						Object thisFie=field.get(object);
						if(thisFie!=null) {
							if(thisFie instanceof Object[]) {	
								sb.append("[\n");
								for(Object o:(Object[])thisFie) {
									sb.append(json(o));
									sb.append(",");
								}
								sb.delete(sb.length()-1, sb.length());
								sb.append("\n],");
							}else if(thisFie instanceof Number) {
								sb.append(thisFie.toString()+",\n");
							}else if(thisFie instanceof CharSequence) {
								sb.append("\""+thisFie.toString()+",\"");
							}else {
								sb.append(json(thisFie)+",\n");
							}
						}else {
							sb.append("undefined,\n");
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {}
				});
				sb.delete(sb.length(),sb.length());
				sb.append("}");
			}
		}
		return sb.toString();
	}
}