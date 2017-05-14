package com.toan_itc.data.datapage.base;

import com.toan_itc.data.datapage.base.page.IPagination;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Toan.IT
 * Date:2016/3/30
 * Time:20:59
 */
public abstract class BasePageIndexViewData<T> implements IPagination<T> {

    public int page = 1;
    public int pageSize = 20;
    private boolean mHasMorePage = true;
    private List<T> mList;

    @Override
    public void initPage() {
        page = 1;
        mHasMorePage = true;
    }

    @Override
    public Observable<List<T>> loadNextPage() {
        if (mHasMorePage) {
            return fetchListData().flatMap(new Func1<List<T>, Observable<List<T>>>() {
                @Override
                public Observable<List<T>> call(List<T> ts) {
                    if (ts != null) {
                        if (mList == null) {
                            mList = ts;
                        } else {
                            mList.addAll(ts);
                        }
                        page++;
                        if (ts.size() < pageSize) {
                            setHasMorePage(false);
                        }
                    } else {
                        setHasMorePage(false);
                    }
                    return Observable.just(mList);
                }
            });
        } else {
            return null;
        }
    }

    /**
     * Fetch the list data. Child class must override the method.
     *
     * @return the list data.
     */
    protected abstract Observable<List<T>> fetchListData();

    @Override
    public boolean hasMorePage() {
        return mHasMorePage;
    }

    @Override
    public void setHasMorePage(boolean hasMorePage) {
        mHasMorePage = hasMorePage;
    }

    @Override
    public T getData() {
        return null;
    }

    @Override
    public List<T> getListData() {
        return mList;
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
