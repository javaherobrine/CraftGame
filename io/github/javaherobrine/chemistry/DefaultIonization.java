package io.github.javaherobrine.chemistry;
public class DefaultIonization extends Ionization{
	public DefaultIonization(DefaultCompound electrolyte) {
		super(electrolyte);
	}
	@Override
	@ReactionMethod
	public Ions[] electrolysis(DefaultCompound comp) {
		Ion[] i=comp.ions;
		Ion[] i0=((DefaultCompound)(electrolyte)).ions;
		return null;
	}
}
