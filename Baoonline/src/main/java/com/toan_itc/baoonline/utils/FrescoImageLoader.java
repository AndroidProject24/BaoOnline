package com.toan_itc.baoonline.utils;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.facebook.drawee.view.SimpleDraweeView;
import com.toan_itc.baoonline.interface_.ImageLoaderListener;

public class FrescoImageLoader implements ImageLoaderListener {
    @Override
    public void loadImage(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(Uri.parse(url));
    }
}
