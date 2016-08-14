package com.toan_itc.data.datapage.base.page;

import com.toan_itc.data.datapage.base.IViewData;
import java.util.List;
import rx.Observable;

/**
 * Created by Toan.IT
 * Date:2016/3/30
 * Time:20:59
 */
public interface IPagination<T> extends IViewData<T> {

    /**
     * Init the pagination.
     */
    void initPage();

    /**
     * Load the next page data.
     */
    Observable<List<T>> loadNextPage();

    /**
     * Get the page has more
     *
     * @return whether the page has more data.
     */
    boolean hasMorePage();

    /**
     * Set the result has more page.
     *
     * @param hasMorePage whether the page has more data.
     */
    void setHasMorePage(boolean hasMorePage);


}
