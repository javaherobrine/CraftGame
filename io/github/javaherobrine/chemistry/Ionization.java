package io.github.javaherobrine.chemistry;
public abstract class Ionization {
	DefaultCompound electrolyte;
	public Ionization(DefaultCompound electrolyte) {
		this.electrolyte=electrolyte;
	}
	public abstract Ions[] electrolysis(DefaultCompound comp);
}
