package io.github.javaherobrine.chemistry;
public abstract class Ionization {
	Compound electrolyte;
	public Ionization(Compound electrolyte) {
		this.electrolyte=electrolyte;
	}
	public abstract Ions[] electrolysis(DefaultCompound comp);
}
