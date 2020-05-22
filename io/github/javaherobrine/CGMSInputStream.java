package io.github.javaherobrine;
import java.io.*;
import java.net.*;
/**
 * 通过InputStream读取CGMS
 * @author Java_Herobrine
 */
public class CGMSInputStream extends InputStream{
	private CGMSFile thisFile;
	public String pack;
	private BufferedReader br;
	private URL u;
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
	public CGMSInputStream(URL u) {
		this.u=u;
	}
	public void openStreamByUrl() throws IOException {
		if(u==null) {
			return;
		}
		HttpURLConnection conn=(HttpURLConnection) u.openConnection();
		conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36Name");
		is=conn.getInputStream();
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
		CGMSGrammerCheck.check(thisFile);
		if(br==null) {
			getSourceReader();
		}
		String temp;
		boolean flag=false;
		boolean flag2=false;
		String[] temps;
		while((temp=br.readLine())!=null) {
			temp.trim();
			flag2=temp.split("/*").length!=1;
			if(flag&&!flag2||flag2&&temp.split("*/").length==1) {
				continue;
			}else {
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
			if(temp.startsWith("class")&&temp.split(":").length==2) {
				if(temp.split(":")[1].equals("Mod")) {
					temp=temp.split("<")[1].split(">")[0];
					CGMSCompiler.code("public class "+thisFile.getName().split(".")[0]+"extends "+temp+"{");
					continue;	
				}
			}
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
}