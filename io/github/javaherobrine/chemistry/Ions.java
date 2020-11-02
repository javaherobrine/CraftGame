package io.github.javaherobrine.chemistry;
public class Ions extends Chemical{
	public Compound comp;
	public Ion ion;
	public Ions(Compound comp,Ion ion) {
		this.ion=ion;
		this.comp=comp;
	}
	public Ions(Compound comp,Element element,int valence) {
		this(comp,new Ion(valence,element));
	}
}
