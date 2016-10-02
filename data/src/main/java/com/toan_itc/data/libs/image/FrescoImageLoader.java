package com.toan_itc.data.libs.image;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.toan_itc.data.utils.DensityUtil;
import com.toan_itc.data.utils.Preconditions;

public class FrescoImageLoader implements ImageLoaderListener {
    @Override
    public void loadImage(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView) {
	    Preconditions.checkNotNull(simpleDraweeView,"SimpleDraweeView not null!");
        simpleDraweeView.setImageURI(Uri.parse(url));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadController(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView, int width, int height,@Nullable ControllerListener controllerListener) {
	    Preconditions.checkNotNull(simpleDraweeView,"simpleDraweeView not null!");
	    Preconditions.checkNotNull(controllerListener,"controllerListener not null!");
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setLocalThumbnailPreviewsEnabled(true)
		        .setCacheChoice(ImageRequest.CacheChoice.DEFAULT)
		        .setProgressiveRenderingEnabled(true)
		        .setImageDecodeOptions(ImageDecodeOptions.defaults())
		        .setResizeOptions(new ResizeOptions(DensityUtil.dp2px(width), DensityUtil.dp2px(height)))
                .build();
	    DraweeController controller = Fresco.newDraweeControllerBuilder()
			    .setTapToRetryEnabled(true)
			    .setAutoPlayAnimations(true)
			    .setOldController(simpleDraweeView.getController())
			    .setImageRequest(request)
			    .setControllerListener(controllerListener)
			    .build();
        simpleDraweeView.setController(controller);
    }

    @Override
    public void loadHierarchy(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView) {
	    Preconditions.checkNotNull(simpleDraweeView,"simpleDraweeView not null!");
	    GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(null)
			    .setFadeDuration(300)
			    .setPlaceholderImage(null)
			    .setBackground(null)
			    .setOverlays(null)
			    .build();
        simpleDraweeView.setHierarchy(hierarchy);
    }
}
