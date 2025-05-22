package io.github.javaherobrine.format;
import java.io.*;
import java.util.*;
import java.math.*;
/**
 * A reader that can deserialize JSON
 * <br />
 * please don't call deprecated functions, their implementations are wrong
 */
public class JSONReader extends FilterReader implements ObjectInput{
	private int unread=' ';
	public JSONReader(Reader in) {
		super(in);
	}
	public JSONReader(InputStream in) {
		this(new BufferedReader(new InputStreamReader(in)));
	}
	@Override
	public synchronized int read() throws IOException {
		while(unread!=-1&&Character.isWhitespace(unread)) {
			unread=in.read();
		}
		int ch=unread;
		unread=' ';
		return ch;
	}
	@Override
	public synchronized Object readObject() throws IOException{
		char ch=(char) read();
		Object value=null;
		if(Character.isDigit(ch)||ch=='.'||ch=='+'||ch=='-') {
			value=nextNumber(ch);
		}else if(ch=='{') {
			value=nextObject();
		}else if(ch=='[') {
			value=nextArray();
		}else if(ch=='t') {
			in.skip(3);
			value=true;
		}else if(ch=='f') {
			in.skip(4);
			value=false;
		}else if(ch=='n') {
			value=null;
			in.skip(3);
		}else if(ch=='\''||ch=='\"') {
			value=nextString(ch);
		}
		return value;
	}
	public synchronized String nextString(char end) throws IOException{
		StringBuilder sb=new StringBuilder();
		boolean n=false;
		char ch=(char)in.read();
		while(ch!=end||n) {
			n=false;
			if(ch=='\\') {
				n=true;
			}
			sb.append(ch);
			ch=(char)in.read();
		}
		return sb.toString().translateEscapes();
	}
	public synchronized Number nextNumber(char first) throws IOException {
		int factor=1;
		if(first=='-'){
			factor=-1;
			first=(char)in.read();
		}else if(first=='+') {
			first=(char)in.read();
		}
		if(first=='N') {
			in.skip(2);
			return factor*Double.NaN;
		}else if(first=='I') {
			in.skip(7);
			return factor*Double.POSITIVE_INFINITY;
		}else {
			boolean decimal=false;
			StringBuilder input=new StringBuilder();
			while(Character.isDigit(first)||first=='E'||first=='+'||first=='-'||first=='.') {
				if(first=='E'||first=='.') {
					decimal=true;
				}
				input.append(first);
				first=(char)in.read();
			}
			unread=first;
			if(decimal) {
				return new BigDecimal(input.toString()).multiply(BigDecimal.valueOf(factor));
			}else {
				return new BigInteger(input.toString()).multiply(BigInteger.valueOf(factor));
			}
		}
	}
	public synchronized Object[] nextArray() throws IOException{
		LinkedList<Object> arr=new LinkedList<>();
		char ch=' ';
		while(true) {
			if(ch==',') {
				ch=(char)read();
				continue;
			}
			ch=(char) read();
			if(ch==']') {
				break;
			}
			System.err.println("current character: "+ch);
			Object value=null;
			if(Character.isDigit(ch)||ch=='.'||ch=='+'||ch=='-') {
				value=nextNumber(ch);
			}else if(ch=='{') {
				value=nextObject();
			}else if(ch=='[') {
				value=nextArray();
			}else if(ch=='t') {
				in.skip(3);
				value=true;
			}else if(ch=='f') {
				in.skip(4);
				value=false;
			}else if(ch=='n') {
				value=null;
				in.skip(3);
			}else if(ch=='\''||ch=='\"') {
				value=nextString(ch);
			}
			arr.add(value);
		}
		return arr.toArray();
	}
	public synchronized HashMap<String,Object> nextObject() throws IOException{
		HashMap<String,Object> res=new HashMap<>();
		char ch=(char)read();
		while(ch!='}') {
			if(ch==',') {
				ch=(char)read();
				continue;
			}
			String str=nextString(ch);//key
			read();//:
			ch=(char) read();
			Object value=null;
			if(Character.isDigit(ch)||ch=='.'||ch=='+'||ch=='-') {
				value=nextNumber(ch);
			}else if(ch=='{') {
				value=nextObject();
			}else if(ch=='[') {
				value=nextArray();
			}else if(ch=='t') {
				in.skip(3);
				value=true;
			}else if(ch=='f') {
				in.skip(4);
				value=false;
			}else if(ch=='n') {
				value=null;
				in.skip(3);
			}else if(ch=='\''||ch=='\"') {
				value=nextString(ch);
			}
			res.put(str, value);
			ch=(char) read();
		}
		return res;
	}
	@Deprecated
	@Override
	public void readFully(byte[] b) throws IOException {}
	@Override
	@Deprecated
	public void readFully(byte[] b, int off, int len) throws IOException {}
	@Override
	@Deprecated
	public int skipBytes(int n) throws IOException {
		in.skip(n);
		return 0;
	}
	@Override
	@Deprecated
	public boolean readBoolean() throws IOException {
		return (Boolean)readObject();
	}
	@Override
	@Deprecated
	public byte readByte() throws IOException {
		return ((Number)readObject()).byteValue();
	}
	@Override
	@Deprecated
	public int readUnsignedByte() throws IOException {
		return ((Number)readObject()).intValue();
	}
	@Override
	@Deprecated
	public short readShort() throws IOException {
		return ((Number)readObject()).shortValue();
	}
	@Override
	@Deprecated
	public int readUnsignedShort() throws IOException {
		return ((Number)readObject()).intValue();
	}
	@Override
	@Deprecated
	public char readChar() throws IOException {
		return ((String)readObject()).charAt(0);
	}
	@Override
	@Deprecated
	public int readInt() throws IOException {
		return ((Number)readObject()).intValue();
	}
	@Override
	@Deprecated
	public long readLong() throws IOException {
		return ((Number)readObject()).longValue();
	}
	@Override
	@Deprecated
	public float readFloat() throws IOException {
		return ((Number)readObject()).floatValue();
	}
	@Override
	@Deprecated
	public double readDouble() throws IOException {
		return ((Number)readObject()).doubleValue();
	}
	@Override
	@Deprecated
	public String readLine() throws IOException {
		return (String)readObject();
	}
	@Override
	@Deprecated
	public String readUTF() throws IOException {
		return (String)readObject();
	}
	@Override
	@Deprecated
	public int read(byte[] b) throws IOException {
		return -1;
	}
	@Override
	@Deprecated
	public int read(byte[] b, int off, int len) throws IOException {
		return -1;
	}
	@Override
	@Deprecated
	public int available() throws IOException {
		return -1;
	}
}
