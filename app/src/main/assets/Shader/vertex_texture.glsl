uniform mat4 textureTransform;
attribute vec2 inputTextureCoordinate;
attribute vec4 position;            //NDK坐标点
varying   vec2 textureCoordinate; //纹理坐标点变换后输出

 void main() {
     gl_Position = position;
     textureCoordinate = inputTextureCoordinate;
 }