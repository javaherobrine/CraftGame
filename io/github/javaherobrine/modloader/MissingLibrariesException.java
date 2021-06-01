package io.github.javaherobrine.modloader;
public class MissingLibrariesException extends ModException {
	public MissingLibrariesException(String msg) {
		super(msg);
	}
	public static void of(String[] missedLibs) throws MissingLibrariesException{
		StringBuilder sb=new StringBuilder();
		sb.append("Missed Libraries:\n");
		for(String s:missedLibs) {
			sb.append(s);
			sb.append("\n");
		}
		sb.delete(sb.length(), sb.length());
		throw new MissingLibrariesException(sb.toString());
	}
}
