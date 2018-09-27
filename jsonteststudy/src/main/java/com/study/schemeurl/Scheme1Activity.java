package com.study.schemeurl;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.study.jsontest.R;

import java.net.URLDecoder;

public class Scheme1Activity extends AppCompatActivity {
    public static final String WEBAPP_TRAFIC_REPORT_URL = "https://poi.map.xiaojukeji.com/sugreport";//h5方案用来填写sug页新增上报的url
    public static final String WEBAPP_TRAFIC_REPORT_ADD_FRAGMENT = "/new";//h5方案用来填写sug页新增上报的Uri的Fragement部分
    public static final String WEBAPP_TRAFIC_REPORT_WRONG_FRAGMENT = "/modify";//h5方案用来填写sug页报错的的url的Fragement部分
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme1);
        textView =  (TextView)findViewById(R.id.text);
        getUrlFromParmeter();
        //parseSchemUrl(getIntent());
    }

    public void getUrlFromParmeter(){
        Uri.Builder mBuilder = Uri.parse(WEBAPP_TRAFIC_REPORT_URL).buildUpon();
        //mBuilder =mBuilder.appendEncodedPath("");
        //Log.d("zrz2018","1-mBuilder.build()="+mBuilder.build().toString());
        //mBuilder = mBuilder.appendEncodedPath(WEBAPP_TRAFIC_REPORT_ADD_PATH);
        mBuilder = mBuilder.appendQueryParameter("test","1223");
        mBuilder = mBuilder.appendQueryParameter("id","4556");
        mBuilder = mBuilder.encodedFragment(WEBAPP_TRAFIC_REPORT_ADD_FRAGMENT);
       // urlStr  =urlStr.contains("#")?urlStr.replace("#", "%23"):urlStr;
       // urlStr  =urlStr.contains("%23")?urlStr.replace("%23", "#"):urlStr;
       /* String urlStr = mBuilder.build().toString() ;
        urlStr = urlStr+"#new";
        Uri uri =Uri.parse(urlStr);*/

        textView.setText(mBuilder.build().toString());
        Log.d("zrz2018","2-mBuilder.build()="+mBuilder.build().toString());
       // Log.d("zrz2018","4-mBuilder.build()="+uri);

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
