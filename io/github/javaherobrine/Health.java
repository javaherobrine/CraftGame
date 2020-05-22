package io.github.javaherobrine;
public class Health {
	public static final byte DEFAULT_MAX_HEALTH=20;
	public static final byte MIN_HEALTH=0;
	public byte health;
	public byte thisMaxHealth=DEFAULT_MAX_HEALTH;
	public Health() {
		throw new Error("You can't use this method to instance this class");
	}
	public Health(byte health) {
		this.health=health;
	}
}
