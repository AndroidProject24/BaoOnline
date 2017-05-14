package com.toan_itc.baoonline.base.adapter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toan.IT
 * Date: 17/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public class ListDataManagerImpl<T> implements DataManager<T> {

    private List<T> mData;
    private BaseAdapter mBaseAdapter;
    private static final String TAG = ListDataManagerImpl.class.getSimpleName();

    public ListDataManagerImpl(List<T> tList, BaseAdapter adapter) {
        mData = tList;
        mBaseAdapter = adapter;
    }

    private void checkData() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
    }


    @Override
    public void add(T elem) {
        checkData();
        mData.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAt(int location, T elem) {
        checkData();
        mData.add(location, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addItems(List<T> elements) {
        checkData();
        mData.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void addItemsAt(int location, List<T> elements) {
        checkData();
        mData.addAll(location, elements);
        notifyDataSetChanged();
    }

    @Override
    public void replace(T oldElem, T newElem) {
        if (mData != null && mData.contains(oldElem)) {
            replaceAt(mData.indexOf(oldElem), newElem);
        }
    }

    @Override
    public void replaceAt(int index, T elem) {
        if (mData != null && mData.size() > index) {
            mData.set(index, elem);
            notifyDataSetChanged();
        }
    }

    @Override
    public void replaceAll(List<T> elements) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void setDataSource(List<T> elements) {
        mData = elements;
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        if (mData != null && mData.contains(elem)) {
            mData.remove(elem);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeItems(List<T> elements) {
        if (mData != null && mData.containsAll(elements)) {
            mData.removeAll(elements);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeAt(int index) {
        if (mData != null && mData.size() > index) {
            mData.remove(index);
            notifyDataSetChanged();
        }
    }

    @Override
    public T getItem(int position) {
        if (mData != null && mData.size() > position) {
            return mData.get(position);
        }
        Log.e(TAG, "BaseListAdapter call getItem position = " + position + " return null");
        return null;
    }

    @Override
    public final int getDataSize() {
        return mData == null ? 0 : mData.size();
    }


    @Override
    public boolean contains(T elem) {
        return mData != null && mData.contains(elem);
    }

    @Override
    public boolean isEmpty() {
        return mData == null || mData.size() == 0;
    }


    @Override
    public void clear() {
        if (mData != null && !mData.isEmpty()) {
            mData.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public List<T> getItems() {
        return mData;
    }


    public void notifyDataSetChanged() {
        mBaseAdapter.notifyDataSetChanged();
    }

}
