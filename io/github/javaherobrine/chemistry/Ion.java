package io.github.javaherobrine.chemistry;
/**
 * Рызг
 * @author Java_Herobrine
 */
//Get some electric!
public class Ion {
	public int valence;
	public Element e;
	public Nature[] ionNature;
	public Ion(int valence,Element e) {
		this.valence=valence;
		this.e=e;
		ionNature=e.thisNature;
	}
}
