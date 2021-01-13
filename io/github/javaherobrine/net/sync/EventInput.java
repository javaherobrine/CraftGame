package io.github.javaherobrine.net.sync;
import io.github.javaherobrine.net.event.*;
import java.io.*;
public interface EventInput {
	EventObject readObject() throws IOException;
}
