package io.github.javaherobrine.chemistry;
import io.github.javaherobrine.utils.*;
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
	public Ions(Ion[] ions) {
		Element[] elements=Ion.parse(ions);
		Element[] newElements;
		for(int i=0;i<elements.length;i++) {
			newElements=ArrayUtils.except(elements, i, i);
			
		}
	}
}
