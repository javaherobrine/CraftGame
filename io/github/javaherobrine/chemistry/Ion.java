package io.github.javaherobrine.chemistry;
public class Ion extends Chemical{
	Element e;
	int valence;
	public Ion(int valence,Element element) {
		this.valence=valence;
		this.e=element;
	}
	public static Element[] parse(Ion[] ions) {
		if(ions==null||ions.length==0) return null;
		int length=ions.length;
		int sum=0;
		Element[] es=new Element[length];
		for(int i=0;i<length;i++) {
			es[i]=ions[i].e;
			sum+=ions[i].valence;
		}
		return es;
	}
}
