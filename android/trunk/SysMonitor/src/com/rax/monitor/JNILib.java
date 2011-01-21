/*
 * Copyright (C) 2007 The Android Open Source Project
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
 */

package com.rax.monitor;

import android.util.Log;

public class JNILib {

	private static final String TAG = "RAX_LOG";

	public static native double nativeCpuUsage();

	public static native double nativeMemoryUsage();

	static {
		try {
			System.loadLibrary("jnilib");
		} catch (UnsatisfiedLinkError ex) {
			Log.e(TAG, "static::" + ex.toString());
		}
	}

}
