package io.github.javaherobrine.render;
import java.util.*;
import org.lwjgl.opengl.*;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.*;
import java.nio.*;
import io.github.javaherobrine.render.entity.*;
public class ObjectLoader {
	private ArrayList<Integer> vaos=new ArrayList<>();
	private ArrayList<Integer> vbos=new ArrayList<>();
	private ArrayList<Integer> textures=new ArrayList<>();
	public Model loadModel(float[]vertices,float[] textureCoords,int[] indices) {
		int id=createVAO();
		storeIndicesBuffer(indices);
		storeDataInAttributeList(0,3,vertices);
		storeDataInAttributeList(1,2,textureCoords);
		unbind();
		return new Model(id,vertices.length/3);
	}
	public int loadTexture(String filename) throws Exception{
		int width,height;
		ByteBuffer buffer;
		MemoryStack stack=MemoryStack.stackPush();
		IntBuffer w=stack.mallocInt(1);
		IntBuffer h=stack.mallocInt(1);
		IntBuffer c=stack.mallocInt(1);
		buffer=STBImage.stbi_load(filename, w, h, c, 4);
		if(buffer==null) {
			stack.close();
			throw new Exception("Image file "+filename+" not loaded "+STBImage.stbi_failure_reason());
		}
		width=w.get();
		height=h.get();
		int id=GL11.glGenTextures();
		textures.add(id);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT,1);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		STBImage.stbi_image_free(buffer);
		stack.close();
		return id;
	}
	private int createVAO() {
		int id=GL30.glGenVertexArrays();
		vaos.add(id);
		GL30.glBindVertexArray(id);
		return id;
	}
	private void storeIndicesBuffer(int[]indices) {
		int vbo=GL15.glGenBuffers();
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
		IntBuffer buffer=GUIUtils.storeDataInIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	private void storeDataInAttributeList(int attributeNo,int vertexCount,float[]data) {
		int vbo=GL15.glGenBuffers();
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vbo);
		FloatBuffer buffer=GUIUtils.storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNo, vertexCount, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	private void unbind() {
		GL30.glBindVertexArray(0);
	}
	public void cleanup() {
		for(int i:vaos) {
			GL30.glDeleteVertexArrays(i);
		}
		for(int i:vbos) {
			GL30.glDeleteBuffers(i);
		}
		for(int i:textures) {
			GL30.glDeleteTextures(i);
		}
	}
}
