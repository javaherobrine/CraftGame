#include"io_github_javaherobrine_GameUtils.h"
JNIEXPORT jlong JNICALL Java_io_github_javaherobrine_GameUtils_address
  (JNIEnv * env, jclass, jbyteArray data){
	jboolean isCopy;
	jlong res=(jlong)env->GetPrimitiveArrayCritical (data,&isCopy);
	return res;
}
JNIEXPORT void JNICALL Java_io_github_javaherobrine_GameUtils_allowGC
  (JNIEnv *env, jclass, jlong addr, jbyteArray data){
	env->ReleasePrimitiveArrayCritical(data,(void*)addr,0);
}
