package io.github.javaherobrine.chemistry;
import io.github.javaherobrine.physics.*;
/**
 * Чт
 * @author Java_Herobrine
 */
//Aha,you can use this element to make a bomb
public class Hydrogen extends Element{
	public static final Isotope H=Isotope.regditIsotope(Elements.H,0);
	public static final Isotope D=Isotope.regditIsotope(Elements.H,1);
	public static final Isotope T=Isotope.regditIsotope(Elements.H,2,true,DecayType.ІТ);
	public static final Nature[] thisNature= {Nature.Reducibility,Nature.Neutral,Nature.Fushion};
}