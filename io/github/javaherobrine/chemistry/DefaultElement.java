package io.github.javaherobrine.chemistry;
public class DefaultElement extends Element{
	public Elements element;
	public int oxidizing;
	public int activity;
	public DefaultElement(int[] valence,Elements element) {
		this.element=element;
		this.valence=valence;
		name=element.toString();
	}
}
