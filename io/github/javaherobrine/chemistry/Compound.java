package io.github.javaherobrine.chemistry;
import java.util.stream.*;
/**
 * Aha,you find the core class of chemistry DLC<br>
 * 多个元素通过搭建的仪器发生反♂应生成的产物，比如KMnO4智取O2得到的MnO2和K2O<br>
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
			//想被化学老师打死就来
			System.out.println("这个化合物不存在，于是游戏崩溃了");
			throw new RuntimeException("你被化学老师打死了");
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
	 * 这将会让你用单质创建化合物（有限制）
	 * @param es 单质
	 * @return 创建的化合物
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
