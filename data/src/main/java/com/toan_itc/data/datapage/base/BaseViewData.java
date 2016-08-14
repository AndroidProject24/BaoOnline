package com.toan_itc.data.datapage.base;

import java.util.List;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date:2016/3/30
 * Time:20:59
 */
public class BaseViewData<T> implements IViewData<T> {

    @Override
    public T getData() {
        return null;
    }

    @Override
    public List<T> getListData() {
        return null;
    }

    @Override
    public Observable<T> fetchData() {
        return null;
    }

    @Override
    public Observable<T> updateData() {
        return null;
    }

    @Override
    public Observable<T> deleteData() {
        return null;
    }


}
