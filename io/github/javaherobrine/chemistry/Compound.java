package io.github.javaherobrine.chemistry;
public class Compound {
	public Element[] elements;
	public Ion[] ions=null;
	public Compound(boolean canElectrolysis,Element...elements) {
		this.elements=elements;
		if(canElectrolysis) {
			int length=elements.length;
			int[] temp=new int[length];
			int[] ptrs=new int[length];
			for(int i=0;i<length;i++) {
				temp[i]=0;
				ptrs[i]=0;
			}
			int sum=0;
			boolean flag=true;
			int pointer=length-1;
			while(flag) {
				for(int i=0;i<length;i++) {
					sum+=elements[i].valence[temp[ptrs[i]]];
				}
				if(sum==0) {
					Ion[] ions=new Ion[length];
					for(int i=0;i<length;i++) {
						ions[i]=new Ion(elements[i].valence[temp[ptrs[i]]],elements[i]);
					}
					this.ions=ions;
					return;
				}
				if(pointer==-1) {
					return;
				}
				if(elements[ptrs[pointer]].valence.length==temp[ptrs[pointer]]) {
					pointer--;
					for(int i=pointer;i<length;i++) {
						ptrs[pointer]=0;
					}
				}else {
					ptrs[pointer]++;
					for(int i=length-1;i>pointer;i--) {
						if(ptrs[i]==elements[i].valence.length) {
							ptrs[i]=0;
							ptrs[i-1]++;
						}
					}
				}
				flag=ptrs[0]<elements[0].valence.length;
			}
		}
	}
	public Compound(Element...elements) {
		this(true,elements);
	}
	public Compound(Ion...ions) {
		this.ions=ions;
		this.elements=Ion.parse(ions);
	}
	public static boolean createable(Element...elements) {
		Compound comp=new Compound(true,elements);
		return comp.ions!=null;
	}
}