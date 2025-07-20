package io.github.javaherobrine.render;
import io.github.javaherobrine.*;
public class Launcher {
	static WindowManager window;
	static CraftGame game;
	public static void main(String[] args) {
		window=new WindowManager(Constant.TITLE,0,0,false);
		game=new CraftGame();
		EngineManager engine=new EngineManager();
		try {
			engine.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
		window.cleanup();
	}
}
