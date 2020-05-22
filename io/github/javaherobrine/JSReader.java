package io.github.javaherobrine;
import javax.script.*;
import java.io.*;
public class JSReader {
	public static Object eval(String jsFile,String function,Object...objects ) {
		Object obj=null;
		ScriptEngine se=new ScriptEngineManager().getEngineByName("javascript");
		try {
			FileReader fr=new FileReader(jsFile);
			se.eval(fr);
			if(se instanceof Invocable) {
				obj=((Invocable) se).invokeFunction(function,objects);
			}	
			fr.close();
		} catch (FileNotFoundException e) {
		} catch (ScriptException e) {
		} catch (NoSuchMethodException e) {
		} catch (IOException e) {
		}finally {
		}
		return obj;
	}
	public static Object eval(String jsCode) throws ScriptException {
		ScriptEngine se=new ScriptEngineManager().getEngineByName("javascript");
		return se.eval(jsCode);
	}
}
