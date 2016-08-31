package com.toan_itc.baoonline.ui.readnews.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;

import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseToolbar;
import com.toan_itc.baoonline.library.injector.module.ActivityModule;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.navigation.Navigator;
import com.toan_itc.baoonline.ui.readnews.di.DaggerReadNewsComponent;
import com.toan_itc.baoonline.ui.readnews.di.ReadNewsComponent;
import com.toan_itc.baoonline.ui.readnews.di.ReadNewsModule;
import com.toan_itc.data.libs.view.StateLayout;
import com.toan_itc.data.utils.CommonUtils;
import com.toan_itc.data.utils.Constants;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadNewsActivity extends BaseToolbar implements HasComponent<ReadNewsComponent> {
    @BindView(R.id.webView)
    WebView mWebView;
    @Inject
    Provider<Navigator> mNavigator;
    private ReadNewsComponent mReadNewsComponent;
    @Override
    protected void initViews(Bundle savedInstanceState) {
        if(!CommonUtils.isEmpty(getIntent().getBundleExtra(Navigator.EXTRA_ARGS).getString(Constants.BUNLDE)))
            mWebView.loadUrl(getIntent().getBundleExtra(Navigator.EXTRA_ARGS).getString(Constants.BUNLDE));
        else
            snackBarBuild("Không tìm thấy link!");
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.read_news_activity;
    }

    @Override
    protected void initData() {
        /*final int[][] subButtonColors = new int[3][2];
        for (int i = 0; i<3; i++) {
            subButtonColors[i][1] = GetRandomColor();
            subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
        }
        Drawable[] subButtonDrawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.drawable.ic_back,
                R.drawable.ic_back,
                R.drawable.ic_back
        };
        for (int i = 0; i < 3; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);
        mBoomMenuButton.postDelayed(() -> new BoomMenuButton.Builder()
                .subButtons(subButtonDrawables, subButtonColors, Constants.url)
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
                        Toast.makeText(ReadNewsActivity.this, "On click " + Constants.url[buttonIndex], Toast.LENGTH_SHORT).show();
                        replaceFagment(R.id.content_main,null);
                    }
                })
                .init(mBoomMenuButton),1);*/
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
        if (mReadNewsComponent == null) {
            mReadNewsComponent = DaggerReadNewsComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .activityModule(new ActivityModule(this))
                    .readNewsModule(new ReadNewsModule(getIntent().getBundleExtra(Navigator.EXTRA_ARGS).getString(Constants.BUNLDE)))
                    .build();
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
}