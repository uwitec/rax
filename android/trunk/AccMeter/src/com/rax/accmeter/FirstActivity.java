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

package com.rax.accmeter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class FirstActivity extends Activity {

	static final private boolean DEBUG = true;
	static final private String TAG = "RaxLog";
	
	public FirstActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) Log.v(TAG, "FirstActivity::onCreate");
		
		setContentView(R.layout.first_activity);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (DEBUG) Log.v(TAG, "FirstActivity::onStart");
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (DEBUG) Log.v(TAG, "Runnable::run timeout");
				Intent intent = new Intent(FirstActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 1800);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (DEBUG) Log.v(TAG, "FirstActivity::onStop");
	}
	
	@Override
	protected void onDestroy() {
		if (DEBUG) Log.v(TAG, "FirstActivity::onDestroy");
		super.onDestroy();
	}
}
