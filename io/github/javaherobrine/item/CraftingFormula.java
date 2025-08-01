package io.github.javaherobrine.item;
import io.github.javaherobrine.*;
import java.util.*;
@Modification("Register your formula here")
public class CraftingFormula {
    public static final HashMap<Class<? extends CraftingDevice>,ArrayList<CraftingFormula>> FORMULAS=new HashMap<>();
    public boolean ordered;
    public CraftingSlot[] requirements;
    public CraftingSlot[] products;
    public static class CraftingSlot{
		public boolean allowAlternatives;
		public Item value;
    }
}
