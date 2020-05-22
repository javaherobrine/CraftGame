package io.github.javaherobrine;
/**
 * 这是模组开发必须实现的接口<br>
 * 如果是数据包请实现SavePacket
 * @author Java_Herobrine
 */
public interface ModTemplate {
	/**
	 * 此方法必须实现<br>
	 * 加载原理：Java反射+Java多态
	 */
	public abstract void run();
	/**
	 * 此方法可选择性的覆盖，用于初始化mod的类文件的一些数据<br>
	 */
	public default void run0() {
		
	}
}
