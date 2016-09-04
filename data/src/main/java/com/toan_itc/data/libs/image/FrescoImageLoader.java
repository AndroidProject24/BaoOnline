package com.toan_itc.data.libs.image;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FrescoImageLoader implements ImageLoaderListener {
    @Override
    public void loadImage(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setImageURI(Uri.parse(url));
    }

    @Override
    public void loadController(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setLocalThumbnailPreviewsEnabled(true)
		        .setCacheChoice(ImageRequest.CacheChoice.DEFAULT)
		        .setProgressiveRenderingEnabled(true)
		        .setImageDecodeOptions(ImageDecodeOptions.defaults())
		        .setResizeOptions(new ResizeOptions(300, 250))
                .build();
	    DraweeController controller = Fresco.newDraweeControllerBuilder()
			    .setImageRequest(request)
			    .setAutoPlayAnimations(true)
			    .setOldController(simpleDraweeView.getController())
			    .build();
        simpleDraweeView.setController(controller);
    }

    @Override
    public void loadHierarchy(@NonNull String url, @NonNull SimpleDraweeView simpleDraweeView) {
	    GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(null)
			    .setFadeDuration(300)
			    .setPlaceholderImage(null)
			    .setBackground(null)
			    .setOverlays(null)
			    .build();
        simpleDraweeView.setHierarchy(hierarchy);
    }
}
