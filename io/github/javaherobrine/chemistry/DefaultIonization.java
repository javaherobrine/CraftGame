package io.github.javaherobrine.chemistry;
public class DefaultIonization extends Ionization{
	public DefaultIonization(DefaultCompound electrolyte) {
		super(electrolyte);
	}
	@Override
	public Ions[] electrolysis(DefaultCompound comp) {
		return null;
	}
}
