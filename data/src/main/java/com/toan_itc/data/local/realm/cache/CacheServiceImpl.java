/*
package com.toan_itc.data.local.realm.cache;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;

public class CacheServiceImpl implements CacheService {

    @Inject
    public CacheServiceImpl() {
    }

    @Override
    public <T, E extends RealmObject & CacheObject<T>> void set(final Class<E> clazz, T model) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.createObject(clazz).map(model));
        realm.close();
    }

    @Override
    public <T, E extends RealmObject & CacheObject<T>> Observable<T> get(final Class<E> clazz) {
        return Observable.create(subscriber -> {
            Realm realm = Realm.getDefaultInstance();
            for (E result : realm.where(clazz).findAll()) {
                subscriber.onNext(result.map());
            }
            subscriber.onCompleted();
            realm.close();
        });
    }

    @Override
    public <T, E extends RealmObject & CacheObject<T>> long count(Class<E> clazz) {
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(clazz).count();
        realm.close();
        return count;
    }

    @Override
    public <T, E extends RealmObject & CacheObject<T>> void deleteAll(Class<E> clazz) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(clazz));
        realm.close();
    }
}
*/
