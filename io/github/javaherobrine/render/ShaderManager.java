package io.github.javaherobrine.render;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import java.util.*;
import org.joml.*;
public class ShaderManager {
	private final int programID;
	private int vertexShaderID,fragmentShaderID;
	private final Map<String,Integer>uniforms;
	public ShaderManager() throws Exception{
		this.programID=GL20.glCreateProgram();
		if(programID==0) {
			throw new Exception("Cannot create the shader");
		}
		uniforms=new HashMap<>();
	}
	public void createVertexShader(String shaderCode) throws Exception{
		vertexShaderID=createVertexShader(shaderCode,GL20.GL_VERTEX_SHADER);
	}
	public void createUniform(String uniformName) throws Exception{
		int uniformLocation=GL20.glGetUniformLocation(programID, uniformName);
		if(uniformLocation<0) {
			throw new Exception("Couldn't find uniform "+uniformName);
		}
		uniforms.put(uniformName, uniformLocation);
	}
	public void setUniform(String uniformname,Matrix4f value) {
		MemoryStack stack=MemoryStack.stackPush();
		GL20.glUniformMatrix4fv(uniforms.get(uniformname), false, value.get(stack.mallocFloat(16)));
		stack.close();
	}
	public void setUniform(String uniformname,Vector3f value) {
		GL20.glUniform3f(uniforms.get(uniformname), value.x, value.y, value.z);
	}
	public void setUniform(String uniformname,Vector4f value) {
		GL20.glUniform4f(uniforms.get(uniformname), value.x, value.y, value.z,value.w);
	}
	public void setUniform(String uniformname,int value) {
		GL20.glUniform1i(uniforms.get(uniformname),value);
	}
	public int createVertexShader(String shaderCode,int shaderType) {
		int shader=GL20.glCreateShader(shaderType);
		if(shader==0) {
			throw new IllegalArgumentException("Illegal shader type:"+shaderType);
		}
		GL20.glShaderSource(shader, shaderCode);
		GL20.glCompileShader(shader);
		if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS)==0) {
			throw new IllegalArgumentException("Cannot compile shader,type:"+shaderType+"more info:"+GL20.glGetShaderInfoLog(shader, Integer.MAX_VALUE));
		}
		GL20.glAttachShader(programID, shader);
		return shader;
	}
	public void createFragmentShader(String shaderCode) {
		fragmentShaderID=createVertexShader(shaderCode,GL20.GL_FRAGMENT_SHADER);
	}
	public void link() throws Exception{
		GL20.glLinkProgram(programID);
		if(GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS)==0) {
			throw new Exception("Cannot link shaders,more info:"+GL20.glGetProgramInfoLog(programID, Integer.MAX_VALUE));
		}
		if(vertexShaderID!=0) {
			GL20.glDetachShader(programID, vertexShaderID);
		}
		if(fragmentShaderID!=0) {
			GL20.glDetachShader(programID, fragmentShaderID);
		}
		GL20.glValidateProgram(programID);
		if(GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS)==0) {
			throw new Exception("Cannot to validate shader,more info:"+GL20.glGetProgramInfoLog(programID, Integer.MAX_VALUE));
		}
	}
	public void bind() {
		GL20.glUseProgram(programID);
	}
	public void unbind() {
		GL20.glUseProgram(0);
	}
	public void cleanup() {
		unbind();
		if(programID!=0) {
			GL20.glDeleteProgram(programID);
		}
	}
}
