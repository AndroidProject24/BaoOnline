/*
package com.toan_it.library.library.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.toan_it.library.library.activity.base.BaseActivity;
import com.toan_it.library.library.fragment.WebViewFragment;
import com.toan_it.library.library.utils.ClipboardUtils;
import com.toan_it.library.library.utils.SystemShareUtils;

*/
/**
 * Created by Toan.IT
 * Date: 18/05/2016
 *//*

public class WebViewActivity extends BaseActivity {

    private static String TAG = "WebViewActivity1";
    public static String WEB_URL = "webViewUrl";
    public static String TITLE = "webViewTitle";

    private Toolbar mToolbar;
    private String mUrl;
    private String mTitle;

    private FragmentManager mFragmentManager;
    private WebViewFragment mWebViewFragment;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {
        mUrl = getIntent().getExtras().getString(WEB_URL);
        mTitle = getIntent().getExtras().getString(TITLE);
        mFragmentManager = getSupportFragmentManager();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void setUpView() {
        //设置Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        dynamicAddSkinEnableView(mToolbar, "background", R.color.colorPrimary);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_copy) {
            ClipboardUtils.setText(this, mUrl);
            Snackbar.make(mToolbar, "已复制到剪切板", Snackbar.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_share) {
            SystemShareUtils.shareText(this, "【" + mTitle + "】链接:" + mUrl);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setUpData() {
        mWebViewFragment = new WebViewFragment() {
            @Override
            protected String getLoadUrl() {
                return mUrl;
            }
        };
        mFragmentManager.beginTransaction().replace(R.id.fl_content, mWebViewFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (mWebViewFragment.canGoBack()) {
            mWebViewFragment.goBack();
        } else {
            finish();
        }
    }


}
*/
