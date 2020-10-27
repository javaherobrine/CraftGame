package io.github.javaherobrine.chemistry;
public class Ion {
	DefaultElement e;
	int valence;
	public Ion(int valence,DefaultElement element) {
		this.valence=valence;
		this.e=element;
	}
	public static DefaultElement[] parse(Ion[] ions) {
		int length=ions.length;
		int sum=0;
		DefaultElement[] es=new DefaultElement[length];
		for(int i=0;i<length;i++) {
			es[i]=ions[i].e;
			sum+=ions[i].valence;
		}
		if(sum!=0) {
			return null;
		}
		return es;
	}
}
