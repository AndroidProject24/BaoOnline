package com.toan_itc.baoonline.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Eases.EaseType;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.ClickEffectType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;
import com.toan_it.library.skinloader.base.SkinBaseFragment;
import com.toan_itc.baoonline.R;
import com.toan_itc.baoonline.library.base.BaseToolbar;
import com.toan_itc.baoonline.library.injector.component.DaggerRssComponent;
import com.toan_itc.baoonline.library.injector.component.RssComponent;
import com.toan_itc.baoonline.library.injector.module.ListRssModule;
import com.toan_itc.baoonline.ui.fragment.HomeFragment;
import com.toan_itc.data.local.DatabaseRealm;
import com.toan_itc.data.utils.Constants;

import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseToolbar<RssComponent> {
    @Inject
    DatabaseRealm mDatabaseRealm;
    @BindView(R.id.boomMenu)
    BoomMenuButton mBoomMenuButton;
    private static final String INTENT_EXTRA_PARAM_RSS = "com.toan_itc.BaoOnline.RSS";
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }
    public static Intent getCallingIntent(Context context,String linkRss) {
        Intent callingIntent = new Intent(context, MainActivity.class);
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
                        Toast.makeText(MainActivity.this, "On click " + Constants.url[buttonIndex], Toast.LENGTH_SHORT).show();
                        getNavigator().navigateToLinkRss(MainActivity.this,Constants.url[buttonIndex]);
                    }
                })
                .init(mBoomMenuButton),1);
    }

    @Override
    protected RssComponent injectDependencies() {
        return DaggerRssComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .listRssModule(new ListRssModule(Constants.url[0]))
                .build();
    }
    protected void remove_fragment(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getName());
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        Fragment menufragment = getSupportFragmentManager().findFragmentByTag(SkinBaseFragment.class.getName());
        if(menufragment != null) {
            getSupportFragmentManager().beginTransaction().remove(menufragment).commit();
        }
       /* Fragment fragmentseach = getSupportFragmentManager().findFragmentByTag(PlayerSearchFragment.class.getName());
        if(fragmentseach != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentseach).commit();
        }*/
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