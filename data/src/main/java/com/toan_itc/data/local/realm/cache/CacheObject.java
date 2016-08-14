package com.toan_itc.data.local.realm.cache;

public interface CacheObject<T> {

    T map();

    void map(T model);

}
