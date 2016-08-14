package com.toan_itc.baoonline.ui.details.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Eases.EaseType;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.ClickEffectType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseToolbar;
import com.toan_itc.baoonline.library.injector.component.ActivityComponent;
import com.toan_itc.baoonline.library.injector.component.DaggerActivityComponent;
import com.toan_itc.baoonline.library.injector.scope.HasComponent;
import com.toan_itc.baoonline.ui.home.fragment.HomeFragment;
import com.toan_itc.data.utils.Constants;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseToolbar implements HasComponent<ActivityComponent> {
  /*  @Inject
    DatabaseRealm mDatabaseRealm;*/
    @BindView(R.id.boomMenu)
    BoomMenuButton mBoomMenuButton;
    private ActivityComponent mActivityComponent;
    private static final String INTENT_EXTRA_PARAM_RSS = "com.toan_itc.BaoOnline.RSS";
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DetailsActivity.class);
    }
    public static Intent getCallingIntent(Context context,String linkRss) {
        Intent callingIntent = new Intent(context, DetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_RSS, linkRss);
        return callingIntent;
    }
    @Override
    protected void initViews() {
        addFagment(R.id.contentFragment, HomeFragment.newInstance());
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        final int[][] subButtonColors = new int[3][2];
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
                        Toast.makeText(DetailsActivity.this, "On click " + Constants.url[buttonIndex], Toast.LENGTH_SHORT).show();
                        replaceFagment(R.id.contentFragment,null);
                    }
                })
                .init(mBoomMenuButton),1);
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }

    @Override
    protected View getLoadingTargetView() {
        return ButterKnife.findById(this, R.id.coordinatorLayout);
    }

    @Override
    public ActivityComponent getComponent() {
        if(mActivityComponent == null) {
            mActivityComponent =DaggerActivityComponent.builder()
                    .applicationComponent(getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityComponent=null;
    }
}