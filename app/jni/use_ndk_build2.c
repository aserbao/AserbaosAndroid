#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_aserbao_aserbaosandroid_functions_how_1create_1so_useNdkBuild_CallUtils_callSimpleInfo2
  (JNIEnv *env, jclass ojb){
     return (*env) -> NewStringUTF(env,"I'm an info come from use libuse_ndk_build2.so");
  };


