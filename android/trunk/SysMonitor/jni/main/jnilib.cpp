/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <stdio.h>
#include <string.h>
#include <jni.h>

#include "logging.h"
#include "cpu.h"
#include "memory.h"

static JavaVM *		gVM	= NULL;
static const char *	gClassPathName = "com/rax/monitor/JNILib";

extern "C" jint
nativeCpuUsage(JNIEnv* env, jobject thiz)
{
    //LOGD("nativeCpuUsage+");
    double	usage = get_cpu_usage();    
    //LOGD("nativeCpuUsage- usage:%.2f", usage);
    return usage;
}

extern "C" jdouble
nativeMemoryUsage(JNIEnv* env, jobject thiz)
{
    //LOGD("nativeMemoryUsage+");
    double	usage = get_memory_usage();
    //LOGD("nativeMemoryUsage- usage:%.2f", usage);
    return usage;
}

static JNINativeMethod	gMethods[] = {
    { "nativeCpuUsage", 
	"()D", 
	(void *)nativeCpuUsage },
    { "nativeMemoryUsage", 
	"()D", 
	(void *)nativeMemoryUsage },
};

extern "C" jint
JNI_OnLoad(JavaVM * vm, void * reserved)
{
    JNIEnv *	env		= NULL;
    jint	nResult		= JNI_ERR;
    jint	nMethods	= sizeof(gMethods) / sizeof(gMethods[0]);
    jclass	clazz;
    //LOGV("JNI_OnLoad+");

    if (JNI_OK == vm->GetEnv((void**) &env, JNI_VERSION_1_4)) {
	clazz = env->FindClass(gClassPathName);
	if (NULL != clazz) {
	    if (env->RegisterNatives(clazz, gMethods, nMethods) == 0) {
		nResult	= JNI_VERSION_1_4;
		gVM	= vm;
	    } else {
		LOGE("JNI_OnLoad RegisterNative failed");
	    }
	} else {
	    LOGE("JNI_OnLoad FindClass failed");
	}
    } else {
	LOGE("JNI_OnLoad GetEnv failed");
    }

    //LOGV("JNI_OnLoad-");
    return nResult;
}

// vim:ts=8:sw=4:
