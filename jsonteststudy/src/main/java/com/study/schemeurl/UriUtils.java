package com.study.schemeurl;

import android.content.Context;
import android.net.Uri;

import java.security.PublicKey;

/**
 * @author :created  by  zhangrongzhao on  2018/09/2018/9/21
 */
public class UriUtils {
    private static final UriUtils ourInstance = new UriUtils();

    public static UriUtils getInstance() {
        return ourInstance;
    }

    public Uri getUri() {
        Uri uri = new  Uri.Builder().scheme("https")
                .authority("baidu.com")
                .path("page")
                .appendQueryParameter("name","zhangsan")
                .appendQueryParameter("sex","女").build();
        //String a = uri.toString();
        //System.out.println(" uri2a:" +  a);
          uri=Uri.parse(uri.toString()).buildUpon().appendQueryParameter("shengao","170").build();
         return uri;
    }



}
