package io.github.javaherobrine.physics;
public class Electric {
	public double V;
	public double A;
	public double ¦¸;
	public double power() {
		return V*A;
	}
	public Electric(double V,double A) {
		this.V=V;
		this.A=A;
		this.¦¸=V/A;
	}
}
