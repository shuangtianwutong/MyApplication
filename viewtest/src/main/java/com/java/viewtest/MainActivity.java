package com.java.viewtest;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    public void  onProgessBArTest(View view) {
        Log.i("ansen","onButton1Click(");
        startActivity(new Intent(this.getApplicationContext(),progressBaranimationActivity.class));

    }
}
