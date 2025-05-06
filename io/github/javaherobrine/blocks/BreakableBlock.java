package io.github.javaherobrine.blocks;
import io.github.javaherobrine.item.*;
public abstract class BreakableBlock extends Block{
	public abstract Item[] onBreak(Tool used);
	public abstract int breakTime(Tool used);
}
