package com.toan_it.library.library.libs.image;

import android.support.annotation.NonNull;

import com.facebook.drawee.view.SimpleDraweeView;

public interface ImageLoaderListener {
    void loadImage(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView);
}