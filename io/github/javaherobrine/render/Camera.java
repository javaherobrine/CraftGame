package io.github.javaherobrine.render;
import org.joml.*;
import org.joml.Math;
public class Camera {
	public float x = 0, y = 0, z = 0, pitch, yaw;
	public static float speed = 1, omega = 1;
	public static byte pitchDirection=1,yawDirection=1;
	/*
line 0, column 0= cos(yaw)
line 0, column 1= 0
line 0, column 2= -sin(yaw)
line 0, column 3= -xcos(yaw)--zsin(yaw)
line 1, column 0= sin(pitch)sin(yaw)
line 1, column 1= cos(pitch)
line 1, column 2= cos(yaw)sin(pitch)
line 1, column 3= -xsin(pitch)sin(yaw)+-ycos(pitch)+-zcos(yaw)sin(pitch)
line 2, column 0= cos(pitch)sin(yaw)
line 2, column 1= sin(pitch)
line 2, column 2= cos(pitch)cos(yaw)
line 2, column 3= -xcos(pitch)sin(yaw)+-ysin(pitch)+-zcos(pitch)cos(yaw)
line 3, column 0= 0
line 3, column 1= 0
line 3, column 2= 0
line 3, column 3= 1
	 */
	public Matrix4f lookAt() {
		float pitchC = (float) Math.cos(pitch);
		float pitchS = (float) Math.sin(pitch);
		float yawC = (float) Math.cos(yaw);
		float yawS = (float) Math.sin(yaw);
		return new Matrix4f(yawC, 0, -yawS, -yawC * x + yawS * z, 
				pitchS * yawS, pitchC, yawC * pitchS,-pitchS * yawS * x - pitchC * y - yawC * pitchS * z,
				pitchC * yawS, pitchS, pitchC * yawC,-x*pitchC*yawS-y*pitchS-z*pitchC*yawC, 
				0, 0, 0, 1);
	}
	public void moveRight(float distance) {
		x += distance * Math.cos(yaw);
		z -= distance * Math.sin(yaw);
	}
	public void moveLeft(float distance) {
		moveRight(-distance);
	}
	public void moveForward(float distance) {
		x += distance * Math.sin(yaw);
		z += distance * Math.cos(yaw);
	}
	public void moveBackward(float distance) {
		moveForward(-distance);
	}
}
