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

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This class provides a basic demonstration of how to write an Android
 * activity. Inside of its window, it places a single view: an EditText that
 * displays and edits some internal text.
 */
public class MainActivity extends Activity {

	static final private boolean DEBUG = true;
	static final private String TAG = "MainActivity";

	public MainActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (DEBUG) Log.v(TAG, "onCreate");

		setContentView(R.layout.main_activity);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (DEBUG) Log.v(TAG, "onStart");
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (DEBUG) Log.v(TAG, "onPause");
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (DEBUG) Log.v(TAG, "onResume");
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (DEBUG) Log.v(TAG, "onResume");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		if (DEBUG) Log.v(TAG, "onCreateOptionsMenu");

		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.layout.option_menu, menu);
		
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);

		if (DEBUG) Log.v(TAG, "onPrepareOptionsMenu");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (DEBUG) Log.v(TAG, "onOptionsItemSelected itemId:" + item.getItemId());
		switch (item.getItemId()) {
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
