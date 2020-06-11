package io.github.javaherobrine.item;
import java.awt.event.*;
/**
 * 用于构建物品模型
 * @author Java_Herobrine
 */
public abstract class ItemModel {
	public ItemType type;//物品类型
	public AbstractName name;//显示物品的名称和
	public long maxValue;//最大堆叠数
	public ItemModel(ItemType type,AbstractName name,long maxValue) {
		this.type=type;
		this.name=name;
		this.maxValue=maxValue;
	}
	/**
	 * 如果不需要，请把这个方法实现后留空
	 * 但是两个抽象方法必须有一个，不然物品就废了（只能用于合成别的物品）
	 */
	public abstract void itemEffect(KeyEvent... e);
	public abstract void itemEffect(MouseEvent... e);
}
