package io.github.javaherobrine;
import java.io.*;
import xueli.registry.*;
public class TrieNode {
	private TrieNode[] linkto=new TrieNode[256];
	private Object obj=null;
	public static final TrieNode REGISTRY=new TrieNode();
	public Object access(String str) {
		char[] ch=str.toCharArray();
		TrieNode current=this;
		for(int i=0;i<ch.length;i++) {
			if(current.linkto[ch[i]]==null) {
				return null;
			}
			current=current.linkto[ch[i]];
		}
		return current.obj;
	}
	public void put(String str,Object o) {
		char[] ch=str.toCharArray();
		TrieNode current=this;
		for(int i=0;i<ch.length;i++) {
			if(current.linkto[ch[i]]==null) {
				current.linkto[ch[i]]=new TrieNode();
			}
			current=current.linkto[ch[i]];
		}
		current.obj=o;
	}
	public Object access(InputStream in,int split) throws IOException{
		boolean end=true;
		TrieNode node=this;
		Object res=null;
		int ch=in.read();
		while(ch!=split) {
			node=node.linkto[ch];
			if(node==null) {
				end=false;
				break;
			}
			ch=in.read();
		}
		if(end) {
			res=node.obj;
		}
		while(ch!=split) {
			ch=in.read();
		}
		return res;
	}
	public void put(Identifier i,Object o) {
	    put(i.toString(),o);
	}
	public Object access(Identifier i) {
	    return access(i.toString());
	}
}
