package com.toan_itc.data.local.realm;

import com.toan_itc.data.local.realm.entities.RssDiskRealmMapper;
import com.toan_itc.data.model.rss.RssFeedItem;

import java.util.List;

import rx.Observable;

/**
 * Created by Toan.I
 * Date: 14/07/2016
 */
public class DataSourceDiskRealm implements DataSourceDisk {

    private final RssDiskRealmMapper mapper;

    public DataSourceDiskRealm(RssDiskRealmMapper namesDiskRealmMapper){
        if (namesDiskRealmMapper == null){
            throw new IllegalArgumentException("namesDiskRealmMapper cannot be null!!!");
        }
        this.mapper = namesDiskRealmMapper;
    }

    @Override
    public Observable<RssFeedItem> findByID(Integer identifier) {
        return null;
    }

    @Override
    public Observable<List<RssFeedItem>> getBookmark() {
        return null;
    }

    @Override
    public Observable<Integer> saveRss(RssFeedItem rssFeedItem) {
        return null;
    }

    @Override
    public Observable<Boolean> removeRss(RssFeedItem rssFeedItem) {
        return null;
    }

/*


    @Override
    public Observable<Names> findByID(final Integer identifier) {
        return Observable.create(new Observable.OnSubscribe<Names>() {

            @Override
            public void call(Subscriber<? super Names> subscriber) {
                Realm realm = null;

                try {
                    realm = Realm.getDefaultInstance();

                    NamesDiskRealm result = realm.where(NamesDiskRealm.class)
                            .equalTo("id",identifier)
                            .findFirst();

                    subscriber.onNext(mapper.dataToModel(result));
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }
    @Override
    public Observable<List<Names>> getNames() {
        return Observable.create(new Observable.OnSubscribe<List<Names>>() {
            @Override
            public void call(Subscriber<? super List<Names>> subscriber) {
                Realm realm = null;
                List<Names> listNames = new ArrayList<>();

                try{
                    realm = Realm.getDefaultInstance();
                    RealmResults<NamesDiskRealm> results = realm.allObjects(NamesDiskRealm.class);

                    if (!results.isEmpty()){
                        for (NamesDiskRealm ndr : results) {
                            listNames.add( mapper.dataToModel(ndr) );
                        }
                    }

                    Collections.reverse(listNames);

                    subscriber.onNext(listNames);
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                } finally {
                    if (realm != null){
                        realm.close();
                    }
                }

            }
        });
    }

    @Override
    public Observable<Integer> saveName(final Names names) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Realm realm = null;

                try {
                    realm = Realm.getDefaultInstance();

                    realm.beginTransaction();
                    NamesDiskRealm namesDiskRealm = mapper.modelToData(names);
                    realm.copyToRealm(namesDiskRealm);
                    realm.commitTransaction();

                    subscriber.onNext( namesDiskRealm.getId() );
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }

    @Override
    public Observable<Boolean> removeName(final Names name) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                Realm realm = null;

                try {
                    realm = Realm.getDefaultInstance();

                    realm.beginTransaction();
                    NamesDiskRealm result = realm.where(NamesDiskRealm.class)
                            .equalTo("id",name.getId())
                            .findFirst();

                    result.removeFromRealm();

                    realm.commitTransaction();

                    boolean isEmpty = realm.allObjects(NamesDiskRealm.class).size() <= 0;

                    subscriber.onNext( isEmpty );
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });
    }
*/

}
