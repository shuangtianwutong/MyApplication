package com.java.viewtest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.java.widget.DepartureView;

public class ViewTestActivity extends AppCompatActivity {

    private  static final String TAG = " ViewTestActivity";
    DepartureView departureView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        departureView =findViewById(R.id.departure_center);
    }

    // 向左移动 20px
    public void scrollLeft(View v) {
        //departureView.scrollBy(20,0);
        //departureView.startLoading();

        ValueAnimator animator =ValueAnimator.ofInt(0,1).setDuration(4000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float f=animation.getAnimatedFraction();
                departureView.scrollTo((int)(100*f),0);
            }
        });
        animator.start();

        Log. i(TAG, "zrz2018--getScrollX 的值值值值值！！！！！ ()" + departureView.getScrollX());
        //departureView.invalidate();

    }

    public void scrollRight(View v) {
        ObjectAnimator.ofFloat(departureView,"translationX",0,100).setDuration(3000).start();
        /*departureView.startJump(new DepartureView.AnimationFinishListener() {
            @Override
            public void onFinish() {
                Log. i(TAG, "zrz2018--onfinish()" );

            }
        });*/

        //通过 log 可以得出 scrollto 的第一个参数代表移动到自身和所要移动的距离。如果固定写死。那么无论点击多少次
        /* 都还是那样 */
    }

    // 向上移动 20px
    public void scrollUp(View v) {
        departureView.scrollBy(0,20);
        //departureView.requestLayout();
    }

    // 向下移动 20px
    public void scrollDown(View v) {
        departureView.scrollBy(0,-20);
        //departureView.requestLayout();

    }

    //平滑复位
    public void reset2(View v) {
        Log. i(TAG, "zrz2018--getScrollX 的值值值值值！！！！！ ()" + departureView.getScrollX());
        departureView.smoothReset();

    }
}
