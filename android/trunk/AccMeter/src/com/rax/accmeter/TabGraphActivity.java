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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TabGraphActivity extends Activity implements OnClickListener {

	static final private boolean DEBUG = true;
	static final private String TAG = "RaxLog";
	
	public TabGraphActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) Log.v(TAG, "GraphActivity::onCreate");
		
		setContentView(R.layout.graph_activity);
		
		((Button) findViewById(R.id.btn_start_graph)).setOnClickListener(this);
		
	}
	
	@Override
	public void onBackPressed() {
		getParent().onBackPressed();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start_graph:
			Intent intent = new Intent(this, GraphActivity.class);
			startActivity(intent);
			break;
		}
	}
}
