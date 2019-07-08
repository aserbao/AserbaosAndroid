package com.aserbao.aserbaosandroid.aaThird.okhttp;

import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.AUtils.utils.AppFileMgr;
import com.aserbao.aserbaosandroid.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.base.beans.BaseRecyclerBean;
import com.aserbao.aserbaosandroid.commonData.StaticFinalValues;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

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
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp实现Get同步请求 ",0));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp实现Get异步请求 ",1));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp实现Post同步请求 ",2));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp实现Post异步请求 ",3));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp添加请求头请求 ",4));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp上传String到服务器 ",5));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp流上传 ",6));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp文件上传 ",7));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("通过moshi将json解析成对象 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp通过拦截设置缓存 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp官方推荐的缓存方式 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp取消回调 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp配置超时 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp修改超时配置 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp认证处理 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp设置应用拦截 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp设置网络拦截 "));
        mBaseRecyclerBeen.add(new BaseRecyclerBean("okhttp压缩请求主体 "));
        init();
    }

    private void init() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                    .header("User-Agent", "aserbao/(study okhttp)")
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
            .cache(new Cache(getCacheDir(), 10 * 1024 * 1024))//设置缓存区地址及大小
            .build();
    }

    @Override
    public void itemClickBack(View view, int position) {
        switch (position){
            case 0:
                syncSimpleOkhttpGet();
                break;
            case 1:
                asynSimpleOkhttpGet();
                break;
            case 2:
                syncSimpleOkhttpPost();
                break;
            case 3:
                asynSimpleOkhttpPost();
                break;
            case 4:
                addHeadOkhttpGet();
                break;
            case 5:
                postAString();
                break;
            case 6:
                postStreaming();
                break;
            case 7:
                postFile();
                break;
            case 8:
                parseJsonByMoshi();
                break;
            case 9:
                setCache();
                break;
            case 10:
                useOfficalCache();
                break;
            case 11:
                cancelCall();
                break;
            case 12:
                configTimeouts();
                break;
            case 13:
                changeTimeoutsConfig();
                break;
            case 14:
                handlingAuthentication();
                break;
            case 15:
                addApplicationInterceptor();
                break;
            case 16:
                addNetworkInterceptor();
                break;
            case 17:
                setGzipIntercrptor();
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
     * Post同步请求
     */
    public void syncSimpleOkhttpPost(){
        //1. 添加Post请求参数，构造 FormBody
        FormBody body = new FormBody.Builder()
            .add("url", "https://www.jianshu.com/p/53083f782ea2")
            .add("desc", "一篇好文之Android数据库GreenDao的使用完全解析")
            .add("who", "aserbao")
            .add("type", "Android")
            .add("debug", "true")
            .build();
        String postUrl = "https://gank.io/api/add2gank";
        //2. 构造OkHttpClient客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //3. 构造Request
                    Request request = new Request.Builder().post(body).url(postUrl).build();
                    //4. 执行newCall，回调请求结果
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.e(TAG, "successful: " + response.body().string());
                    } else {
                        Log.e(TAG, "onFailure: " + response.body().string());
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
            .add("debug", "true")
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

    /*
     * 添加请求头
     */
    public void addHeadOkhttpGet(){
        Request request = new Request.Builder()
            .url(sUrl)
            .header("Client-Language", Locale.getDefault().getLanguage())
            .header("Client-Language", Locale.getDefault().getLanguage())
            .addHeader("Cookie", "Cookie1")
            .addHeader("Cookie", "Cookie2")
            .build();

        StringBuffer sb = new StringBuffer();
        List<String> headers = request.headers("Cookie");
        for (String header : headers) {
            sb.append(header).append("  ");
        }
        String header1 = request.header("Client-Language");
        Log.e(TAG, "Cookie = " + sb.toString()  + " \n Client-Language = " + header1);
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
                    Headers responseHeaders = response.headers();       //headers()方法
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.e(TAG, "\n onResponse: name = " + responseHeaders.name(i) + " values = " +  responseHeaders.value(i));
                    }
                    StringBuffer sb = new StringBuffer();
                    List<String> headers = response.headers("set-cookie");  //Headers方法
                    for (String header : headers) {
                        sb.append("set-cookie的值为：").append(header).append("\n");
                    }
                    Log.e(TAG, "\nonResponse: set-cookie == " + sb.toString()  + "data == " +  response.header("date")); // header方法
                }
            }
        });
    }

    /**
     * 上传String到服务器
     */
    public void postAString(){
        OkHttpClient client = new OkHttpClient();
        String postBody = " this is a custom markdown porfile txt";

        Request request = new Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), postBody))
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Log.e(TAG, "successful: " + response.body().string() );
                }else{
                    Log.e(TAG, "onFailure: " + response.body().string() );
                }
            }
        });
    }


    /**
     * post 流
     */
    public void postStreaming(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 1; i <= 100; i++) {
                    sink.writeUtf8(String.format(" 上传第%d个数\n", i));
                }
            }
        };

        Request request = new Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(requestBody)
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Log.e(TAG, "successful: " + response.body().string() );
                }else{
                    Log.e(TAG, "onFailure: " + response.body().string() );
                }
            }
        });
    }

    /**
     * 文件上传
     */
    public void postFile(){
        OkHttpClient client = new OkHttpClient();
        File file = new File(AppFileMgr.getSdCardAbsolutePath() +"aserbao.txt");
        Request request = new Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file))
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Log.e(TAG, "successful: " + response.body().string() );
                }else{
                    Log.e(TAG, "onFailure: " + response.body().string() );
                }
            }
        });
    }


    /**
     * 通过moshi解析response返回的json成对象
     */
    private void parseJsonByMoshi() {
        final OkHttpClient client = new OkHttpClient();
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

        Request request = new Request.Builder()
            .url("https://api.github.com/gists/c2a7c39532239ff261be")
            .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "  );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Gist gist = gistJsonAdapter.fromJson(response.body().source());
                for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
                    Log.e(TAG, "onResponse: key = " + entry.getKey()  + " value =  " + entry.getValue().content);
                }
            }
        });
    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }


    /**
     * 服务器不支持缓存时的配置
     */
    private void setCache(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new CacheInterceptor())
            .build();
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

    public class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                //配置缓存有效时长为3天
                .header("Cache-Control", "max-age=" + 3600 * 24 * 3)
                .build();
            return response1;
        }
    }

    /**
     * okhttp官方推荐的缓存方式
     */
    public void useOfficalCache(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new CacheInterceptor())
            .build();
        CacheControl cacheControl = new CacheControl.Builder()
