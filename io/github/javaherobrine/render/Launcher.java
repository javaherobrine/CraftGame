package io.github.javaherobrine.render;
public class Launcher {
	public static void main(String[] args) {
		WindowManager manager=new WindowManager("CraftGame",0,0,false);
		manager.init();
		while(!manager.windowShouldClose()) {
			manager.update();
		}
		manager.cleanup();
	}
}
