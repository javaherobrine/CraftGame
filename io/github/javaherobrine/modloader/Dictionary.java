package io.github.javaherobrine.modloader;
import io.github.javaherobrine.*;
import xueli.registry.*;
public class Dictionary {
    public static void registry(Identifier identifier) {
		TrieNode.REGISTRY.put(identifier.location(), identifier.location().hashCode());
    }
}
