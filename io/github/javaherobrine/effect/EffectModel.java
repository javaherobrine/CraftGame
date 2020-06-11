package io.github.javaherobrine.effect;
import io.github.javaherobrine.item.*;
public abstract class EffectModel {
	public AbstractName a;//显示状态效果的名称，描述被忽略
	public EffectModel(AbstractName a) {
		this.a=a;
	}
	public abstract void effectFunction();
}
