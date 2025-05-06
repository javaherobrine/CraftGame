package io.github.javaherobrine;
public class TrieNode {
	private TrieNode[] linkto=new TrieNode[256];
	private Object obj=null;
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
}
