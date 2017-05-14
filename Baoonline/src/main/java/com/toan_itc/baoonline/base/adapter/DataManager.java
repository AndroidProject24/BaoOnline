package com.toan_itc.baoonline.base.adapter;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 17/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public interface DataManager<T> {

    //Add opt
    void add(T elem);

    void addAt(int location, T elem);

    void addItems(List<T> elements);

    void addItemsAt(int location, List<T> elements);


    //update opt
    void replace(T oldElem, T newElem);

    void replaceAt(int index, T elem);

    void replaceAll(List<T> elements);

    void setDataSource(List<T> elements);


    //remove opt
    void remove(T elem);

    void removeAt(int index);

    void removeItems(List<T> elements);


    //get
    T getItem(int position);


    List<T> getItems();

    int getDataSize();

    //contains
    boolean contains(T elem);

    boolean isEmpty();

    //clear opt
    void clear();
}
