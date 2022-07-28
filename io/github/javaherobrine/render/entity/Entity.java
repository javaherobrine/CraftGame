package io.github.javaherobrine.render.entity;
import org.joml.*;
public class Entity {
	private Model model;
	private Vector3f pos,rotation;
	private float scale;
	public Entity(Model model,Vector3f pos,Vector3f rotation,float scale) {
		this.model=model;
		this.pos=pos;
		this.rotation=rotation;
		this.scale=scale;
	}
	public void incPos(float x,float y,float z) {
		pos.x+=x;
		pos.y+=y;
		pos.z+=z;
	}
	public void setPos(float x,float y,float z) {
		pos.x=x;
		pos.y=y;
		pos.z=z;
	}
	public void incRotation(float x,float y,float z) {
		rotation.x+=x;
		rotation.y+=y;
		rotation.z+=z;
	}
	public void setRotation(float x,float y,float z) {
		rotation.x=x;
		rotation.y=y;
		rotation.z=z;
	}
}
