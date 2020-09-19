package io.github.javaherobrine;
import javax.script.*;
import java.util.*;
import jdk.nashorn.api.scripting.*;
public class JavaScript {
	public static final ScriptEngine engine=new ScriptEngineManager().getEngineByName("javascript");
	public static HashMap<Object,Object> parse(String json) throws ScriptException {
		HashMap<Object, Object> map=new HashMap<>();
		processJavaScriptObjectMirror((ScriptObjectMirror)engine.eval("json("+json+")"),map);
		return map;
	}
	static {
		try {
			engine.eval("function json(json){return json}");
		} catch (ScriptException e) {
		}
	}
	public static void processJavaScriptObjectMirror(ScriptObjectMirror object,Map<Object,Object> output) throws ScriptException {
		if(object.isEmpty()) return;
		Iterator<?> iter=object.keySet().iterator();
		Iterator<?> valueIter=object.values().iterator();
		LinkedList<Object> keys=new LinkedList<>();
		LinkedList<Object> values=new LinkedList<>();
		iter.forEachRemaining(key->{
			if(key instanceof ScriptObjectMirror) {
				ScriptObjectMirror mirror=(ScriptObjectMirror)key;
				if(mirror.isArray()) {
					keys.add(mirror.values().toArray());
					return;
				}
				HashMap<Object,Object>map=new HashMap<>();
				try {
					processJavaScriptObjectMirror(mirror,map);
				} catch (ScriptException e) {}
				keys.add(map);
				return;
			}
		});
		valueIter.forEachRemaining(value->{		
			if(value instanceof ScriptObjectMirror) {
				ScriptObjectMirror mirror=(ScriptObjectMirror)value;
				if(mirror.isArray()) {
					values.add(mirror.values().toArray());
					return;
				}
				HashMap<Object,Object>map=new HashMap<>();
				try {
					processJavaScriptObjectMirror(mirror,map);
				} catch (ScriptException e) {}
				values.add(map);
				return;
			}
			values.add(value);
		});
		if(keys.size()!=values.size()) {
			throw new ScriptException("´íÎóµÄJSON");
		}
		int i=keys.size();
		for(int ii=0;ii<i;ii++) {
			output.put(keys.get(ii), values.get(ii));
		}
	}
}
