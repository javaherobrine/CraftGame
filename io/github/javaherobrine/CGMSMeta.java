package io.github.javaherobrine;
@Deprecated
public class CGMSMeta {
	String meta;
	public CGMSMeta(String meta) throws CGMSException{
		if(meta.startsWith("#")) {
			this.meta=meta.split("#")[1];
		}else {
			throw new CGMSException("�ⲻ��Ԫ���");
		}
	}
	public void changeCompiler(CGMSInputStream is) {
		if(meta.startsWith("package")) {
			is.pack=meta.split("\'")[1];
		}
	}
}
