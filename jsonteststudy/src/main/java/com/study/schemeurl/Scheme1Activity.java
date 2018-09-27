package com.study.schemeurl;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.jsontest.R;

public class Scheme1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme1);
        parseSchemUrl(getIntent());
    }

    public void parseSchemUrl(Intent intent){
        String action = intent.getAction();
        String scheme = intent.getScheme();
        Uri uri = intent.getData();
        System.out.println("action:" + action);
        System.out.println("scheme:" + scheme);
        if (uri != null) {
            String host = uri.getHost();
            String dataString = intent.getDataString();
            String id = uri.getQueryParameter("id");
            String name = String.valueOf(uri.getQueryParameters("name"));
            String age = uri.getQueryParameter("age");
            String path = uri.getPath();
            String path1 = uri.getEncodedPath();
            String queryString = uri.getQuery();
            System.out.println("host:" + host);
            System.out.println("dataString:" + dataString);
            System.out.println("id:" + id);
            System.out.println("name:" + name);
            System.out.println("age:" + age);
            System.out.println("path:" + path);
            System.out.println("path1:" + path1);
            System.out.println("queryString:" + queryString);
        }
        uri=uri.buildUpon().appendQueryParameter("test","testw").
                appendQueryParameter("test1","").build();
        System.out.println(" uri:" +  uri);
        System.out.println(" uri2:" +  UriUtils.getInstance().getUri());

    }
}
