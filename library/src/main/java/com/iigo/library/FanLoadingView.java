package com.iigo.library;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description A fan rotation loading view.
 */
public class FanLoadingView extends View {
    private final static int DEFAULT_COLOR = Color.WHITE;

    private Paint mCirclePaint;
    private Paint mSectorPaint;

    private float mCircleRadius;
    private float mStrokeWidth;

    private float mCenterX;
    private float mCenterY;

    private int mColor = DEFAULT_COLOR;

    private ObjectAnimator mObjectAnimator;
    private TimeInterpolator mTimeInterpolator = new AccelerateDecelerateInterpolator();
    private float mAnimatorValue;

    private long mCurrentAnimatorPlayTime;

    private RectF mSectorRectF;

    public FanLoadingView(Context context) {
        this(context, null);
    }

    public FanLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FanLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FanLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttrs(attrs);
        init();
    }

    private void parseAttrs(AttributeSet attrs) {
        if (attrs == null){
            return;
        }

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FanLoadingView);
        mColor = typedArray.getColor(R.styleable.FanLoadingView_main_color, DEFAULT_COLOR);
        int interpolatorValue = typedArray.getInt(R.styleable.FanLoadingView_interpolator, 0);
        switch(interpolatorValue) {
            case 0:
                this.mTimeInterpolator = new AccelerateDecelerateInterpolator();
                break;
            case 1:
                this.mTimeInterpolator = new AccelerateInterpolator();
                break;
            case 2:
                this.mTimeInterpolator = new DecelerateInterpolator();
                break;
            case 3:
                this.mTimeInterpolator = new BounceInterpolator();
                break;
            case 4:
                this.mTimeInterpolator = new CycleInterpolator(0.5F);
                break;
            case 5:
                this.mTimeInterpolator = new LinearInterpolator();
                break;
            case 6:
                this.mTimeInterpolator = new AnticipateOvershootInterpolator();
                break;
            case 7:
                this.mTimeInterpolator = new AnticipateInterpolator();
                break;
            case 8:
                this.mTimeInterpolator = new OvershootInterpolator();
        }

        typedArray.recycle();
    }

    private void init(){
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mColor);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mSectorPaint = new Paint();
        mSectorPaint.setAntiAlias(true);
        mSectorPaint.setColor(mColor);
        mSectorPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        stop();
        mCenterX = w / 2;
        mCenterY = h / 2;

        int minSize = Math.min(w, h);
        mStrokeWidth = minSize / 50f;

        mCirclePaint.setStrokeWidth(mStrokeWidth);

        mCircleRadius = (minSize - mStrokeWidth * 2) / 2f;

        float sectorRadius  = mCircleRadius * 4 / 5f;

        mSectorRectF = new RectF(mCenterX - sectorRadius, mCenterY - sectorRadius,
                mCenterX + sectorRadius,mCenterY + sectorRadius);

        setupAnimator();
    }

    private void setupAnimator(){
        mObjectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f);
        mObjectAnimator.setDuration(1200);
        mObjectAnimator.setInterpolator(mTimeInterpolator);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.start();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCenterX, mCenterY, mCircleRadius, mCirclePaint);

        mSectorPaint.setPathEffect(new CornerPathEffect(mCircleRadius / 10f));
        canvas.drawArc(mSectorRectF, -30, 60, true, mSectorPaint);
        canvas.drawArc(mSectorRectF, 60, 60, true, mSectorPaint);
        canvas.drawArc(mSectorRectF, 150, 60, true, mSectorPaint);
        canvas.drawArc(mSectorRectF, 240, 60, true, mSectorPaint);
    }

    /**
     * Pause animation.
     * */
    public void pause(){
        if (mObjectAnimator != null && mObjectAnimator.isRunning()){
            mCurrentAnimatorPlayTime = mObjectAnimator.getCurrentPlayTime();
            mObjectAnimator.cancel();
        }
    }

    /**
     * Resume animation.
     * */
    public void resume(){
        if (mObjectAnimator != null
                && !mObjectAnimator.isRunning()){
            mObjectAnimator.setCurrentPlayTime(mCurrentAnimatorPlayTime);
            mObjectAnimator.start();
        }
    }

    /**
     * Start animation.
     * */
    public void start(){
        mCurrentAnimatorPlayTime = 0;
        if (mObjectAnimator != null){
            mObjectAnimator.start();
        }
    }

    /**
     * Stop animation.
     * */
    public void stop(){
        mCurrentAnimatorPlayTime = 0;
        if (mObjectAnimator != null){
            mObjectAnimator.cancel();
        }
    }

    /**
     * Now set the main color.
     * */
    public void setColor(int color) {
        this.mColor = color;
        mCirclePaint.setColor(mColor);
        mSectorPaint.setColor(mColor);
        postInvalidate();
    }

    /**
     * Get the main color.
     * */
    public int getColor() {
        return mColor;
    }
}
