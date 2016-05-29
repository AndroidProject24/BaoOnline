package com.toan_itc.baoonline.ui.activity;

import android.os.Bundle;

import com.toan_it.library.library.BaseApplication;
import com.toan_it.library.library.activity.base.BaseActivity;
import com.toan_it.library.library.data.local.DatabaseRealm;
import com.toan_it.library.library.data.local.PreferencesHelper;
import com.toan_it.library.library.data.rxjava.RxBus;
import com.toan_it.library.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.injector.ActivityComponent;
import com.toan_itc.baoonline.injector.DaggerActivityComponent;

public class MainActivity extends BaseActivity {
    private BaseActivity mBaseActivity=this;
    private DatabaseRealm mDatabaseRealm;
    private PreferencesHelper mPreferencesHelper;
    private RxBus rxBus;
    private ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*RestApi restClient=RestClient.sRestClient();
        RestData restData =new RestData(restClient);
        restData.GetRss("http://vietnamnet.vn/rss/tin-noi-bat.rss")
                .map(responseBody -> {
                    BufferedReader reader = null;
                    StringBuilder sb = new StringBuilder();
                    try {
                        reader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
                        String line;
                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    ;
                    return sb.toString();
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            TikXml parse= new TikXml.Builder().exceptionOnUnreadXml(false).build();
                            RssFeed rssFeed=parse.read(new Buffer().writeUtf8(s), RssFeed.class);
                            //Log.wtf("employee=", rssFeed.toString());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });*/
    }

    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void injectViews() {
        //ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.contentFrame);
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectData() {
       /* mDatabaseRealm=mActivityComponent.mDatabaseRealm();
        mDatabaseRealm.setup();
        mPreferencesHelper = mActivityComponent.mPreferencesHelper();
        rxBus=mActivityComponent.mRxBus();
        mDatabaseRealm.Get_News(this);*/
    }

    @Override
    protected void injectDependencies() {
        if (mActivityComponent == null) {
            mActivityComponent= DaggerActivityComponent.builder()
                    .applicationComponent(BaseApplication.get(this).getApplicationComponent())
                    .build();
        }
    }
    protected void remove_fragment(){
        /*Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getName());
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        Fragment menufragment = getSupportFragmentManager().findFragmentByTag(SkinBaseFragment.class.getName());
        if(menufragment != null) {
            getSupportFragmentManager().beginTransaction().remove(menufragment).commit();
        }*/
        /*Fragment fragmentseach = getSupportFragmentManager().findFragmentByTag(PlayerSearchFragment.class.getName());
        if(fragmentseach != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentseach).commit();
        }*/
    }
}