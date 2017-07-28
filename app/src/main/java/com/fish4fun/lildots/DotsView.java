package com.fish4fun.lildots;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.util.AttributeSet;


public class DotsView extends android.support.v7.widget.AppCompatImageView {

    private AnimatedVectorDrawableCompat animatedVectorDrawable;

    private boolean cancelAnimation = false;

    private boolean isRunning = false;

    public DotsView(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(0);
        }
    }

    public DotsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(getAnimatedVectorResourceID(attrs));
        }
    }

    public DotsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(getAnimatedVectorResourceID(attrs));
        }
    }

    private int getAnimatedVectorResourceID(@Nullable AttributeSet attrs) {
        return attrs != null ? attrs.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", "srcCompat", 0) : 0;
    }

    private void init(int animatedVectorResourceID) {
        setSaveEnabled(true);
        if (animatedVectorResourceID != 0) {
            animatedVectorDrawable = AnimatedVectorDrawableCompat.create(getContext(), animatedVectorResourceID);
            setImageDrawable(animatedVectorDrawable);
        }
    }

    private Animatable2Compat.AnimationCallback animationCallback = new Animatable2Compat.AnimationCallback() {
        @Override
        public void onAnimationStart(Drawable drawable) {
            super.onAnimationStart(drawable);
        }

        @Override
        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (animatedVectorDrawable != null) {
                if (cancelAnimation) {
                    animatedVectorDrawable.stop();
                } else {
                    animatedVectorDrawable.start();
                }
            }
        }
    };

    public void start() {
        if (animatedVectorDrawable != null) {
            cancelAnimation = false;
            isRunning = true;
            animatedVectorDrawable.registerAnimationCallback(animationCallback);
            animatedVectorDrawable.start();
        }
    }

    protected void stop() {
        cancelAnimation = true;
        isRunning = false;
    }


    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.isRunning = isRunning;
        return savedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        isRunning = savedState.isRunning;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animatedVectorDrawable != null) {
            stop();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isRunning) {
            start();
        }
    }

    protected boolean isRunning() {
        return isRunning;
    }

    private static class SavedState extends BaseSavedState {
        boolean isRunning;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            isRunning = in.readInt() != 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(isRunning ? 1 : 0);
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
