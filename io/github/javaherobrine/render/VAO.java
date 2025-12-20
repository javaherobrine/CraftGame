package io.github.javaherobrine.render;
import static org.lwjgl.opengl.GL45.*;
import org.joml.*;
import io.github.javaherobrine.*;
import java.nio.*;
import org.lwjgl.system.*;
public class VAO {// compact data
	public final float[] data;
	public final int size;
	private final int VAO;
	private int VBO,IBO;
	private int elements,datatype;
	public VAO(float[] data, int size) {
		this.data = data;
		this.size = size;
		this.VAO = glGenVertexArrays();
		glBindVertexArray(VAO);
		elements = data.length / size;
	}
	public int bindVBO(int mode) {// return: VBO ID
		VBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		glBufferData(GL_ARRAY_BUFFER, data, mode);
		return VBO;
	}
	public int bindIBO(int[] indices, int mode) {// return: IBO ID
		IBO = glGenBuffers();
		datatype=GL_UNSIGNED_INT;
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, mode);
		elements = indices.length;
		return IBO;
	}
	public int bindIBO(short[] indices, int mode) {// return: IBO ID
		IBO = glGenBuffers();
		datatype=GL_UNSIGNED_SHORT;
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, mode);
		elements = indices.length;
		return IBO;
	}
	public int bindIBO(byte[] indices, int mode) {// return: IBO ID
		IBO = glGenBuffers();
		datatype=GL_UNSIGNED_BYTE;
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
		long addr=GameUtils.address(indices);
		nglBufferData(GL_ELEMENT_ARRAY_BUFFER, indices.length,addr, mode);
		GameUtils.allowGC(addr, indices);
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
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		if(elements!=-1) {
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
			glDrawElements(GL_TRIANGLES, elements, datatype, 0);
		}else
			apply0();
	}
	private void apply0() {
		glDrawArrays(GL_TRIANGLES,0,data.length/size);
	}
	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
	}
	/**
	 * Create a VAO of a block with 6 same planes
	 */
	public static VAO blockVAO(Vector2f LU,Vector2f LD,Vector2f RU,Vector2f RD,int mode) {
		VAO vao=new VAO(new float[] {
                0, 0, 0,  LD.x, LD.y,
                1, 0, 0,  RD.x, RD.y,
                1, 1, 0,  RU.x, RU.y,
                0, 1, 0,  LU.x, LU.y,
                0, 0, 1,  LD.x, LD.y,
                1, 0, 1,  RD.x, RD.y,
                1, 1, 1,  RU.x, RU.y,
                0, 1, 1,  LU.x, LU.y,
                0, 1, 1,  RD.x, RD.y,
                0, 1, 0,  RU.x, RU.y,
                0, 0, 0,  LU.x, LU.y,
                1, 1, 1,  RD.x, RD.y,
                1, 0, 0,  LU.x, LU.y,
                1, 0, 1,  LD.x, LD.y,
                1, 0, 0,  RU.x, RU.y,
                0, 1, 1,  LD.x, LD.y,
   },5);
		vao.bindIBO(new byte[] {
				0,1,2,
				2,3,0,
				4,5,6,
				6,7,4,
				8,9,10,
				10,4,8,
				11,2,12,
				12,13,11,
				10,14,5,
				5,4,10,
				3,2,11,
				11,15,3,
		}, mode);
		return vao;
	}
	public static VAO skyVAO() {
		VAO vao=new VAO(new float[] {
			    -1.0f,  1.0f, -1.0f,
			    -1.0f, -1.0f, -1.0f,
			     1.0f, -1.0f, -1.0f,
			     1.0f,  1.0f, -1.0f,
			    -1.0f, -1.0f,  1.0f,
			    -1.0f,  1.0f,  1.0f,
			     1.0f, -1.0f,  1.0f,
			     1.0f,  1.0f,  1.0f,
		},3);
		vao.bindIBO(new byte[] {
				0,1,2,
				2,3,0,
				4,1,0,
				0,5,4,
				2,6,7,
				7,3,2,
				4,5,7,
				7,6,4,
				0,3,7,
				7,5,0,
				1,4,2,
				2,4,6,
		},GL_STATIC_DRAW);
		return vao;
	} 
}
