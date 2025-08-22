#version 400 core
out vec4 fragColor;
in vec3 out_color;
in vec2 out_coord;
uniform sampler2D tex;
void main(){
	fragColor=texture(tex,out_coord)*vec4(out_color,1.0);
	//fragColor=vec4(out_color,1.0);	
	//fragColor=texture(tex,out_coord);
	//fragColor=vec4(1,1,1,1);
}