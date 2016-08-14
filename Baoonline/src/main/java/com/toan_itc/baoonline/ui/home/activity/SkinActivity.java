/*
package com.toan_itc.baoonline.ui.splash.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.common.file.FileUtils;
import com.toan_it.library.skinloader.base.SkinBaseActivity;
import com.toan_it.library.skinloader.listener.ILoaderListener;
import com.toan_it.library.skinloader.load.SkinManager;
import com.toan_itc.baoonline.R;

import java.io.File;

public class SkinActivity extends SkinBaseActivity {
    private static String TAG = "ChangeSkinFragment";

    private static String SKIN_BROWN_NAME = "skin_brown.skin";
    private static String SKIN_BLACK_NAME = "skin_black.skin";
    private static String SKIN_DIR;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void init() {
        SKIN_DIR = FileUtils.getSkinDirPath(getMContext());
    }

    @Override
    protected void setUpView() {
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.ll_green);
        LinearLayout linearLayout1=(LinearLayout)findViewById(R.id.ll_brown);
        LinearLayout linearLayout2=(LinearLayout)findViewById(R.id.ll_black);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().restoreDefaultTheme();

            }
        });
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skinFullName = SKIN_DIR + File.separator + "skin_brown.skin";
                FileUtils.moveRawToDir(getMContext(), "skin_brown.skin", skinFullName);
                File skin = new File(skinFullName);
                if (!skin.exists()) {
                    Toast.makeText(getMContext(), "请检查" + skinFullName + "是否存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.wtf("skin=",skin.getAbsolutePath());
                SkinManager.getInstance().load(skin.getAbsolutePath(),
                        new ILoaderListener() {
                            @Override
                            public void onStart() {
                                Log.e(TAG, "loadSkinStart");
                            }

                            @Override
                            public void onSuccess() {
                                Log.i(TAG, "loadSkinSuccess");
                                Toast.makeText(getMContext(), "切换成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed() {
                                Log.i(TAG, "loadSkinFail");
                                Toast.makeText(getMContext(), "切换失败", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skinFullName = SKIN_DIR + File.separator + "skin_black.skin";
                FileUtils.moveRawToDir(getMContext(), "skin_black.skin", skinFullName);
                File skin = new File(skinFullName);
                if (!skin.exists()) {
                    Toast.makeText(getMContext(), "请检查" + skinFullName + "是否存在", Toast.LENGTH_SHORT).show();
                    return;
                }
                SkinManager.getInstance().load(skin.getAbsolutePath(),
                        new ILoaderListener() {
                            @Override
                            public void onStart() {
                                Log.e(TAG, "loadSkinStart");
                            }

                            @Override
                            public void onSuccess() {
                                Log.e(TAG, "loadSkinSuccess");
                                Toast.makeText(getMContext(), "切换成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed() {
                                Log.e(TAG, "loadSkinFail");
                                Toast.makeText(getMContext(), "切换失败", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
*/
