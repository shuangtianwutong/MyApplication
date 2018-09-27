package com.study;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.study.jsontest.JsonStudyActivity;
import com.study.jsontest.R;
import com.study.schemeurl.Scheme1Activity;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test分支修改
        //developer 分支修改后
        //developer_self 分支修改后
    }


    public void  onButtonClickJsonTest(View view) {
        Log.i("ansen","onButton1Click(");
        startActivity(new Intent(this.getApplicationContext(),JsonStudyActivity.class));
        //test分支修改
        //master分支修改首次
        //test分支修改2

    }
    /**
     *  scheme ： test
        host ： shangjia
        path ： shangjiaDetail
        query : shangjiaId=222
      */

    public void  onButtonClickSchemrTest(View view) {

           Uri url = Uri.parse("test://shangjia/shangjiaDetail?shagnjiaId=222");
           try {
               startActivity(new Intent(Intent.ACTION_VIEW, url));
           }catch (ActivityNotFoundException e){
               System.out.println("zrz2018nofound");
           }


        //startActivity(new Intent(this.getApplicationContext(),Scheme1Activity.class));

    }
}
