package com.java.viewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.java.widget.MyImageView;

/*
规则：左加右减，上加下减

1. 瞬间移动视图的内容 : 利用 View的 scroll 方法
    1). scrollBy(int x, int y) : 滑动指定的偏移量 (从当前位置瞬间 )
           x: x 轴上的偏移量 , x>0内容向左滑动 , x<0 内容向右滑动 , x=0 水平方向不滑动
           y: y 轴上的偏移量 , y>0内容向上滑动 , y<0 内容向下滑动 , y=0 垂直方向不滑动
     2). scrollTo(int x, int y) : 滑动到指定的偏移量 ( 从当前位置瞬间 )//scrollTo(int x, int y) 是将View中内容滑动到相应的位置，参考的坐标系原点为parent View的左上角。
           x: 目标位置 x轴上的偏移量 , x>0 移动到原始位置的左侧 , x<0移动到原始位置的右侧 ,x=0 移动到水平原始位置


           y: 目标位置 y轴上的偏移量 , y>0 移动到原始位置的上侧 , y<0移动到原始位置的下侧 ,y=0 移动到垂直原始位置
区别：by ：每次执行都会移动
     to ：如果参数是死的，那么每次执行只能移动到固定位置

2. 平滑移动视图的内容 : 利用 Scroller的 startScroll 方法和 View 的scrollTo 方法
    1). Scroller 是实现 View平滑移动的帮助类 , 它本身并不能实现对 View 的移动
    2). 平滑移动的基本原理 : 将整个从起始位置到结束位置的移动分解成多个小的距离 , 循环调用 scrollTo() 实现平滑移动
    3). 相关API:
         a. Scoller 类:
         -->Scoller(Context context) : 创建对象的构造方法
         -->startScroll(int startX, int startY, int dx, int dy, int duration) : 开始平滑移动视图 ( 这个方法本身不会产生滑动 )
               startX : 起始位置的 X 偏移量
               startY : 起始位置的 Y 偏移量
               dx: 滑动多大的 X 偏移量 (如果是 0,X 方向不会滑动 )
               dy: 滑动多大的 Y 偏移量 (如果是 0,Y 方向不会滑动 )
             duration : 整个过程持续的时间 (ms)
      -->startScroll(int startX, int startY, int dx, int dy): 开始平滑移动视图 ( 时间为 250ms)
      -->boolean computeScrollOffset() : 计算当前移动的偏移量 , 并将其保存到 Scoller对象中 , 如果滑动还没有完成返回 true
      -->int getCurrX() : 得到计算出的 X偏移量
      -->int getCurrY() : 得到计算出的 Y偏移量
b. View类
    -->invalidate() : 强制重绘 , 导致 draw()-->computeScroll()
          在scoller.startScroll() 后必须执行此方法
    -->computeScroll() : 需要重写此方法 , 用于计算移动 , 此方法在 draw() 中调用
                    调用 scoller 计算移动偏移量
                    调用 view 对象 scrollTo()到计算出的偏移量
                    调用 View 对象 invalidate()强制重绘 , 导致 computeScroll()再次执行
*/
public class ScrollerTestActivity extends AppCompatActivity {
    private static final String TAG = "ScrollerTestActivity201";
    MyImageView iv_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_test);
        iv_main = (MyImageView) findViewById(R.id.iv_main );
        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScrollerTestActivity.this, "onclick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 向左移动 20px
    public void scrollLeft(View v) {
        Log. i(TAG, "getScrollX 的值值值值值！！！！！ ()" + iv_main.getScrollX());
        iv_main.scrollBy( 20, 0); //scrollBy(): 指明移动的幅度。
        Log. i( TAG, " 第二次 getScrollX 的值值值值值！！！！！ ()" + iv_main .getScrollX());

    }

    // 向右移动 20px
    public void scrollRight(View v) {
//        iv_main.scrollBy(-20,0);
        Log. i(TAG, "getScrollX 的值值值值值！！！！！ ()" + iv_main.getScrollX()+"---iv_main.getX()=="+iv_main.getX()+"iv_main.getleft()"+iv_main.getLeft()+"iv_main.ggetTranslationX())"+iv_main.getTranslationX());
        //通过 log 可以得出 scrollto 的第一个参数代表移动到自身和所要移动的距离。如果固定写死。那么无论点击多少次
        /* 都还是那样 */
        iv_main.scrollTo( iv_main.getScrollX() - 20, iv_main .getScrollY()); //参数 2 可以写为 0
        Log. i( TAG, " 第二次 getScrollX 的值值值值值！！！！！ ()" + iv_main .getScrollX()+"---iv_main.getX()=="+iv_main.getX()+"iv_main.getleft()"+"iv_main.ggetTranslationX())"+iv_main.getTranslationX());
    }

    // 向上移动 20px
    public void scrollUp(View v) {
        iv_main.scrollBy( 0, 20);
    }

    // 向下移动 20px
    public void scrollDown(View v) {
        iv_main.scrollTo( iv_main.getScrollX(), iv_main .getScrollY() - 20 );

    }

    //瞬间复位
    public void reset1(View v) {
//        iv_main.scrollBy(-iv_main.getScrollX(),-iv_main.getScrollY());
        iv_main.scrollTo( 0, 0);

    }
    //平滑复位
    public void reset2(View v) {
        iv_main.smoothReset();

    }



}
