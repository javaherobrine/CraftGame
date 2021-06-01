package io.github.javaherobrine.modloader;
public class ModException extends Exception {
	public ModException() {
		super();
	}
	public ModException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public ModException(String message, Throwable cause) {
		super(message, cause);
	}
	public ModException(String message) {
		super(message);
	}
	public ModException(Throwable cause) {
		super(cause);
	}
}
