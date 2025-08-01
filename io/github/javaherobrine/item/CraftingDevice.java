package io.github.javaherobrine.item;
import io.github.javaherobrine.*;
@Modification("Implement this interface and register formulas")
public interface CraftingDevice {
    Item inputSlot(int index);
}
