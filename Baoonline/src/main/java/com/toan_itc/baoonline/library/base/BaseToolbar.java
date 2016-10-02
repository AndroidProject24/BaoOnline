package com.toan_itc.baoonline.library.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by Toan.IT
 * Date: 14/07/2016
 */

public abstract class BaseToolbar extends BaseActivity {

	private Toolbar toolbar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeToolbar();
	}

	protected boolean hasToolbar() {
		return toolbar != null;
	}

	protected Toolbar getToolbar() {
		return toolbar;
	}

	protected abstract int getToolbarResId();

	protected abstract int getToolbarTitleResId();

	private void initializeToolbar() {
		toolbar = (Toolbar) findViewById(getToolbarResId());
		if (hasToolbar()) {
			setSupportActionBar(toolbar);
			if (getToolbarTitleResId() > 0) {
				setTitle(getString(getToolbarTitleResId()));
			} else {
				setTitle("");
			}
		}
	}
}
