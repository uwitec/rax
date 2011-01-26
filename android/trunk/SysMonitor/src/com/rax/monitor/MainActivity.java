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

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	static final private boolean DEBUG = true;
	static final private String TAG = "RaxLog";
	
    private static final int MENU_SETTING = Menu.FIRST;
    private static final int MENU_ABOUT = Menu.FIRST + 1;
    
    private static final int MSG_UPDATE_DATA = 0;
    
	private GridSurfaceView mGridView;
	private Timer mTimer;
	private LinkedList<Double> mCPUList;
	private LinkedList<Double> mMemoryList;

	public MainActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) Log.v(TAG, "onCreate");

		// FIXME: Load main layout from XML file
		//setContentView(R.layout.main_activity);
		//mGridView = (GridSurfaceView) findViewById(R.id.grid_view);
		//Log.v(TAG, "GridView:" + mGridView);

		RelativeLayout mainLayout = new RelativeLayout(this);
		setContentView(mainLayout);
		mGridView = new GridSurfaceView(this);

		mainLayout.addView(mGridView);
		
		mCPUList = new LinkedList<Double>();
		mMemoryList = new LinkedList<Double>();
		mGridView.setData(mCPUList);
		
		mTimer = new Timer();
		try {
			// TODO: Run as system service
			// TODO: Can change period in settings
			mTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					//mHandler.sendMessage(mHandler.obtainMessage(MSG_UPDATE_DATA));
					double cpu_usage = 100 * JNILib.nativeCpuUsage();
					double memory_usage = 100 * JNILib.nativeMemoryUsage();
					mCPUList.add(cpu_usage);
					if (mCPUList.size() > 100) {
						mCPUList.removeFirst();
					}
					mMemoryList.add(memory_usage);
					if (mMemoryList.size() > 100) {
						mMemoryList.removeFirst();
					}
					Log.i(TAG, String.format("CPU: %.3f%% Memory: %.3f%%", cpu_usage, memory_usage));
					//mGridView.postInvalidate();
					mGridView.onDraw();
				}
			}, 0, 100);
		} catch (Exception ex) {
			Log.e(TAG, "Timer schedule exception:" + ex.toString());
		}
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
		if (DEBUG) Log.v(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy() {
		if (DEBUG) Log.v(TAG, "onDestroy");
		mGridView = null;
		mTimer.cancel();
		mTimer = null;
		mCPUList = null;
		mMemoryList = null;
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		//if (DEBUG) Log.v(TAG, "onCreateOptionsMenu");

		menu.add(0, MENU_SETTING, 0, R.string.menu_setting).setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(0, MENU_ABOUT, 0, R.string.menu_about).setIcon(android.R.drawable.ic_menu_info_details);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		//if (DEBUG) Log.v(TAG, "onPrepareOptionsMenu");
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (DEBUG) Log.v(TAG, "onOptionsItemSelected itemId:" + item.getItemId());
		switch (item.getItemId()) {
		case MENU_SETTING:
			Intent intent = new Intent(this, SettingActivity.class);
			startActivity(intent);
			break;
		case MENU_ABOUT:
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			//if (DEBUG) Log.w(TAG, "Handler::handleMessage msg:" + msg);
			switch (msg.what) {
			case MSG_UPDATE_DATA:
				double cpu_usage = JNILib.nativeCpuUsage();
				double memory_usage = JNILib.nativeMemoryUsage();
				Log.i(TAG, String.format("CPU: %.3f%% Memory: %.3f%%", cpu_usage, memory_usage));
				// TODO: Render the Grid
				break;
			default:
				break;
			}
		}
	};
}
