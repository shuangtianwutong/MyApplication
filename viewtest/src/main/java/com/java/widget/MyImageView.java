package com.java.widget;

import android.content.Context;
import android.util.AttributeSet;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Scroller;

public class MyImageView extends android.support.v7.widget.AppCompatImageView {

    //1.声明 Scroller 类的对象
    private Scroller scroller;

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //2.构造器中实例化 Scroller
        scroller = new Scroller(context);
    }

    /**
     * 平滑的复位
     */
    public void smoothReset() {
           /*
          3. 调用scroller.startScroll(), 指明移动的参数
                   参数 1 ，参数 2 ：指明当前视图移动的距离
                   参数 3 ，参数 4 ：指明当前视图要移动的距离
                   参数 5 ：指明移动结束需要花费的时间

           */

        scroller.startScroll( this.getScrollX(), this .getScrollY(),-getScrollX(),-getScrollY(), 1000);
        invalidate(); //强制重绘 --->draw()--->computeScroll()
    }

    //4.在 invalidate() 执行时，调用如下的方法，在方法内判断是否移动到最终位置，如果没有，继续移动
    @Override
    public void computeScroll() {
        //如果还没有移动到最终位置，则返回值为 true. 反之，返回值为 false ，对应着移动到了最终位置
        if ( scroller.computeScrollOffset()){
            // 小幅度的移动
            scrollTo( scroller.getCurrX(), scroller .getCurrY());
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
        Log.d("zrz2018", "-invalidate(");
        super.invalidate();
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top-getScrollY(), right, bottom-getScrollY());
        Log.d("zrz2018","onLayout= right="+ right);

    }
}
