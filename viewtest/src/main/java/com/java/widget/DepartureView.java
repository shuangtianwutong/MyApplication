package com.java.widget;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.Scroller;

import com.java.utils.DimenUtil;

import java.lang.ref.WeakReference;

/**
 * 上车点view
 * Created by qiuchunlong on 16/9/27.
 */
public class DepartureView extends View {
    private Scroller mscroller;

    /**
     * 正常状态
     */
    public static final int TYPE_NORMAL = 1;
    /**
     * 正在加载动画
     */
    public static final int TYPE_LOADING = 2;
    /**
     * 加载完成动画
     */
    public static final int TYPE_LOADED = 3;
    /**
     * 显示文字状态
     */
    public static final int TYPE_TEXT = 4;

    /**
     * 当前状态类型
     */
    int type = TYPE_NORMAL;

    /**
     * 小圈颜色
     */
    private int mSmallCircleColor = Color.parseColor("#ffffff");
    /**
     * 大圈颜色
     */
    private int mBigCircleColor = Color.parseColor("#3cbca3");

    /**
     * 大圈边颜色
     */
    private int mBigCircleStrokeColor = Color.parseColor("#329e89");

    /**
     * 下方小棍颜色
     */
    private int mRectColor = Color.parseColor("#329e89");

    /**
     * 下方小棍颜色
     */
    private int mTextColor = Color.parseColor("#ffffff");
    /**
     * 阴影颜色
     */
    private int mShadowColor = Color.parseColor("#7f000000");

    /**
     * 绘制小圈画笔
     */
    private Paint mWhiteCirclePaint = null;


    /**
     * 绘制大圈画笔
     */
    private Paint mGreenCirclePaint = null;


    /**
     * 绘制大圈边画笔
     */
    private Paint mBigCircleStorkePaint = null;


    /**
     * 绘制方块画笔
     */
    private Paint mRectPaint = null;

    /**
     * 文字画笔
     */
    private Paint mTextPaint = null;

    /**
     * 阴影画笔
     */
    private Paint mShadowPaint = null;

    /**
     * 小圈宽度
     */
    private int mSmallCricleRadius = 0;

    /**
     * 外圈宽度
     */
    private int mBigCircleRadius = 0;

    /**
     * 外圈宽度
     */
    private int mBigCircleStorkeWidth = 0;

    /**
     * 小棍宽度
     */
    private int mRectWidth = 0;

    /**
     * 小棍高度
     */
    private int mRectHeight = 0;

    /**
     * 跳动动画的偏离值
     */
    private int mOffestY = 0;

    /**
     * 文字大小
     */
    private int mTextSize = 0;

    /**
     * 阴影宽度
     */
    private int mShadowWidth = 0;

    /**
     * 阴影高度
     */
    private int mShadowHeight = 0;

    /**
     * 显示的文案
     */
    private String mText = null;

    /**
     * 正在加载动画
     */
    private StartLoadingAnimation mStartLoadingAnimation = null;

    /**
     * 加载完成动画
     */
    private StopLoadingAnimation mStopLoadingAnimation = null;

    /**
     * 加载动画进度
     */
    private float mLoading = 0;

    /**
     * 加载完成动画进度
     */
    private float mLoaded = 0;

    /**
     * 组件高度
     */
    private int mHeight = 0;
    /**
     * 组件宽度
     */
    private int mWidth = 0;

    public DepartureView(Context context) {
        this(context, null);
    }

    public DepartureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mscroller=new Scroller(context);
        mSmallCricleRadius = DimenUtil.dip2px(getContext(), 3);
        mBigCircleRadius = DimenUtil.dip2px(getContext(), 11);
        mBigCircleStorkeWidth = DimenUtil.dip2px(getContext(), 0.5f);
        mRectWidth = DimenUtil.dip2px(getContext(), 2);
        mRectHeight = DimenUtil.dip2px(getContext(), 10);
        mTextSize = DimenUtil.dip2px(getContext(), 10);
        mOffestY = DimenUtil.dip2px(getContext(), -15);
        // 阴影初始大小
        mShadowWidth = DimenUtil.dip2px(getContext(), 3);
        mShadowHeight = DimenUtil.dip2px(getContext(), 2);

