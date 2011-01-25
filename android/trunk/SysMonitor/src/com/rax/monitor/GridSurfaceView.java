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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GridSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

	private static final boolean DEBUG = true;
	private static final String TAG = "RaxLog";
	
	private SurfaceHolder mHolder;
	private LinkedList<Double> mList;
	private int mWidth;
	private int mHeight;
	private Paint mPaint;
	private boolean mThreadRunning;
	//private Thread mThread;

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
	
	public void setData(LinkedList<Double> list) {
		if (DEBUG) Log.d(TAG, "GridSurfaceView::setData+ list:" + list);
		mList = list;
		if (DEBUG) Log.d(TAG, "GridSurfaceView::setData-");
	}

	public void onDraw() {
		//if (DEBUG) Log.d(TAG, "GridSurfaceView::onDraw+");
		Canvas canvas = null;
		try {
			canvas = mHolder.lockCanvas();
			canvas.drawColor(Color.BLACK);
			int i = 1;
			float vStep = (float)mWidth / 101f;
			float hStep = (float)mHeight / 100f;
			//if (DEBUG) Log.d(TAG, String.format("GridSurfaceView::onDraw mWidth:%d mHeight:%d vStep:%f hStep:%f", mWidth, mHeight, vStep, hStep));
			for (Double f : mList) {
				float startX = vStep * i;
				float startY = 5;
				float stopX = vStep * i;
				float stopY = (float)(hStep * f * 100 + 5);
				//if (DEBUG) Log.d(TAG, String.format("GridSurfaceView::onDraw startX:%f startY:%f stopX:%f stopY:%f", startX, startY, stopX, stopY));
				canvas.drawLine(startX, startY, stopX, stopY, mPaint);
				i++;
			}
		} catch (Exception ex) {
			Log.e(TAG, "GridSurfaceView::onDraw exception:" + ex.toString());
		} finally {
			if (canvas != null) {
				mHolder.unlockCanvasAndPost(canvas);
			}
		}
		//if (DEBUG) Log.d(TAG, "GridSurfaceView::onDraw-");
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceCreated+");
		
		mPaint = new Paint();
		mPaint.setColor(Color.GREEN);
		
		mThreadRunning = true;
		//mThread = new Thread(this);
		//mThread.start();
		
		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceCreated-");
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if (DEBUG) Log.v(TAG, String.format("GridSurfaceView::surfaceChanged+ fmt:%d w:%d h:%d", format, width, height));
		mWidth = width;
		mHeight = height;
		if (DEBUG) Log.v(TAG, "GridSurfaceView::surfaceChanged-");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceDestroyed+");
		try {
			mPaint = null;
			mThreadRunning = false;
			//mThread.join();
		} catch(Exception ex) {
			Log.e(TAG, "GridSurfaceView::surfaceDestroyed thread exception:" + ex.toString());
		}
		if (DEBUG) Log.d(TAG, "GridSurfaceView::surfaceDestroyed-");
	}

	@Override
	public void run() {
		while (mThreadRunning) {
			Canvas canvas = mHolder.lockCanvas();
			
			
			mHolder.unlockCanvasAndPost(canvas);
		}
	}
}
