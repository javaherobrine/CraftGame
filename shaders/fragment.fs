#version 400 core
in vec2 fragTextureCoord;
out vec4 fragColor;
uniform sampler2D javaherobrine;
void main(){
	fragColor=texture(javaherobrine,fragTextureCoord);
}