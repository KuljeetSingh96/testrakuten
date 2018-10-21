#include <jni.h>
#include <string>

static int count = 0;

extern "C"
JNIEXPORT jint JNICALL
Java_com_testrakuten_MainActivity_incrementCount(JNIEnv *env,jobject instance) {
    count+=1;
    return count;

}
