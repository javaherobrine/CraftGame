package io.github.javaherobrine;
import java.io.*;
import java.util.stream.*;
/**
 * 通过InputStream读取CGMS
 * @author Java_Herobrine
 */
public class CGMSInputStream extends InputStream{
	private CGMSFile thisFile;
	public String pack;
	private BufferedReader br;
	private InputStream is;
	@Override
	@Deprecated
	public int read() throws IOException {
		close();
		throw new EOFException("你用了错误的方法读取cgms，文件关闭");
	}
	public CGMSInputStream(CGMSFile f) {
		thisFile=f;
	}
	private void getSourceReader() {
		br=new BufferedReader(new InputStreamReader(is));
	}
	public CGMSInputStream(InputStream cgmsis) {
		is=cgmsis;
	}
	public void openStreamByCGMSFile() throws FileNotFoundException {
		is=new FileInputStream(thisFile);
	}
	/**
	 * CGMS0规范
	 * @throws IOException 如果发生IO错误
	 * @throws CGMSException 如果CGMS抛出异常或者CGMS语法有错
	 */
	public synchronized void readCGMS0() throws IOException,CGMSException{
		boolean isAnnotate=false;
		if(br==null) {
			getSourceReader();
		}
		String temp;
		boolean isOnClass=false;
		boolean isOnFunction=false;
		boolean flag=false;
		boolean flag2=false;
		String[] temps;
		while((temp=br.readLine())!=null) {
			temp.trim();
			flag2=temp.indexOf("/*")!=1;
			if(flag&&!flag2||flag2&&temp.indexOf("*/")!=-1) {
				isAnnotate=false;
				continue;
			}else {
				isAnnotate=true;
				flag=false;
				flag2=false;
			}
			temps=temp.split("//");
			temp=temps[0];
			if(temps.length!=1) {
				flag=temps[temps.length-1].endsWith("\\");
				continue;
			}
			if(temp.startsWith("#")) {
				CGMSMeta meta=new CGMSMeta(temp);
				meta.changeCompiler(this);
				continue;
			}
			if(temp.startsWith("class")) {
				CGMSCompiler.code("public class "+thisFile.getName().split(".")[0]);
				String[] ss=temp.split(":");
				isOnClass=true;
				if(ss.length==2) {
					CGMSCompiler.code(" extends "+getClassName(ss[1]));
				}
				CGMSCompiler.code("{");
				CGMSCompiler.crlf();
				continue;
			}
			if(temp.startsWith("function")) {
				String type=temp.split("<")[2].split(">")[0];
				String functionName=temp.split(" ")[1];
				String[] args=temp.split("(")[1].split(")")[0].split(",");
				if(!isOnClass) {
					CGMSCompiler.temp("public static "+type+functionName+getMethodArgs(args));
				}else {
					CGMSCompiler.code("public "+type+functionName+getMethodArgs(args));
				}
				isOnFunction=true;
			}
			if(temp.endsWith(";")) {
				String[] tempss=temp.split(";");
				Stream<String> stream=Stream.of(tempss);
				stream.forEach(s->{
					
				});
			}
		}
		if(isAnnotate) {
			throw new CGMSException("意外结束的注释");
		}
	}
	@Deprecated
	public static CGMSInputStream nullCGMSInputStream() {
		return new CGMSInputStream(nullInputStream()) {
			public int read() throws EOFException{
				throw new EOFException("EOF");
			}
		};
	}
	public static String getClassName(String cgmsClass) throws CGMSException{
		if(cgmsClass.startsWith("Mod")) {
			throw new CGMSException(cgmsClass.split("<")[0]+"不是一个CGMS类集合");
		}else {
			return getValue(cgmsClass);
		}
	}
	public static String getValue(String cgmsSet) {
		return cgmsSet.split("<")[1].split(">")[0];
	}
	public static String getMethodArgs(String[] args) {
		StringBuilder temp0=new StringBuilder();
		Stream<String> s=Stream.of(args);
		s.forEach(ss ->{
			temp0.append(getValue(ss.split(" ")[0])).append(" ").append(ss.split(" ")[1]);
		});
		return temp0.toString();
	}
}