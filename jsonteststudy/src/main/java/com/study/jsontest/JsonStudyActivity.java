package com.study.jsontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JsonStudyActivity extends AppCompatActivity {

    public String jsonText="{\"hint_text\":\"请描述导航绕路的具体道路\",\"need_description\":1,\"parallel_list\":null,\"report_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_detour_big\",\"report_list_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_detour\",\"report_title\":\"导航绕路\",\"report_type\":\"10000\",\"screen_shot_type\":0,\"update_type\":1}";
    public String jsonText2 ="{\"scene_type\": \"1001\",\"extendValue\":{\"dispatchtype\":1,\"parameters\":{\"orderid\":\"XXXXXX\",\"product_id\":260,\"utctime\":1538016479, \"infotype\": 2,\"maxnum\":50}}}";
    public String jsonText1="{\"dispatchtype\":1,\"parameters\":{\"orderid\":\"XXXXXX\",\"product_id\":260,\"utctime\":1538016479, \"infotype\": 2,\"maxnum\":50}}\"}";
    public String jsonTextArray="{\"hint_text\":\"请描述行程起点或终点存在的问题\",\"need_description\":0,\"parallel_list\":[{\"report_list\":[{\"sub_report_title\":\"起点不存在\",\"sub_report_type\":\"10307\"},{\"sub_report_title\":\"终点不存在\",\"sub_report_type\":\"10308\"},{\"sub_report_title\":\"起点名称错误\",\"sub_report_type\":\"10303\"},{\"sub_report_title\":\"终点名称错误\",\"sub_report_type\":\"10305\"},{\"sub_report_title\":\"起点位置错误\",\"sub_report_type\":\"10302\"},{\"sub_report_title\":\"终点位置错误\",\"sub_report_type\":\"10304\"}],\"required\":1,\"title\":\"问题类型\"}],\"report_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_startendpoi_big\",\"report_list_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_startendpoi\",\"report_title\":\"起终点问题\",\"report_type\":\"10300\",\"screen_shot_type\":0,\"update_type\":1}\n";
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_json_main);
        textView =findViewById(R.id.text);
         Log.d("zrz2018","zrz2018");
        testJson();
         //testSerializable(getIntent());

    }

      public void testSerializable(Intent intent)   {
         TestBean bean = (TestBean)intent.getSerializableExtra("testBean");
         Log.d("zrz2018","zrz2018--bean=="+bean);
         //test girrypick---1

      }




    public void testJson(){
        try {
            JSONObject  jsonobject2 = new  JSONObject(jsonText2);
            JSONObject extendValue = jsonobject2.getJSONObject("extendValue");
            Log.d("zrz2018","iextendValue="+extendValue.toString());
            JSONObject item = new  JSONObject(extendValue.toString());;
            String dispatchtype =item.getString("dispatchtype");
            JSONObject item1=item.getJSONObject("parameters");
            String proid= item1.getString("product_id");
            String utctime= item1.getString("utctime");
            Iterator iterator = item1.keys();
            boolean a=item1.has("maxnum");
            boolean b=item1.isNull("maxnum");
            boolean c=item1.has("maxnum1");
            boolean d=item1.isNull("maxnum1");

            if(item1.has("maxnum") && !item1.isNull("maxnum")){
                   Log.d("zrz2018","maxnu="+item1.getString("maxnum"));
            }

            int length = item1.length();



           ArrayList<String> keys = new ArrayList<String>();
            while (iterator.hasNext())
            {
                String key = (String) iterator.next();
                keys.add(key);
                Log.d("zrz2018","iterator-"+key+" :=="+item1.getString(key));
            }





            //JSONArray parallelListArray = item.getJSONArray("parameters");

            Log.d("zrz2018","dispatchtyp--"+dispatchtype+"--utctime="+utctime+"--proid-="+proid);
            Log.d("zrz2018","dispatchtyp=1 ====-"+(Integer.parseInt(dispatchtype)==1));
          /* for (int j = 0; j < parallelListArray.length(); j++) {
          /* for (int j = 0; j < parallelListArray.length(); j++) {
                JSONObject parallelItemObject = parallelListArray.getJSONObject(j);
                Log.d("zrz2018","\nparallelItemObject["+j+"]=="+parallelItemObject);
                JSONArray eventListArray = parallelItemObject.getJSONArray("report_list");
                for (int  i= 0; i< eventListArray.length(); i++) {
                    Log.d("zrz2018","\neventListArray["+i+"]=="+eventListArray.getJSONObject(i));

                }

                }*/
           textView.setText(extendValue.toString());
        }catch (JSONException e){
            Log.d("zrz2018","zrz2018e");
        }
    }
}