        // 中心白圈初始化参数
        this.mWhiteCirclePaint = new Paint();
        this.mWhiteCirclePaint.setAntiAlias(true);
        this.mWhiteCirclePaint.setColor(mSmallCircleColor);

        // 大圈画笔初始化参数
        this.mGreenCirclePaint = new Paint();
        this.mGreenCirclePaint.setAntiAlias(true);
        this.mGreenCirclePaint.setColor(mBigCircleColor);

        // 大圈描边画笔初始化参数
        this.mBigCircleStorkePaint = new Paint();
        this.mBigCircleStorkePaint.setAntiAlias(true);
        this.mBigCircleStorkePaint.setColor(mBigCircleStrokeColor);
        this.mBigCircleStorkePaint.setStrokeWidth(mBigCircleStorkeWidth);
        this.mBigCircleStorkePaint.setStyle(Paint.Style.STROKE);

        // 下方小棍画笔初始化参数
        this.mRectPaint = new Paint();
        this.mRectPaint.setAntiAlias(true);
        this.mRectPaint.setColor(mRectColor);

        // 下方小棍画笔初始化参数
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(mTextColor);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setTextSize(mTextSize);

        // 阴影画笔
        this.mShadowPaint = new Paint();
        this.mShadowPaint.setAntiAlias(true);
        this.mShadowPaint.setColor(mShadowColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("zrz2018","onMeasure= type="+ type+"--widthMeasureSpec=="+widthMeasureSpec+"--heightMeasureSpec=="+heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

/*
        // 宽度为大圆直径,高度为大圆直径+竖线高度+半个阴影高度
        if (type == TYPE_LOADED) {
            // 当使用跳动动画时会动态修改高度
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            // 其他状态时固定宽度高度
            if (mHeight == 0) {
                mHeight = mRectHeight + (mBigCircleRadius * 2) + mShadowHeight / 2 + mBigCircleStorkeWidth;
            }=3
            if (mWidth == 0) {
                mWidth = mBigCircleRadius * 2 + mBigCircleStorkeWidth * 2;
            }
            setMeasuredDimension(mWidth-getScrollX(), mHeight-getScrollY());
        }*/
    }


    /**
     * 设置正常状态
     */
    public void setNormal() {
        clearAnimation();
        type = TYPE_NORMAL;
        invalidate();
    }


    /**
     * 设置显示文字
     *
     * @param text
     */
    public void setText(String text) {
        if (!TextUtils.isEmpty(text)) {
            type = TYPE_TEXT;
            // 只显示第一个字
            this.mText = text.substring(0, 1);
            invalidate();
        } else {
            setNormal();
        }

    }

    /**
     * 设置加载动画样式
     */
    public void startLoading() {
        clearAnimation();
        type = TYPE_LOADING;
        mStartLoadingAnimation = new StartLoadingAnimation(this);
        mStartLoadingAnimation.setAnimationListener(new DepartureMarkerAnimationListener());
        startAnimation(mStartLoadingAnimation);
    }

    /**
     * 0-0.5白圈外扩
     * 0.5-1绿圈外扩
     *
     * @param loading
     */
    public void   setLoading(float loading) {
        this.mLoading = loading;
        invalidate();
    }
    /**
     * 平滑的复位
     */
    public void smoothReset() {
        mscroller.startScroll( this.getScrollX(), this .getScrollY(),-getScrollX(),-getScrollY(), 1000);
        invalidate(); //强制重绘 --->draw()--->computeScroll()
    }

    //4.在 invalidate() 执行时，调用如下的方法，在方法内判断是否移动到最终位置，如果没有，继续移动
    @Override
    public void computeScroll() {
        //如果还没有移动到最终位置，则返回值为 true. 反之，返回值为 false ，对应着移动到了最终位置
        if (mscroller.computeScrollOffset()){
            // 小幅度的移动
             Log.d("zrz2018","--computeScroll()--Xmscroller.getCurrX()="+mscroller.getCurrX()+"----mscroller.getCurrY()=="+mscroller.getCurrY()+"left="+this.getLeft());
             scrollTo( mscroller.getCurrX(),mscroller.getCurrY());
            invalidate(); // 强制重绘 --->draw()--->computeScroll()

        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.d("zrz2018","-onScrollChanged");

        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void invalidate() {
        Log.d("zrz2018","-invalidate(");

        super.invalidate();
    }

    public  Scroller getScrooler()
    {
        return mscroller;
    }
     int mlastX = 0;
     int mlasty = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        int disX = x - mlastX;
        int disY = y - mlasty;
        int  translationX= (int) this.getTranslationX() + disX;
        int  translationY= (int) this.getTranslationY() +disY;
       // Log.d("zrz2018","Xx="+x+"----translationX=="+translationX+"left="+this.getLeft());
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                this.setTranslationX(translationX);
                this.setTranslationY(translationY);
                break;

                default: break;
        }

        mlastX = x;
        mlasty = y;

        return true;
    }
    public void setTypeLoaded() {
        type = TYPE_LOADED;
    }
    /**
     * 开启弹跳动画
     */
    public void startJump(final AnimationFinishListener listener) {
        clearAnimation();
        type = TYPE_LOADED;
        mStopLoadingAnimation = new StopLoadingAnimation(this);
        mStopLoadingAnimation.setAnimationListener(
                new DepartureMarkerAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        super.onAnimationEnd(animation);
                        if (listener != null) {
                            listener.onFinish();
                        }
                    }
                });

