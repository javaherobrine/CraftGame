#version 450 core
layout (location = 0) in vec3 position;
layout (location = 1) in vec3 in_color;
layout (location = 2) in vec2 in_coord;
out vec2 out_coord;
out vec3 out_color;
layout (location = 0) uniform mat4 transform;
void main(){
	gl_Position = transform * vec4(position, 1.0);
	//gl_Position=vec4(position, 1.0);
	out_color=in_color;
	out_coord=in_coord;
}