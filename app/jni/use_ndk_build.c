#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_aserbao_aserbaosandroid_functions_how_1create_1so_useNdkBuild_CallUtils_callSimpleInfo
  (JNIEnv *env, jclass ojb){
     return (*env) -> NewStringUTF(env,"Hello, I'm an info come from use ndk-build");
  };


