package com.study.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonStudyActivity extends AppCompatActivity {

    public String jsonText="{\"hint_text\":\"请描述导航绕路的具体道路\",\"need_description\":1,\"parallel_list\":null,\"report_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_detour_big\",\"report_list_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_detour\",\"report_title\":\"导航绕路\",\"report_type\":\"10000\",\"screen_shot_type\":0,\"update_type\":1}";
    public String jsonTextArray="{\"hint_text\":\"请描述行程起点或终点存在的问题\",\"need_description\":0,\"parallel_list\":[{\"report_list\":[{\"sub_report_title\":\"起点不存在\",\"sub_report_type\":\"10307\"},{\"sub_report_title\":\"终点不存在\",\"sub_report_type\":\"10308\"},{\"sub_report_title\":\"起点名称错误\",\"sub_report_type\":\"10303\"},{\"sub_report_title\":\"终点名称错误\",\"sub_report_type\":\"10305\"},{\"sub_report_title\":\"起点位置错误\",\"sub_report_type\":\"10302\"},{\"sub_report_title\":\"终点位置错误\",\"sub_report_type\":\"10304\"}],\"required\":1,\"title\":\"问题类型\"}],\"report_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_startendpoi_big\",\"report_list_icon\":\"http://img-hxy021.didistatic.com/static/reporticon/report_driver_startendpoi\",\"report_title\":\"起终点问题\",\"report_type\":\"10300\",\"screen_shot_type\":0,\"update_type\":1}\n";
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_json_main);
        textView =findViewById(R.id.text);
        testJson();
    }

    public void testJson(){
        try {
            JSONObject item = new JSONObject(jsonTextArray);
            String hint_tex =item.getString("hint_text");
            JSONArray parallelListArray = item.getJSONArray("parallel_list");
            Log.d("zrz2018","\nparallelItemObject[--parallelListArray.length()="+parallelListArray.length());

            for (int j = 0; j < parallelListArray.length(); j++) {
                JSONObject parallelItemObject = parallelListArray.getJSONObject(j);
                Log.d("zrz2018","\nparallelItemObject["+j+"]=="+parallelItemObject);
                JSONArray eventListArray = parallelItemObject.getJSONArray("report_list");
                for (int  i= 0; i< eventListArray.length(); i++) {
                    Log.d("zrz2018","\neventListArray["+i+"]=="+eventListArray.getJSONObject(i));

                }

                }
            textView.setText(hint_tex);
        }catch (JSONException e){

        }
    }
}
