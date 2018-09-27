package com.java.viewtest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
   private ViewGroup mlinerLayoutTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlinerLayoutTest = (LinearLayout)findViewById(R.id.linerlayouttest);
        //test developer1
    }

    public void  onButtonClickJsonTest(View view) {
        Log.i("ansen","onButton1Click(");
        startActivity(new Intent(this.getApplicationContext(),ViewTestActivity.class));

    }
    /*
     *  scheme ： test
     host ： shangjia
     path ： shangjiaDetail
     query : shangjiaId=222
     */

    public void  onButtonClickSchemrTest(View view) {


        startActivity(new Intent(this.getApplicationContext(),ScrollerTestActivity.class));
        //     //test developer1 test2

    }

    public void onShapeTest(View view) {
        GradientDrawable p = (GradientDrawable) mlinerLayoutTest.getBackground();
        p.setColor(Color.parseColor("#ebebeb"));

    }

    public void  onProgessBArTest(View view) {
        Log.i("ansen","onButton1Click(");
        startActivity(new Intent(this.getApplicationContext(),progressBaranimationActivity.class));

    }
}
