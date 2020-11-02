package io.github.javaherobrine.chemistry;
public abstract class Ionization implements Reaction{
	Compound electrolyte;
	public Ionization(Compound electrolyte) {
		this.electrolyte=electrolyte;
	}
	public abstract Ions[] electrolysis(DefaultCompound comp);
}
