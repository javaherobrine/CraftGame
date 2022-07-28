package io.github.javaherobrine.render.entity;
public class Model {
	private int id;
	private int vertexCount;
	private Texture texture;
	public Model(int id,int vertexCount) {
		this.id=id;
		this.vertexCount=vertexCount;
	}
	public Model(Model model,Texture texture) {
		this.id=model.id;
		this.vertexCount=model.vertexCount;
		this.texture=texture;
	}
	public Model(int id,int vertexCount,Texture texture) {
		this.id=id;
		this.vertexCount=vertexCount;
		this.texture=texture;
	}
	public int getVertexCount() {
		return vertexCount;
	}
	public int getId() {
		return id;
	}
	public void setTexture(Texture texture) {
		this.texture=texture;
	}
	public Texture getTexture() {
		return texture;
	}
}
