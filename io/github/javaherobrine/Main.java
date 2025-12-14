package io.github.javaherobrine;
//import io.github.javaherobrine.experimental.*;
//import java.util.*;
import org.lwjgl.opengl.*;
import io.github.javaherobrine.render.*;
public class Main {
	public static void main(String[] args) throws Exception {
//		System.load("D:/java/awa/Debug/libawa.dll");
//		LinkedList<String> l1=new LinkedList<>();
//		LinkedList<String> l2=new LinkedList<>();
//		l1.add("jaro");
//		l2.add("is");
//		l2.add("sb");
//		System.err.println(l1);
//		System.err.println(l2);
//		LinkedListSplicer.splice0(l1, l2);
//		System.err.println(l1);
//		System.err.println(l2);
		System.err.println("Begin");
		Window win = new Window();
		System.err.println("Window");
		Renderer render = new Renderer(win);
		render.run();
	}
}