//            .noCache()                           //不接受响应缓存
//            .noStore()                          //不存储任何响应缓存
//            .onlyIfCached()                     //只有缓存中存在这个响应时才返回，否则会返回504
            .maxAge(3, TimeUnit.DAYS)  //配置缓存有效时长为3天，注意，maxAge最小精度单位为SECONDS，再小就会丢失。
//            .maxStale(2,TimeUnit.DAYS)        //设置最大陈旧度值
//            .minFresh(2,TimeUnit.DAYS)//
            .build();
        Request request = new Request.Builder()
            .url(sUrl)
            .cacheControl(cacheControl)
            .build();
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
     * okhttp取消回调
     */
    public void cancelCall(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .build();
        Request request = new Request.Builder()
            .url("http://httpbin.org/delay/3")//这个接口会延时3s返回
            .build();

        final long startNanos = System.nanoTime();
        final Call call = okHttpClient.newCall(request);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(new Runnable() {
            @Override public void run() {
                String startTime = String.format("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
                Log.e(TAG, "run: startTime = " +startTime );
                call.cancel();
                String endTime = String.format("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f);
                Log.e(TAG, "run: endTime = " + endTime );
            }
        }, 1, TimeUnit.SECONDS);    //1s之后取消请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "run: onFailure = time =  " + String.format("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f) );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.body().string());
                Log.e(TAG, "run: onResponse = time =  " + String.format("%.2f Canceling call.%n", (System.nanoTime() - startNanos) / 1e9f) );
            }
        });
    }


    /**
     * 配置超时时间
     */
    public void configTimeouts(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) //设置建立连接最大可用时间，适用于网络状况正常的情况下，两端连接所用的最长时间。默认值为10s
            .writeTimeout(10, TimeUnit.SECONDS)   //设置单个IO写入超时时长，默认值10s
            .readTimeout(3, TimeUnit.SECONDS)    //设置读取超时时间，默认值为10s。
            .build();
        Request request = new Request.Builder()
            .url("http://httpbin.org/delay/5")//这个接口会延时5s返回
            .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    Log.e(TAG, "successful: " + response.body().string() );
                }else{
                    Log.e(TAG, "onFailure: " + response.body().string() );
                }
            }
        });
    }

    /**
     * 修改超时配置
     */
    public void changeTimeoutsConfig(){
        Request request = new Request.Builder()
            .url("http://httpbin.org/delay/2")//这个接口会延时2s返回
            .build();

        new OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.SECONDS)    //设置读取超时时间，默认值为10s。
            .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "successful: " + response.body().string() );
            }
        });
        new OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)    //设置读取超时时间，默认值为10s。
            .build().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "onFailure: " + e.toString() );
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e(TAG, "successful: " + response.body().string() );
                }
            });
    }


    /**
     * 认证处理
     */
    public void handlingAuthentication(){
        Request request = new Request.Builder()
            .url("http://publicobject.com/secrets/hellosecret.txt")
            .build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .authenticator(new Authenticator() {
                @Override public Request authenticate(Route route, Response response) throws IOException {
                    if (response.request().header("Authorization") != null) {
                        return null; // 返回null，放弃重新认证。当然这里条件可以自己添加，比如允许验证三次，就添加一个变量，当大于3的时候返回null
                    }
                    Log.e(TAG, "Authenticating for response: " + response);
                    Log.e(TAG, "Challenges: " + response.challenges());
                    String credential = Credentials.basic("jesse", "password1");
                    return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
                }
            })
            .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "successful: " + response.body().string() );
            }
        });
    }


    /**
     * 设置简单拦截
     */
    public void addApplicationInterceptor(){
        Request request = new Request.Builder()
            .url("http://www.publicobject.com/helloworld.txt")
            .header("User-Agent", "OkHttp Example")
            .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "successful: " + response.body().string() );
            }
        });
    }
    class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.e(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.e(TAG, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }

    /**
     * okhttp设置网络拦截器
     */
    public void addNetworkInterceptor(){
        Request request = new Request.Builder()
            .url("http://www.publicobject.com/helloworld.txt")
            .header("User-Agent", "OkHttp Example")
            .build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new LoggingInterceptor())
            .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "successful: " + response.body().string() );
            }
        });
    }

    /**
     * okhttp压缩请求主体
     */
    public void setGzipIntercrptor(){
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new GzipRequestInterceptor())
            .build();
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 1; i <= 100; i++) {
                    sink.writeUtf8(String.format(" 上传第%d个数\n", i));
                }
            }
        };
        Request request = new Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(requestBody)
            .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.toString() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "successful: " + response.body().string() );
            }
        });
    }

    /** This interceptor compresses the HTTP request body. Many webservers can't handle this! */
    final class GzipRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
                return chain.proceed(originalRequest);
            }

            RequestBody requestBody = originalRequest.body();
            RequestBody gzipBody = gzip(requestBody);
            Log.e(TAG, "intercept: 原RequestBody = " + requestBody.toString() + " \n 压缩后的RequestBody = " + gzipBody.toString());
            Request compressedRequest = originalRequest.newBuilder()
                .header("Content-Encoding", "gzip")
                .method(originalRequest.method(), gzipBody)
                .build();

            return chain.proceed(compressedRequest);
        }

        private RequestBody gzip(final RequestBody body) {
            return new RequestBody() {
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public long contentLength() {
                    return -1; // We don't know the compressed length in advance!
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
        }
    }
}


