package io.github.javaherobrine;
/**
 * ����ģ�鿪������ʵ�ֵĽӿ�<br>
 * ��������ݰ���ʵ��SavePacket
 * @author Java_Herobrine
 */
public interface ModTemplate {
	/**
	 * �˷�������ʵ��<br>
	 * ����ԭ��Java����+Java��̬
	 */
	public abstract void run();
	/**
	 * �˷�����ѡ���Եĸ��ǣ����ڳ�ʼ��mod�����ļ���һЩ����<br>
	 */
	public default void run0() {
		
	}
}
