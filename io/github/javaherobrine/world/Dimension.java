package io.github.javaherobrine.world;
import java.util.ArrayList;
public record Dimension(int id,WorldGenerator gen) {
	public static final ArrayList<Dimension> ALL_DIMENSIONS=new ArrayList<>();
	public static int current=-1;
	public static Dimension createDimension(WorldGenerator gen) {
		Dimension d=new Dimension((current=current+1),gen);
		ALL_DIMENSIONS.add(d);
		return d;
	}
}
