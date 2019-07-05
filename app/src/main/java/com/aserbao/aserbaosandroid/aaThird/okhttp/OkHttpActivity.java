package com.aserbao.aserbaosandroid.aaThird.okhttp;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/7/5 11:29 AM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.okhttp
 */
public class OkhttpActivity extends BaseRecyclerViewActivity {
    private static final String TAG = "OkhttpActivity";
    private OkHttpClient mHttpClient;

    @Override
    public void initGetData() {
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp简单的异步Get请求"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp简单的同步Get请求"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp实现Post请求"));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("添加请求头的Get请求"));
        init();
    }

    private void init() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                    .header("User-Agent", "aserbao/(study okhttp)")         // 设置头部代理
                    .header("Client-Language", Locale.getDefault().getLanguage())
                    .method(original.method(), original.body())
                    .build();
                return chain.proceed(request);
            }
        };

        mHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)                  //设置拦截器
            .connectTimeout(10, TimeUnit.SECONDS) //设置建立连接最大可用时间，适用于网络状况正常的情况下，两端连接所用的最长时间。默认值为10s
            .writeTimeout(20, TimeUnit.SECONDS)   //设置单个IO写入超时时长，默认值10s
            .readTimeout(20, TimeUnit.SECONDS)    //设置读取超时时间，默认值为10s。
            .cache(new Cache(new File(Environment.getExternalStorageDirectory(), "cache"), 10 * 1024 * 1024))//设置缓存区地址及大小
            .build();
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                asynSimpleOkhttpGet();
                break;
            case 1:
                syncSimpleOkhttpGet();
                break;
            case 2:
                simpleOkhttpPost();
                break;
            case 3:
                addHeadOkhttpGet();
                break;
        }
    }
       public static final String sUrl = "https://cn.bing.com/";
//    public static final String sUrl = "http://gank.io/api/today ";
    /**
     * 简单的异步Get请求
     * 步骤：
     * 1. 构造OkHttpClient客户端
     * 2. 构造Request
     * 3. 执行newCall，回调请求结果
     */
    public void asynSimpleOkhttpGet(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(sUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.body().string() );
            }
        });
    }

    /**
     * 简单的GET同步请求
     */
    public void syncSimpleOkhttpGet(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder().url(sUrl).build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()){
                        Log.e(TAG, "successful: " + response.body().string() );
                    }else{
                        Log.e(TAG, "onFailure: " + response.body().string() );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    /**
     * 简单的post请求
     * 步骤：
     * 1. 添加Post请求参数，构造 FormBody
     * 2. 构造OkHttpClient客户端
     * 3. 构造Request
     * 4. 执行newCall，回调请求结果
     */
    public void asynSimpleOkhttpPost(){
        //1. 添加Post请求参数，构造 FormBody
        FormBody body = new FormBody.Builder()
            .add("url", "https://www.jianshu.com/p/53083f782ea2")
            .add("desc", "一篇好文之Android数据库GreenDao的使用完全解析")
            .add("who", "aserbao")
            .add("type", "Android")
            .add("debug", "false")
            .build();
        String postUrl = "https://gank.io/api/add2gank";
        //2. 构造OkHttpClient客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //3. 构造Request
        Request request = new Request.Builder().post(body).url(postUrl).build();
        //4. 执行newCall，回调请求结果
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.body().string() );
            }
        });
    }

    public void syncSimpleOkhttpPost(){
        //1. 添加Post请求参数，构造 FormBody
        FormBody body = new FormBody.Builder()
            .add("url", "https://www.jianshu.com/p/53083f782ea2")
            .add("desc", "一篇好文之Android数据库GreenDao的使用完全解析")
            .add("who", "aserbao")
            .add("type", "Android")
            .add("debug", "false")
            .build();
        String postUrl = "https://gank.io/api/add2gank";
        //2. 构造OkHttpClient客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //3. 构造Request
        Request request = new Request.Builder().post(body).url(postUrl).build();
        //4. 执行newCall，回调请求结果
        try {
            Response response = okHttpClient.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 添加请求头的Get请求
     */
    public void addHeadOkhttpGet(){
        Request request = new Request.Builder()
            .url(sUrl)
            .header("User-Agent", "OkHttp Headers.java")         // 设置头部代理
            .build();

        String header = request.header("User-Agent");
        Log.e(TAG, "addHeadOkhttpGet: " + header );
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e(TAG, "onFailure: " );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()){
                    if (!response.isSuccessful()) throw new IOException(" sorry， the get request is fail");
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.e(TAG, "\n onResponse: name = " + responseHeaders.name(i) + " values = " +  responseHeaders.value(i));
                    }
                    Log.e(TAG, "\n onResponse: User-Agent = " + response.header("User-Agent") );
                    Log.e(TAG, "onResponse: " + responseBody.string() );
                }
            }
        });
    }



}
