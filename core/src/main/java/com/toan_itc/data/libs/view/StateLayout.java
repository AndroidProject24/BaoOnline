package com.toan_itc.data.libs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.toan_itc.data.R;
import com.toan_itc.data.libs.loading.AVLoadingIndicatorView;

/**
 * Created by Toan.IT
 * Date: 30/08/2016
 * Email: huynhvantoan.itc@gmail.com
 */
public class StateLayout extends RelativeLayout {
    private View contentView;

    private View emptyView;
    private View emptyContentView;

    private View errorView;
    private View errorContentView;

    private View progressView;
    private AVLoadingIndicatorView progressContentView;

    private TextView emptyTextView;
    private TextView errorTextView;
    private ImageView errorImageView;
    private ImageView emptyImageView;

    private View currentShowingView;


    public StateLayout(Context context) {
        this(context, null);
    }


    public StateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        parseAttrs(context, attrs);

        emptyView.setVisibility(View.GONE);

        errorView.setVisibility(View.GONE);

        progressView.setVisibility(View.GONE);

        currentShowingView = contentView;
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StateLayout, 0, 0);
        int progressViewId;
        Drawable errorDrawable;
        Drawable emptyDrawable;
        try {
            errorDrawable = a.getDrawable(R.styleable.StateLayout_errorDrawable);
            emptyDrawable = a.getDrawable(R.styleable.StateLayout_emptyDrawable);
            progressViewId = a.getResourceId(R.styleable.StateLayout_progressView, -1);
        } finally {
            a.recycle();
        }
        setViewSwitchAnimProvider(new FadeScaleViewAnimProvider());
        /******************************************************************************************/

        if (progressViewId != -1) {
            progressView = inflater.inflate(progressViewId, this, false);
        } else {
            progressView = inflater.inflate(R.layout.view_progress, this, false);
            progressContentView =(AVLoadingIndicatorView) progressView.findViewById(R.id.loading_view);
        }

        addView(progressView);
        /******************************************************************************************/

        /******************************************************************************************/

        errorView = inflater.inflate(R.layout.view_error, this, false);
        errorContentView = errorView.findViewById(R.id.error_content);
        errorTextView = (TextView) errorView.findViewById(R.id.errorTextView);
        errorImageView = (ImageView) errorView.findViewById(R.id.errorImageView);
        if (errorDrawable != null) {
            errorImageView.setImageDrawable(errorDrawable);
        } else {
            errorImageView.setImageResource(R.drawable.ic_exception);
        }
        addView(errorView);
        /******************************************************************************************/

        /******************************************************************************************/

        emptyView = inflater.inflate(R.layout.view_empty, this, false);
        emptyContentView = emptyView.findViewById(R.id.empty_content);
        emptyTextView = (TextView) emptyView.findViewById(R.id.emptyTextView);
        emptyImageView = (ImageView) emptyView.findViewById(R.id.emptyImageView);
        if (emptyDrawable != null) {
            emptyImageView.setImageDrawable(emptyDrawable);
        } else {
            emptyImageView.setImageResource(R.drawable.ic_empty);
        }
        addView(emptyView);
        /******************************************************************************************/

    }

    private void checkIsContentView(View view) {
        if (contentView == null && view != errorView && view != progressView && view != emptyView) {
            contentView = view;
            currentShowingView = contentView;
        }
    }

    public ImageView getErrorImageView() {
        return errorImageView;
    }

    public ImageView getEmptyImageView() {
        return emptyImageView;
    }

    public void setViewSwitchAnimProvider(ViewAnimProvider viewSwitchAnimProvider) {
        if (viewSwitchAnimProvider != null) {
            this.showAnimation = viewSwitchAnimProvider.showAnimation();
            this.hideAnimation = viewSwitchAnimProvider.hideAnimation();
        }
    }


    public boolean isShouldPlayAnim() {
        return shouldPlayAnim;
    }

    public void setShouldPlayAnim(boolean shouldPlayAnim) {
        this.shouldPlayAnim = shouldPlayAnim;
    }

    private boolean shouldPlayAnim = true;
    private Animation hideAnimation;
    private Animation showAnimation;

    public Animation getShowAnimation() {
        return showAnimation;
    }

    public void setShowAnimation(Animation showAnimation) {
        this.showAnimation = showAnimation;
    }

    public Animation getHideAnimation() {
        return hideAnimation;
    }

    public void setHideAnimation(Animation hideAnimation) {
        this.hideAnimation = hideAnimation;
    }

    private void switchWithAnimation(final View toBeShown) {
        final View toBeHided = currentShowingView;
        if (toBeHided == toBeShown)
            return;
        if (shouldPlayAnim) {
            if (toBeHided != null) {
                if (hideAnimation != null) {
                    hideAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            toBeHided.setVisibility(GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    hideAnimation.setFillAfter(false);
                    toBeHided.startAnimation(hideAnimation);
                } else
                    toBeHided.setVisibility(GONE);
            }
            if (toBeShown != null) {
                if (toBeShown.getVisibility() != VISIBLE)
                    toBeShown.setVisibility(VISIBLE);
                currentShowingView = toBeShown;
                if (showAnimation != null) {
                    showAnimation.setFillAfter(false);
                    toBeShown.startAnimation(showAnimation);
                }
            }
        } else {
            if (toBeHided != null) {
                toBeHided.setVisibility(GONE);
            }
            if (toBeShown != null) {
                currentShowingView = toBeShown;
                toBeShown.setVisibility(VISIBLE);
            }
        }

    }

    public void setEmptyContentViewMargin(int left, int top, int right, int bottom) {
        ((LayoutParams) emptyContentView.getLayoutParams()).setMargins(left, top, right, bottom);
    }

    public void setErrorContentViewMargin(int left, int top, int right, int bottom) {
        ((LayoutParams) errorContentView.getLayoutParams()).setMargins(left, top, right, bottom);
    }

    public void setProgressContentViewMargin(int left, int top, int right, int bottom) {
        ((LayoutParams) progressContentView.getLayoutParams()).setMargins(left, top, right, bottom);
    }

    public void setInfoContentViewMargin(int left, int top, int right, int bottom) {
        ((LayoutParams) emptyContentView.getLayoutParams()).setMargins(left, top, right, bottom);
        ((LayoutParams) errorContentView.getLayoutParams()).setMargins(left, top, right, bottom);
        ((LayoutParams) progressContentView.getLayoutParams()).setMargins(left, top, right, bottom);
    }


    public void showContentView() {
        onHideProgressView();
        switchWithAnimation(contentView);
    }

    public void showEmptyView() {
        showEmptyView(null);
    }

    public void showEmptyView(String msg) {
        onHideContentView();
        onHideProgressView();
        if (!TextUtils.isEmpty(msg))
            emptyTextView.setText(msg);
        switchWithAnimation(emptyView);
    }

    public void showErrorView() {
        showErrorView(null);
    }

    public void showErrorView(String msg) {
        onHideContentView();
        onHideProgressView();
        if (msg != null)
            errorTextView.setText(msg);
        switchWithAnimation(errorView);
    }

    public void showProgressView() {
        progressContentView.smoothToShow();
        onHideContentView();
        switchWithAnimation(progressView);
    }

    public void setErrorAction(final OnClickListener onErrorButtonClickListener) {
        errorView.setOnClickListener(onErrorButtonClickListener);
    }

    public void setEmptyAction(final OnClickListener onEmptyButtonClickListener) {
        emptyView.setOnClickListener(onEmptyButtonClickListener);
    }


    public void setErrorAndEmptyAction(final OnClickListener errorAndEmptyAction) {
        errorView.setOnClickListener(errorAndEmptyAction);
        emptyView.setOnClickListener(errorAndEmptyAction);
    }

    protected void onHideContentView() {
        //Override me
    }
    protected void onHideProgressView() {
        if(progressContentView!=null) {
            progressContentView.smoothToHide();
        }
    }


    /**
     * addView
     */

    @Override
    public void addView(View child) {
        checkIsContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        checkIsContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, index, params);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int width, int height) {
        checkIsContentView(child);
        super.addView(child, width, height);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params, boolean preventRequestLayout) {
        checkIsContentView(child);
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }
}
