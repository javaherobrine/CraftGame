package io.github.javaherobrine.chemistry;
import io.github.javaherobrine.physics.DecayType;
/**
 * Í¬Î»ËØ
 * @author Java_Heorbrine
 */
public final class Isotope{
	public boolean radioactivity;
	public Elements e;
	public long neutronNumber;
	public DecayType deacyMode;
	private Isotope(Elements e,long elementCode,boolean radioactivity,DecayType mode) {
		this.e=e;
		this.neutronNumber=elementCode;
		this.radioactivity=radioactivity;
		this.deacyMode=mode;
	}
	public static Isotope regditIsotope(Elements e,long neutronNumber,boolean radioactivity,DecayType mode) {
		return new Isotope(e,neutronNumber,radioactivity,mode);
	}
	public static Isotope regditIsotope(Elements e,long neutronNumber) {
		return regditIsotope(e,neutronNumber,false,DecayType.None);
	}
}
