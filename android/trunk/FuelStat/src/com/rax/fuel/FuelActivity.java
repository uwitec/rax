package com.rax.fuel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FuelActivity extends Activity {
	
	private static final String TAG = "FuelActivity";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i(TAG, "onCreate");

		setContentView(R.layout.main);
	}
}
