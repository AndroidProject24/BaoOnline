package com.toan_itc.baoonline.ui.setting;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.base.BaseFragment;
import com.toan_itc.baoonline.ui.home.activity.MainActivity;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.theme.MaterialTheme;

/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class SettingFragment extends BaseFragment implements DialogInterface.OnClickListener{
	private static final String KEY_ARG_CURRENT_THEME = "KEY_ARG_CURRENT_THEME";

	private MaterialTheme mCurrentTheme;
	private int mCurrentSelectedThemeIndex;
    public SettingFragment() {
        setRetainInstance(true);
    }

    public static SettingFragment newInstance(MaterialTheme currentTheme) {
	    SettingFragment fragment = new SettingFragment();
        Bundle args = fragment.getArguments();
        if (args == null) {
            args = new Bundle();
        }

        args.putSerializable(KEY_ARG_CURRENT_THEME, currentTheme);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.setting_fragment;
    }

    @Override
    protected void injectDependencies() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViews() {

    }

	@Override
	public void onClick(DialogInterface dialogInterface, int i) {
		mCurrentSelectedThemeIndex = i;
		MaterialTheme newTheme = MaterialTheme.getThemeList().get(mCurrentSelectedThemeIndex);

		// If the theme is new, set it and start a new activity
		if (!mCurrentTheme.equals(newTheme)) {
			Activity parentActivity = getActivity();
			Intent intent = MainActivity.newInstanceTheme(parentActivity, newTheme);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			parentActivity.startActivity(intent);
		}
	}
}
