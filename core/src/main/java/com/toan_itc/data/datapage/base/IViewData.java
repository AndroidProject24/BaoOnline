package com.toan_itc.data.datapage.base;

import java.util.List;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date:2016/3/30
 * Time:20:59
 */
public interface IViewData<T> {

    /**
     * Get the data
     *
     * @return the T data.
     */
    T getData();

    /**
     * Get the list data.
     *
     * @return the list T data.
     */
    List<T> getListData();

    /**
     * Get the data.
     *
     * @return the observable.
     */
    Observable<T> fetchData();

    /**
     * Update the data.
     *
     * @return the observable.
     */
    Observable<T> updateData();

    /**
     * Delete the data.
     *
     * @return the observable.
     */
    Observable<T> deleteData();
}
