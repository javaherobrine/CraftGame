package io.github.javaherobrine.effect;
import io.github.javaherobrine.item.*;
public abstract class EffectModel {
	public AbstractName a;//��ʾ״̬Ч�������ƣ�����������
	public EffectModel(AbstractName a) {
		this.a=a;
	}
	public abstract void effectFunction();
}
