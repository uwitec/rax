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

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GridSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private static final boolean DEBUG = true;
	private static final String TAG = "RaxLog";
	
	private SurfaceHolder mHolder;


	public GridSurfaceView(Context context) {
		super(context);
		mHolder = getHolder();
		mHolder.addCallback(this);
	}
	
	public GridSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public GridSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceCreated+");

		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceCreated-");
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if (DEBUG) Log.v(TAG, String.format("GridSurfaceView::surfaceChanged+ fmt:%d w:%d h:%d", format, width, height));	
		if (DEBUG) Log.v(TAG, "GridSurfaceView::surfaceChanged-");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceDestroyed+");

		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceDestroyed-");
	}

}
