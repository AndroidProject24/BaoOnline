package com.toan_itc.baoonline.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toan_it.library.library.fragment.base.BaseFragment;
import com.toan_itc.baoonline.R;
/**
 * Created by Toan.IT
 * Date: 25/05/2016
 */
public class HomeFragment extends BaseFragment {

    public HomeFragment() {
        // Requires empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    protected int setLayoutResourceID() {
        return 0;
    }

    @Override
    protected void injectData() {

    }

    @Override
    protected void injectViews() {

    }
}
