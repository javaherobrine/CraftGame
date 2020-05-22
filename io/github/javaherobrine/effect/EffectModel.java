package io.github.javaherobrine.effect;
import io.github.javaherobrine.item.*;
public abstract class EffectModel {
	public static final String MODLOADER_VERSION="0.1";
	public AbstractName a;
	public EffectModel(AbstractName a) {
		this.a=a;
	}
	public abstract void effectFunction();
}
