package com.toan_itc.baoonline.base.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Toan.IT on 16/6/28.
 * Emmail huynhvantoan.itc@gmail.com
 */
public abstract class ArrayFragmentPagerAdapter<T> extends FragmentPagerAdapter {

    protected List<T> mList;

    public ArrayFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setList(List<T> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    public List<T> getList(){
        return mList;
    }

    public void setList(T[] list){
        setList(Arrays.asList(list));
    }
}
/*


public class TrendingFragmentAdapter extends ArrayFragmentPagerAdapter<Integer> {

    public TrendingFragmentAdapter(FragmentManager fm, Integer[] data) {
        super(fm);
        setList(data);
    }

    @Override
    public Fragment getItem(int position) {
        int lang = mList.get(position);
        return TrendingFragment.newInstance(lang);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int lang = mList.get(position);
        switch (lang) {
            case TrendingApi.LANG_JAVA:
                return "Java";

            case TrendingApi.LANG_OC:
                return "Objective-C";

            case TrendingApi.LANG_SWIFT:
                return "Swift";

            case TrendingApi.LANG_HTML:
                return "HTML";

            case TrendingApi.LANG_PYTHON:
                return "Python";

            case TrendingApi.LANG_BASH:
                return "Shell";

            default:
                AppLog.w("unknown language");
                break;
        }

        return "";
    }
}*/
