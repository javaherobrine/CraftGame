package io.github.javaherobrine.item;
import java.awt.event.*;
/**
 * ���ڹ�����Ʒģ��
 * @author Java_Herobrine
 */
public abstract class ItemModel {
	public ItemType type;//��Ʒ����
	public AbstractName name;//��ʾ��Ʒ�����ƺ�
	public long maxValue;//���ѵ���
	public ItemModel(ItemType type,AbstractName name,long maxValue) {
		this.type=type;
		this.name=name;
		this.maxValue=maxValue;
	}
	/**
	 * �������Ҫ������������ʵ�ֺ�����
	 * �����������󷽷�������һ������Ȼ��Ʒ�ͷ��ˣ�ֻ�����ںϳɱ����Ʒ��
	 */
	public abstract void itemEffect(KeyEvent... e);
	public abstract void itemEffect(MouseEvent... e);
}
