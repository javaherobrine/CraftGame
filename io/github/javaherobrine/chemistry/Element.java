package io.github.javaherobrine.chemistry;
public abstract class Element extends Chemical{
	public int[] valence;
	public String name;
	@Override
	public String toString() {
		return name;
	}
}
