package com.toan_itc.baoonline.data.glidemodule;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.toan_it.library.library.utils.Constant;

import java.io.InputStream;

/**
 * Created by Toan.IT
 * Date: 22/05/2016
 */
public class OkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //Disk Cache
        int cacheSize100MegaBytes = 104857600;
        builder.setDiskCache(
                new ExternalCacheDiskCacheFactory(context, Constant.IMAGE_CACHE, cacheSize100MegaBytes )
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
        //Use
        /*glide.register(CustomImageSizeModel.class, InputStream.class, new CustomImageSizeModelFactory());
        //Activity image size
        String baseImageUrl = "https://futurestud.io/images/example.png";
        CustomImageSizeModel customImageRequest = new CustomImageSizeModelFutureStudio( baseImageUrl );

        Glide
                .with( context )
                .load( customImageRequest )
                .into( imageView2 );*/
    }
}
