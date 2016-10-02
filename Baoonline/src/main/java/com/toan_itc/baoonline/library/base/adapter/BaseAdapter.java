package com.toan_itc.baoonline.library.base.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 17/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements DataManager<T> {
	private DataManager<T> mTDataManager;

	public BaseAdapter() {
		this(null);
	}

	public BaseAdapter(List<T> list) {
		mTDataManager = new ListDataManagerImpl<>(list, this);
	}

	@Override
	public void add(T elem) {
		mTDataManager.add(elem);
	}

	@Override
	public void addAt(int location, T elem) {
		mTDataManager.addAt(location, elem);
	}

	@Override
	public void addItems(List<T> elements) {
		mTDataManager.addItems(elements);
	}

	@Override
	public void addItemsAt(int location, List<T> elements) {
		mTDataManager.addItemsAt(location, elements);
	}

	@Override
	public void replace(T oldElem, T newElem) {
		mTDataManager.replace(oldElem, newElem);
	}

	@Override
	public void replaceAt(int index, T elem) {
		mTDataManager.replaceAt(index, elem);
	}

	@Override
	public void replaceAll(List<T> elements) {
		mTDataManager.replaceAll(elements);
	}

	@Override
	public void remove(T elem) {
		mTDataManager.remove(elem);
	}

	@Override
	public void removeItems(List<T> elements) {
		mTDataManager.removeItems(elements);
	}

	@Override
	public void removeAt(int index) {
		mTDataManager.removeAt(index);
	}

	@Override
	public T getItem(int position) {
		return mTDataManager.getItem(position);
	}

	@Override
	public final int getDataSize() {
		return mTDataManager.getDataSize();
	}


	@Override
	public boolean contains(T elem) {
		return mTDataManager.contains(elem);
	}

	@Override
	public void setDataSource(List<T> elements) {
		mTDataManager.setDataSource(elements);
	}

	@Override
	public void clear() {
		mTDataManager.clear();
	}

	@Override
	public List<T> getItems() {
		return mTDataManager.getItems();
	}

	@Override
	public int getItemCount() {
		return mTDataManager.getDataSize();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
