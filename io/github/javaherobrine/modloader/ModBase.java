package io.github.javaherobrine.modloader;
public interface ModBase {
	void load();
	default void unload() {}
}
