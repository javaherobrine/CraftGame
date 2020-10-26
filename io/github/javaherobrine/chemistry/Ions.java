package io.github.javaherobrine.chemistry;
public class Ions {
	public DefaultCompound comp;
	public Ion ion;
	public Ions(DefaultCompound comp,Ion ion) {
		this.ion=ion;
		this.comp=comp;
	}
	public Ions(DefaultCompound comp,Element element,int valence) {
		this(comp,new Ion(valence,element));
	}
}
