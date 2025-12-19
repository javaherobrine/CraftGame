#version 450 core
out vec4 fragColor;
in vec2 out_coord;
layout (location = 3) uniform sampler2D tex;
layout (location = 4) uniform sampler2D overlap;
void main(){
	fragColor=mix(texture(tex,out_coord),texture(overlap,out_coord),0.4);
	//fragColor=vec4(out_color,1.0);	
	//fragColor=texture(tex,out_coord);
	//fragColor=vec4(1,1,1,1);
}