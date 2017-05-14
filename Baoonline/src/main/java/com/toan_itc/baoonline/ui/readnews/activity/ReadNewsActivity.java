/*
package com.toan_itc.baoonline.ui.readnews.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.toan_itc.baoonline.R;
import com.toan_itc.data.base.BaseToolbar;
import com.toan_itc.baoonline.injector.module.ActivityModule;
import com.toan_itc.baoonline.injector.scope.HasComponent;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.readnews.customtab.CustomTabActivityHelper;
import com.toan_itc.baoonline.ui.readnews.di.DaggerReadNewsComponent;
import com.toan_itc.baoonline.ui.readnews.di.ReadNewsComponent;
import com.toan_itc.baoonline.ui.readnews.di.ReadNewsModule;
import com.toan_itc.baoonline.ui.readnews.mvp.ReadNews;
import com.toan_itc.baoonline.ui.readnews.mvp.ReadNewsPresenter;
import com.toan_itc.data.config.Constants;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.model.newdetails.NewsDetails;
import com.toan_itc.data.utils.Preconditions;
import com.toan_itc.data.utils.logger.Logger;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadNewsActivity extends BaseToolbar implements ReadNews,HasComponent<ReadNewsComponent> {
    @BindView(R.id.txt_title)
    TextView mTextTitle;
    @BindView(R.id.txt_pubdate)
    TextView mTextPubdate;
    @BindView(R.id.webView)
    WebView mWebView;
	@BindView(R.id.boom_circle)
	BoomMenuButton mBoomMenuButton;
    @Inject
    Provider<Navigator> mNavigator;
    @Inject
    ReadNewsPresenter mReadNewsPresenter;
    private ReadNewsComponent mReadNewsComponent;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        mReadNewsPresenter.attachView(this);
	    initWebview();
	    initBoomMenu();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.read_news_activity;
    }

    @Override
    protected void initData() {
        mTextTitle.setText(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.NEWS_TITLE));
        mTextPubdate.setText(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.NEWS_PUBDATE));
        mReadNewsPresenter.getContent_News();
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return ButterKnife.findById(this,R.id.stateLayout);
    }

    @Override
    public ReadNewsComponent getComponent() {
        if (mReadNewsComponent == null&&!Preconditions.isEmpty(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.BUNLDE))) {
            mReadNewsComponent = DaggerReadNewsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .readNewsModule(new ReadNewsModule(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.BUNLDE)))
                    .build();
        }else{
            snackBarBuild("Không tìm thấy link!");
        }
        return mReadNewsComponent;
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar;
    }

    @Override
    protected int getToolbarTitleResId() {
        return R.string.app_name;
    }

	private void initBoomMenu(){
		*/
/*final int[][] subButtonColors = new int[3][2];
		for (int i = 0; i<3; i++) {
			subButtonColors[i][1] = GetRandomColor();
			subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
		}
		Drawable[] subButtonDrawables = new Drawable[3];
		int[] drawablesResource = new int[]{
				R.drawable.ic_empty,
				R.drawable.ic_empty,
				R.drawable.ic_empty
		};
		String[] menu={"aaaa","bbbb","cccc"};
		for (int i = 0; i < 3; i++)
			subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);
		mBoomMenuButton.postDelayed(() -> new BoomMenuButton.Builder()
				.subButtons(subButtonDrawables, subButtonColors,menu)
				.button(ButtonType.CIRCLE)
				.duration(2000)
				.autoDismiss(true)
				.cancelable(true)
				.dim(DimType.DIM_6)
				.clickEffect(ClickEffectType.RIPPLE)
				.rotateDegree(720)
				.showRotateEase(EaseType.EaseOutBack)
				.boom(BoomType.PARABOLA)
				.place(PlaceType.CIRCLE_3_1)
				.subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
				.onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
					@Override
					public void onClick(int buttonIndex) {
						snackBarBuild("Click");
					}
				})
				.init(mBoomMenuButton),1);*//*

	}
    private static String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B"};

    public static int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }

	@SuppressLint("SetJavaScriptEnabled")
	private void initWebview(){
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.requestFocus();
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setWebViewClient(new MyWebViewClient());
	}
	private class MyWebViewClient extends WebViewClient{
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			hideLoading();
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
			return super.shouldOverrideUrlLoading(view, request);
		}

		@Override
		public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
			super.onReceivedError(view, request, error);
			hideLoading();
			CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
			CustomTabActivityHelper.openCustomTab(ReadNewsActivity.this, customTabsIntent, Uri.parse(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.BUNLDE)),null);
		}
	}
    @Override
    public void loadNews(NewsDetails newsDetails) {
        Logger.wtf(newsDetails.getDetails());
        if(!Preconditions.isEmpty(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.BUNLDE))){
	        mWebView.loadDataWithBaseURL(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.BUNLDE),newsDetails.getDetails(),"text/html", "utf-8", null);
        }
        else {
	        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
	        CustomTabActivityHelper.openCustomTab(ReadNewsActivity.this, customTabsIntent, Uri.parse(getIntent().getBundleExtra(Navigator.EXTRA_ARG).getString(Constants.BUNLDE)), null);
        }
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mWebView !=null) {
			mWebView.removeAllViews();
			mWebView.destroy();
		}
	}
}*/
