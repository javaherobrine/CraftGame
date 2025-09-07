package io.github.javaherobrine.math;
import org.joml.Math;
import org.joml.*;
public class RotationHelper {
    public static Matrix3f rotate(Vector3f axis, float theta) {
		float C=Math.cos(theta);
		float S=Math.sin(theta);
		float NC=1-C;
		axis.normalize();
		float XY=axis.x*axis.y*NC;
		float XZ=axis.x*axis.z*NC;
		float YZ=axis.y*axis.z*NC;
		return new Matrix3f(
			C+axis.x*axis.x*NC, XY-axis.z*S,XZ+axis.y*S,
			XY+axis.z*S,C+axis.y*axis.y*NC,YZ-axis.x*S,
			XZ-axis.y*S,YZ+axis.x*S,C+axis.z*axis.z*NC);
    }
    public static Matrix3f rotate(Vector4f axis) {
		float theta=axis.w;
		axis.w=0;
		float C=Math.cos(theta);
		float S=Math.sin(theta);
		float NC=1-C;
		axis.normalize();
		float XY=axis.x*axis.y*NC;
		float XZ=axis.x*axis.z*NC;
		float YZ=axis.y*axis.z*NC;
		return new Matrix3f(
			C+axis.x*axis.x*NC, XY-axis.z*S,XZ+axis.y*S,
			XY+axis.z*S,C+axis.y*axis.y*NC,YZ-axis.x*S,
			XZ-axis.y*S,YZ+axis.x*S,C+axis.z*axis.z*NC);
    }
}
