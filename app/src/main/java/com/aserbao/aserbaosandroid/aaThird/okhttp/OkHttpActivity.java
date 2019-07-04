package com.aserbao.aserbaosandroid.aaThird.okhttp;

import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "OkHttpActivity";
    private OkHttpClient mClient;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("Okhttp的简单网络请求"));
        initOkhttp();
    }

    private void initOkhttp() {
        mClient = new OkHttpClient();
      /*  new OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)//链接超时时间设置
            .writeTimeout(20);    //设置新连接的默认写入超时时间。值0表示没有超时，否则在转换为毫秒时，值必须介于1和{@link Integer#MAX_VALUE}之间
        mClient.connectTimeoutMillis();*/
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestOkhttp();
                    }
                }).start();
                break;
        }
    }
//    String  requesUrl = "http://gank.io/api/today ";
    String  requesUrl = "https://cn.bing.com/";
    public void requestOkhttp(){
        Request.Builder builder = new Request.Builder();
        builder.header("Authorization","aserbao")
            .header("Client-Language","CN")
            .header("User-Agent","your agent");
        Request request = builder.url(requesUrl).build();
        try {
            Response response = mClient.newCall(request).execute();
            String string = response.body().string();
            Log.e(TAG, "requestOkhttp: " + string );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    String sJson = "{name: json在线编辑器}";
    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse(("application/json; charset=utf-8")), json);
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
        try (Response response = mClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
