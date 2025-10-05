package io.github.javaherobrine.render;
public record KeyBinding(int key, int scancode, boolean click, String name, Runnable callback) {
}
