package io.github.javaherobrine.render;
import static org.lwjgl.opengl.GL45.*;
import org.joml.*;
import java.nio.*;
import org.lwjgl.system.*;
public class Shader {
    public final int program,vertex,fragment;
    public Shader(String vertexCode,String fragmentCode) {
		System.err.println("vs: "+vertexCode);
		System.err.println("fs: "+fragmentCode);
		program=glCreateProgram();
		vertex=glCreateShader(GL_VERTEX_SHADER);
		fragment=glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragment, fragmentCode);
		glShaderSource(vertex, vertexCode);
		glCompileShader(vertex);
		System.err.println(glGetShaderInfoLog(vertex));
		glCompileShader(fragment);
		System.err.println(glGetShaderInfoLog(fragment));
		glAttachShader(program,vertex);
		glAttachShader(program, fragment);
		glLinkProgram(program);
		System.err.println(glGetProgramInfoLog(program));
		glDeleteShader(fragment);
		glDeleteShader(vertex);
    }
    public void exec() {
		glUseProgram(program);
    }
    public int uniform(String name) {
		return glGetUniformLocation(program, name);
    }
    public void uniform(String name,Matrix4f mat) {
		//System.out.println(mat);
		uniform(uniform(name),mat);
    }
    public void uniform(int location,Matrix4f mat) {
		//System.out.println(mat);
		FloatBuffer buffer=MemoryUtil.memAllocFloat(16);
		glUniformMatrix4fv(location, false, mat.get(buffer));
		MemoryUtil.memFree(buffer);
    }
}
