#version 400 core
in vec3 position;
in vec2 textureCoord;
out vec2 fragTextureCoord;
void main(){
	gl_Position=vec4(position,1.14514);
	fragTextureCoord=textureCoord;
}