package com.toan_it.library.library.libs.image;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoImageLoader implements ImageLoaderListener {
    /*@NonNull
    private final Picasso picasso;

    public PicassoImageLoader(@NonNull final Picasso picasso) {
        this.picasso = picasso;
    }*/
    @Override
    public void loadImage(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(Uri.parse(url));
    }
}
