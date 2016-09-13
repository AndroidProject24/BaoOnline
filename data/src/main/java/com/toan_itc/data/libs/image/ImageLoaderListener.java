package com.toan_itc.data.libs.image;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;

public interface ImageLoaderListener {
    void loadImage(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView);

    void loadController(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView, int width, int height, @Nullable ControllerListener controllerListener);

	void loadHierarchy(@NonNull String url,@NonNull SimpleDraweeView simpleDraweeView);
}