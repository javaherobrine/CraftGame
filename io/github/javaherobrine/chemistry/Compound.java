package io.github.javaherobrine.chemistry;
import java.util.stream.*;
/**
 * Aha,you find the core class of chemistry DLC<br>
 * ���Ԫ��ͨ�����������������Ӧ���ɵĲ������KMnO4��ȡO2�õ���MnO2��K2O<br>
 * @author Java_Herobrine
 */
public class Compound {
	public Anion[] a;
	public Cation[] c;
	public Nature[] thisNature;
	private static int val=0;
	private static byte[][] temp=null;
	private static int temp2=0;
	public Compound(Anion[] a,Cation[] c) {
		int an=0;
		int ca=0;
		for(int i=0;i<a.length;i++) {
			an+=a[i].valence;
		}
		for(int i=0;i<c.length;i++) {
			ca+=c[i].valence;
		}
		if(an==ca) {
			this.a=a;
			this.c=c;
		}else {
			//�뱻��ѧ��ʦ��������
			System.out.println("��������ﲻ���ڣ�������Ϸ������");
			throw new RuntimeException("�㱻��ѧ��ʦ������");
		}
	}
	public static boolean canCreate(Anion[] a,Cation[] c) {
		int an=0;
		int ca=0;
		for(int i=0;i<a.length;i++) {
			an+=a[i].valence;
		}
		for(int i=0;i<c.length;i++) {
			ca+=c[i].valence;
		}
		return an==ca;
	}
	/**
	 * �⽫�������õ��ʴ�������������ƣ�
	 * @param es ����
	 * @return �����Ļ�����
	 */
	public static Compound createCompoundByElements(Element...es) {
		temp=new byte[es.length][];
		Stream<Element>es2=Stream.of(es);
		es2.forEach(e->streamScript(e));
		int s=0;
		int s2=0;
		boolean flag;
		for(int i=0;i<temp.length;i++) {
			for(;s<temp.length;s++) {
				for(;s2<temp[i].length;s2++) {
					
				}
			}
		}
		return createCompoundByElements(es);
	}
	private static void streamScript(Element e) {
		temp[temp2]=e.valences;
		temp2++;
	}
}
