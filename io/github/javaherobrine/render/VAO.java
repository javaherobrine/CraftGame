package io.github.javaherobrine.render;
import static org.lwjgl.opengl.GL45.*;
public class VAO {// compact data
	public final float[] data;
	public final int size;
	private final int VAO;
	private int elements;
	public VAO(float[] data, int size) {
		this.data = data;
		this.size = size;
		this.VAO = glGenVertexArrays();
		glBindVertexArray(VAO);
		elements = data.length / size;
	}
	public int bindVBO(int mode) {// return: VBO ID
		int VBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		glBufferData(GL_ARRAY_BUFFER, data, mode);
		return VBO;
	}
	public int bindIBO(int[] indices, int mode) {// return: IBO ID
		int IBO = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, mode);
		elements = indices.length;
		return IBO;
	}
	public void attribute(int location, int size) {
		glVertexAttribPointer(location, size, GL_FLOAT, false, this.size << 2, 0);
		glEnableVertexAttribArray(location);
	}
	public void attribute(int location, int size, long offset) {
		glVertexAttribPointer(location, size, GL_FLOAT, false, this.size << 2, offset << 2);
		glEnableVertexAttribArray(location);
	}
	public void apply() {
		glDrawElements(GL_TRIANGLES, elements, GL_UNSIGNED_INT, 0);
	}
	public void apply0() {
		glDrawArrays(GL_TRIANGLES,0,data.length/size);
	}
}
