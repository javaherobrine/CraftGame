package io.github.javaherobrine;
import java.util.*;
public class Time {
	public static Date start() {
		return new Date();
	}
	public static long stop(Date d) {
		Date d2=new Date();
		return d2.getTime()-d.getTime();
	}
}
