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

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	static final private boolean DEBUG = true;
	static final private String TAG = "RaxLog";
	
    private static final int MENU_SETTING = Menu.FIRST;
    private static final int MENU_ABOUT = Menu.FIRST + 1;
    
    private static final int DIALOG_QUIT_CONFIRM = 1;

	public MainActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) Log.v(TAG, "MainActivity::onCreate");
		
        final TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("list")
                .setContent(new Intent(this, GPadActivity.class)));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("photo list")
                .setContent(new Intent(this, GraphActivity.class)));
        
        // TODO: Add animation for splash screen
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {	
		//if (DEBUG) Log.v(TAG, "MainActivity::onCreateOptionsMenu");

		menu.add(0, MENU_SETTING, 0, R.string.menu_setting).setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(0, MENU_ABOUT, 0, R.string.menu_about).setIcon(android.R.drawable.ic_menu_info_details);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		//if (DEBUG) Log.v(TAG, "MainActivity::onPrepareOptionsMenu");
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (DEBUG) Log.v(TAG, "MainActivity::onOptionsItemSelected itemId:" + item.getItemId());
		switch (item.getItemId()) {
		case MENU_SETTING:
			startActivity(new Intent(this, SettingActivity.class));
			break;
		case MENU_ABOUT:
			startActivity(new Intent(this, AboutActivity.class));
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case DIALOG_QUIT_CONFIRM:
			return new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(R.string.dlg_quit_confirm_title)
					.setMessage(R.string.dlg_quit_confirm_message)
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									finish();
								}
							})
					.setNegativeButton(android.R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
								}
							}).create();
		}
		return null;
	}

	@Override
	public void onBackPressed() {
		if (DEBUG) Log.v(TAG, "MainActivity::onBackPressed");
		showDialog(DIALOG_QUIT_CONFIRM);
	}
}
