package io.github.javaherobrine.world;
import java.util.ArrayList;
public class Dimension {
	public static final ArrayList<Dimension> ALL_DIMENSIONS=new ArrayList<>();
	public static final int INDEX=0;
	private int id;
	private Dimension() {}
	public static Dimension createDimension() {
		Dimension d=new Dimension();
		d.id=INDEX;
		ALL_DIMENSIONS.add(d);
		return d;
	}
	public int getID() {
		return id;
	}
}
