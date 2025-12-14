#version 450 core
layout (location = 0) in vec3 position;
layout (location = 1) in vec2 in_coord;
out vec2 out_coord;
layout (location = 0) uniform mat4 model;
layout (location = 1) uniform mat4 view;
layout (location = 2) uniform mat4 projection;
void main(){
	gl_Position = projection * view * model * vec4(position, 1.0);
	out_coord=in_coord;
}