package io.github.javaherobrine.effect;
import java.util.*;
public class Effect {
	public EffectModel thisEffect;
	public static ArrayList<EffectModel> effects;
	public static void regedit(EffectModel e) {
		effects.add(e);
	}
	public Effect(EffectModel e) {
		this.thisEffect=e;
	}
}
