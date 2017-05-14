package com.toan_itc.baoonline.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Toan.IT
 * Created by vantoan on 3/20/17.
 * Email: Huynhvantoan.itc@gmail.com
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