        startAnimation(mStopLoadingAnimation);
    }

    /**
     * 加载完成动画
     * 0-0.5向上减速
     * 0.5-1向下加速
     *
     * @param loaded
     */
   public  void setLoaded(float loaded) {
        this.mLoaded = loaded;
        // 动态设置高度
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (loaded >= 0 && loaded < 0.5) {
            // 圆形整体向上移动,底部棍和阴影不动
            layoutParams.width = mWidth;
            layoutParams.height = (int) (mHeight + loaded / 0.5f * mRectHeight);
        } else if (loaded >= 0.5 && loaded <= 1) {
            layoutParams.width = mWidth;
            layoutParams.height = (int) (mHeight + (1 - loaded) / 0.5f * mRectHeight);
        }
        setLayoutParams(layoutParams);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("zrz2018","Xx=onDraw=type="+type);

        if (type == TYPE_NORMAL) {
            drawNormal(canvas);
        } else if (type == TYPE_LOADING) {
            drawLoading(canvas);
        } else if (type == TYPE_LOADED) {
            drawLoaded(canvas);
        } else if (type == TYPE_TEXT) {
            drawText(canvas, mText);
        } else {
            drawNormal(canvas);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //layout(left,top-getScrollY(),right,bottom-getScrollY());
        super.onLayout(false, left+100, top-getScrollY(), right, bottom-getScrollY());
        Log.d("zrz2018","onLayout= top-getScrollY()="+ (top-getScrollY())+"--bottom-getScrollY()=="+(bottom-getScrollY()));

    }


    /**
     * 绘制停止时的样式
     *
     * @param canvas
     */
    private void drawNormal(Canvas canvas) {
        Log.d("zrz2018","drawNormal= getHeight()="+ getHeight()+"getScrollY()--"+getScrollY());

        // 绘制阴影
        RectF ovalRectF = new RectF(getWidth() / 2f - mShadowWidth / 2f, getHeight() - mShadowHeight-getScrollY()
                , getWidth() / 2f + mShadowWidth / 2f, getHeight()-getScrollY());
        canvas.drawOval(ovalRectF, mShadowPaint);
        // 绘制下方棍
        canvas.drawRect(getWidth() / 2f - (mRectWidth / 2f), mBigCircleRadius-getScrollY()
                , getWidth() / 2f + (mRectWidth / 2f), getHeight() - mShadowHeight / 2f-getScrollY(), mRectPaint);
        // 绘制内绿圆
        mGreenCirclePaint.setAlpha(255);
        canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth-getScrollY(),
                mBigCircleRadius, mGreenCirclePaint);
        // 绘制空心圆
        canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth-getScrollY(), mBigCircleRadius, mBigCircleStorkePaint);
        // 绘制内白圆
        mWhiteCirclePaint.setAlpha(255);
        canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth-getScrollY(), mSmallCricleRadius, mWhiteCirclePaint);

    }

    /**
     * 绘制加载时的动画
     *
     * @param canvas
     */
    private void drawLoading(Canvas canvas) {
        Log.d("zrz2018","drawLoading");

        if (mLoading < 0) {
            mLoading = 0;
        }
        if (mLoading > 1) {
            mLoading = 1;
        }

        // 绘制阴影
        RectF ovalRectF = new RectF(getWidth() / 2f - mShadowWidth / 2f, getHeight() - mShadowHeight
                , getWidth() / 2f + mShadowWidth / 2f, getHeight());
        canvas.drawOval(ovalRectF, mShadowPaint);
        // 绘制下方棍
        canvas.drawRect(getWidth() / 2f - (mRectWidth / 2f), mBigCircleRadius
                , getWidth() / 2f + (mRectWidth / 2f), getHeight() - mShadowHeight / 2f, mRectPaint);

        if (mLoading >= 0 && mLoading < 0.5) {

            // 绘制背景绿色圆
            mGreenCirclePaint.setAlpha(255);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mGreenCirclePaint);

            // 绘制内渐变大的白圆
            mWhiteCirclePaint.setAlpha(255);
            float offRadius = (mLoading/ 0.5f) * (mBigCircleRadius - mSmallCricleRadius);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mSmallCricleRadius + offRadius, mWhiteCirclePaint);

            // 添加最内部alpha渐变的绿圈
            mGreenCirclePaint.setAlpha((int) (255 * mLoading * 2));
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mSmallCricleRadius
                    , mGreenCirclePaint);
        } else if (mLoading >= 0.5 && mLoading <= 1) {
            // 绘制背景白色圆
            mWhiteCirclePaint.setAlpha(255);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mWhiteCirclePaint);

            // 绘制内渐变大的绿圆
            mGreenCirclePaint.setAlpha(255);
            float offRadius = ((mLoading - 0.5f) / 0.5f) * (mBigCircleRadius - mSmallCricleRadius);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mSmallCricleRadius + offRadius, mGreenCirclePaint);

            // 添加最内部alpha渐变的白圈
            mWhiteCirclePaint.setAlpha((int) (255 * (mLoading - 0.5) * 2));
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mSmallCricleRadius
                    , mWhiteCirclePaint);

        }
        // 绘制空心圆
        canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mBigCircleStorkePaint);


    }

    /**
     * 绘制加载完成时的动画
     *
     * @param canvas
     */
    private void drawLoaded(Canvas canvas) {
        Log.d("zrz2018","drawLoaded");

        if (mLoaded < 0) {
            mLoaded = 0;
        }
        if (mLoaded > 1) {
            mLoaded = 1;
        }
        // 动态修改view 高度
        float offestY = 0;
        // 偏移小棍高度的一半
        if (mLoaded >= 0 && mLoaded < 0.5) {
            // 圆形整体向上移动,底部棍和阴影不动
            // 绘制阴影和下方小棍
            RectF ovalRectF = new RectF(getWidth() / 2f - mShadowWidth / 2f, getHeight() - mShadowHeight
                    , getWidth() / 2f + mShadowWidth / 2f, getHeight());
            canvas.drawOval(ovalRectF, mShadowPaint);
            canvas.drawRect(getWidth() / 2f - (mRectWidth / 2f), mBigCircleRadius
                    , getWidth() / 2f + (mRectWidth / 2f), getHeight() - mShadowHeight / 2f, mRectPaint);
            // 绘制圆
            mGreenCirclePaint.setAlpha(255);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mGreenCirclePaint);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mBigCircleStorkePaint);
            mWhiteCirclePaint.setAlpha(255);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mSmallCricleRadius, mWhiteCirclePaint);
        } else if (mLoaded >= 0.5 && mLoaded <= 1) {
            // 圆形向下移动
            if (mLoaded > 0.5 && mLoaded <= 0.75) {
                // 小棍向上移动
                offestY = (mLoaded - 0.5f) / 0.25f * mRectHeight;
            } else {
                // 小棍向下移动
                offestY = (1f - mLoaded) / 0.25f * mRectHeight;
            }
            // 绘制阴影和小棍
            RectF ovalRectF = new RectF(getWidth() / 2f - mShadowWidth / 2f, getHeight() - mShadowHeight - offestY
                    , getWidth() / 2f + mShadowWidth / 2f, getHeight() - offestY);
            canvas.drawOval(ovalRectF, mShadowPaint);
            canvas.drawRect(getWidth() / 2f - (mRectWidth / 2f), mBigCircleRadius
                    , getWidth() / 2f + (mRectWidth / 2f), getHeight() - mShadowHeight / 2f - offestY, mRectPaint);
            // 绘制圆
            mGreenCirclePaint.setAlpha(255);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mGreenCirclePaint);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mBigCircleStorkePaint);
            mWhiteCirclePaint.setAlpha(255);
            canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mSmallCricleRadius, mWhiteCirclePaint);
        }
    }

    /**
     * 绘制加载完成时的动画
     *
     * @param canvas
     */
    private void drawText(Canvas canvas, String text) {

        // 绘制阴影
        RectF ovalRectF = new RectF(getWidth() / 2f - mShadowWidth / 2f, getHeight() - mShadowHeight
                , getWidth() / 2f + mShadowWidth / 2f, getHeight());
        canvas.drawOval(ovalRectF, mShadowPaint);
        // 绘制下方棍
        canvas.drawRect(getWidth() / 2f - (mRectWidth / 2f), mBigCircleRadius
                , getWidth() / 2f + (mRectWidth / 2f), getHeight() - mShadowHeight / 2f, mRectPaint);
        // 绘制内绿圆
        canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mGreenCirclePaint);
        // 绘制空心圆
        canvas.drawCircle(getWidth() / 2f, mBigCircleRadius + mBigCircleStorkeWidth, mBigCircleRadius, mBigCircleStorkePaint);
        // 矫正y坐标,绘制文字
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float fontTotalHeight = fontMetrics.bottom - fontMetrics.top;
        float offY = fontTotalHeight / 2f - fontMetrics.bottom;
        float newY = mBigCircleRadius + offY;
        canvas.drawText(text, getWidth() / 2f, newY, mTextPaint);
    }

    /**
     * view结束时清除动画
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mStartLoadingAnimation != null) {
            mStartLoadingAnimation.cancel();
            mStartLoadingAnimation = null;
        }

        if (mStartLoadingAnimation != null) {
            mStartLoadingAnimation.cancel();
            mStartLoadingAnimation = null;
        }
    }

    /**
     * 上车点正在加载动画
     */
    static class StartLoadingAnimation extends Animation {

        /**
         * 加载时的动画
         */
        private static int sDuration = 1000;

        private WeakReference<DepartureView> mDepartureMarkerView = null;

        private StartLoadingAnimation(DepartureView departureMarkerView) {
            mDepartureMarkerView = new WeakReference<>(departureMarkerView);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(sDuration);
            setRepeatCount(-1);
            setRepeatMode(ValueAnimator.RESTART);
            setInterpolator(new LinearInterpolator());
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            DepartureView departureMarkerView = mDepartureMarkerView.get();
            if (departureMarkerView != null) {
                departureMarkerView.setLoading(interpolatedTime);
            }

        }
    }

    /**
     * 缓存动画状态动画监听
     */
    static class DepartureMarkerAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    /**
     * 上车点加载完成动画
     */
    static class StopLoadingAnimation extends Animation {

        /**
         * 加载时的动画
         */
        private static int sDuration = 400;

        private WeakReference<DepartureView> mDepartureMarkerView = null;

        private StopLoadingAnimation(DepartureView departureMarkerView) {
            mDepartureMarkerView = new WeakReference<>(departureMarkerView);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(sDuration);
            setRepeatCount(0);
            setInterpolator(new AccelerateInterpolator());
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            DepartureView departureMarkerView = mDepartureMarkerView.get();
            if (departureMarkerView != null) {
                departureMarkerView.setLoaded(interpolatedTime);
            }
        }
    }


    /**
     * 动画结束监听
     */
    public interface AnimationFinishListener {
        void onFinish();
    }


}

