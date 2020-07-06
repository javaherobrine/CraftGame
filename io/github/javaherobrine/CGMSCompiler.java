package io.github.javaherobrine;
import java.io.*;
import javax.tools.*;
@Deprecated
public class CGMSCompiler {
	private static StringBuilder code;
	static StringBuilder temp;
	public static void temp(String function) {
		temp.append(function);
	}
	public static void crlfInTemp() {
		temp(Constants.CRLF);
	}
	public static void code(String code) {
		CGMSCompiler.code.append(code);
	}
	public static void crlf() {
		code(Constants.CRLF);
	}
	public static void compile(File bin,File src,CGMSFile cf) throws IOException, CGMSException {
		CGMSInputStream cis=new CGMSInputStream(cf);
		cis.readCGMS0();
		cis.close();
		String s=cf.getName().split(".")[0];
		s+=".java";
		src=new File(s);
		BufferedWriter bw=new BufferedWriter(new FileWriter(src));
		bw.write(code.toString());
		bw.close();
		JavaCompiler javac=ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager f=javac.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> iter=f.getJavaFileObjects(src);
		JavaCompiler.CompilationTask task=javac.getTask(null,f,null,null,null,iter);
		task.call();
		f.close();
		bin.mkdirs();
		bin.createNewFile();
		src=new File(s.split(".")[0]+".class");
		FileInputStream fis=new FileInputStream(src);
		FileOutputStream fos=new FileOutputStream(bin);
		fos.write(fis.readAllBytes());
		fis.close();
		fos.close();
	}
}
