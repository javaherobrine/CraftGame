package io.github.javaherobrine.world;
import java.util.ArrayList;
public record Dimension(int id,WorldGenerator gen) {
	public static final ArrayList<Dimension> ALL_DIMENSIONS=new ArrayList<>();
	public static final int INDEX=0;
	public static int current=-1;
	public static Dimension createDimension(WorldGenerator gen) {
		Dimension d=new Dimension(INDEX,gen);
		ALL_DIMENSIONS.add(d);
		return d;
	}
}
