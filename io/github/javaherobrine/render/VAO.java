package io.github.javaherobrine.render;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
public class VAO {//compact data
    public final float[] data;
    public final int size;
    private final int VAO;
    private int elements;
    public VAO(float[] data,int size) {
		this.data=data;
		this.size=size;
		this.VAO=GL45.glGenVertexArrays();
		GL45.glBindVertexArray(VAO);
		elements=data.length/size;
    }
    public int bindVBO(int mode) {//return: VBO ID
		int VBO=GL45.glGenBuffers();
		GL45.glBindBuffer(GL45.GL_ARRAY_BUFFER, VBO);
		GL45.glBufferData(GL45.GL_ARRAY_BUFFER, data, mode);
		return VBO;
    }
    public int bindIBO(int[] indices, int mode) {//return: IBO ID
		int IBO=GL45.glGenBuffers();
		GL45.glBindBuffer(GL45.GL_ELEMENT_ARRAY_BUFFER, IBO);
		GL45.glBufferData(GL45.GL_ELEMENT_ARRAY_BUFFER, indices, mode);
		elements=indices.length;
		return IBO;
    }
    public void attribute(int location) {
		GL45.glVertexAttribPointer(location, size, GL45.GL_FLOAT, false, size<<2, MemoryUtil.memAlloc(0));
    }
    public void apply() {
		GL45.glEnableVertexAttribArray(0);
		GL45.glDrawElements(GL45.GL_TRIANGLES, elements, GL45.GL_UNSIGNED_INT, 0);
    }
}
