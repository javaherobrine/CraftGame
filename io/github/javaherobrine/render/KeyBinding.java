package io.github.javaherobrine.render;
import java.util.function.*;
public record KeyBinding(int key, int scancode, boolean click, String name, LongConsumer callback) {
}
