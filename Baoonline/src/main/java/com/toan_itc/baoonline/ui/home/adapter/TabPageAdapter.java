package com.toan_itc.baoonline.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toan.IT
 * Date: 23/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public class TabPageAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments= new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();
    public TabPageAdapter(FragmentManager fm, List<Fragment> fragments,List<String> fragmentTitleList) {
        super(fm);
        this.fragments = fragments;
        this.mFragmentTitleList=fragmentTitleList;
    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }
}
