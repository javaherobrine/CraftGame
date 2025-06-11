#include"io_github_javaherobrine_experimental_LinkedListSplicer.h"
const char* NODE_SIGN="Ljava/util/LinkedList$Node;";
JNIEXPORT void JNICALL Java_io_github_javaherobrine_experimental_LinkedListSplicer_splice
  (JNIEnv* env, jclass, jobject l1, jobject l2,jclass list,jclass node){
	jfieldID headID=env->GetFieldID(list, "first", NODE_SIGN);
	jfieldID tailID=env->GetFieldID(list, "last", NODE_SIGN);
	jfieldID prevID=env->GetFieldID(node, "prev", NODE_SIGN);
	jfieldID nextID=env->GetFieldID(node, "next", NODE_SIGN);
	jfieldID sizeID=env->GetFieldID(list, "size", "I");
	jobject head=env->GetObjectField(l2, headID);
	if(env->IsSameObject(head, 0)){
		return;
	}
	jobject tail=env->GetObjectField(l1, tailID);
	if(env->IsSameObject(tail, 0)){
		env->SetObjectField(l1, headID, head);
		env->SetObjectField(l1, tailID, env->GetObjectField(l2, tailID));
		env->SetIntField(l1, sizeID, env->GetIntField(l2, sizeID));
	}else{
		env->SetObjectField(head,prevID,tail);
		env->SetObjectField(tail,nextID,head);
		env->SetIntField(l1, sizeID, env->GetIntField(l1,sizeID)+env->GetIntField(l2, sizeID));
		env->SetObjectField(l1, tailID, env->GetObjectField(l2, tailID));
	}
	env->SetObjectField(l2, headID, 0);
	env->SetObjectField(l2, tailID, 0);
	env->SetIntField(l2, sizeID, 0);
}
