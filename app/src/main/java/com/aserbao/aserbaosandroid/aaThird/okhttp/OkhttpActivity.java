package com.aserbao.aserbaosandroid.aaThird.okhttp;

import android.util.Log;
import android.view.View;

import com.example.base.utils.others.AppFileMgr;
import com.aserbao.aserbaosandroid.aaThird.okhttp.Listeners.PrintingEventListener;
import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
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
import okhttp3.TlsVersion;
import okio.Buffer;
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
        mBaseRecyclerBean.add(new BaseRecyclerBean("测试 ",1000));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp实现Get同步请求 ",0));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp实现Get异步请求 ",1));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp实现Post同步请求 ",2));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp实现Post异步请求 ",3));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp添加请求头请求 ",4));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp上传String到服务器 ",5));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp流上传 ",6));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp文件上传 ",7));
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过moshi将json解析成对象 ",8));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp通过拦截设置缓存 ",9));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp官方推荐的缓存方式 ",10));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp取消回调 ",11));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp配置超时 ",12));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp修改超时配置 ",13));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp认证处理 ",14));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp设置应用拦截 ",15));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp设置网络拦截 ",16));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp压缩请求主体 ",17));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp配置证书锁 ",18));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp自定义认证证书 ",19));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp事件监听 ",20));
        mBaseRecyclerBean.add(new BaseRecyclerBean("okhttp事件工厂类 ",201));
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
            .cache(new Cache(getCacheDir(), 10 * 1024 * 1024))//设置缓存区地址及大小
            .build();
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 1000:
                testHttp();
                break;
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
            case 18:
                certificatePin();
                break;
            case 19:
                customCertificate();
                break;
            case 20:
                useSimpleEvent();
                break;
            case 201:
                userEventFactory();
                break;
        }
    }


    private void testHttp(){
        RequestBody body = RequestBody.create( MediaType.parse("application/json; charset=utf-8"), "{\"Text\": \"你好你好\", \"Tone\": 0}");
        Request request = new Request.Builder()
            .url("https://cv-tob.bytedance.com/chimera/api/v1/animation/avatar_bs")
                .header("Content-Type"," application/json")
//                .addHeader("AccountId","123")
            .post(body)
            .build();
        Log.d(TAG, "requestText: body =" + body.toString() + "Content-Type header= ");
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e){
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                Headers responseHeaders = response.headers();       //headers()方法
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    Log.e(TAG, "\n onResponse: name = " + responseHeaders.name(i) + " values = " +  responseHeaders.value(i));
                }
                String responseBody = response.body().string();
            }
        });
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


    public void userHttps(){
        OkHttpClient client = new OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
            .build();

        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();

        OkHttpClient client1 = new OkHttpClient.Builder()
            .connectionSpecs(Collections.singletonList(spec))
            .build();
    }


    /**
     * 证书锁定
     */
    public void certificatePin(){
        OkHttpClient client = new OkHttpClient.Builder()
            .certificatePinner(new CertificatePinner.Builder()
                .add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
                .build())
            .build();

        Request request = new Request.Builder()
            .url("https://publicobject.com/robots.txt")
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " +e.toString() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                for (Certificate certificate : response.handshake().peerCertificates()) {
                    Log.e(TAG, "onResponse: " + CertificatePinner.pin(certificate) );
                }
            }
        });
    }


    /**
     * 自定义认证证书
     */
    public void customCertificate(){
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        try {
            trustManager = trustManagerForCertificates(trustedCertificatesInputStream());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        OkHttpClient client = new OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustManager)
            .build();

        Request request = new Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " +e.toString() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.e(TAG, "onResponse: " +  responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                Log.e(TAG, "onResponse: " + response.body().string() );
            }
        });
    }

    /**
     * Returns an input stream containing one or more certificate PEM files. This implementation just
     * embeds the PEM files in Java strings; most applications will instead read this from a resource
     * file that gets bundled with the application.
     */
    private InputStream trustedCertificatesInputStream() {
        // PEM files for root certificates of Comodo and Entrust. These two CAs are sufficient to view
        // https://publicobject.com (Comodo) and https://squareup.com (Entrust). But they aren't
        // sufficient to connect to most HTTPS sites including https://godaddy.com and https://visa.com.
        // Typically developers will need to get a PEM file from their organization's TLS administrator.
        String comodoRsaCertificationAuthority = ""
            + "-----BEGIN CERTIFICATE-----\n"
            + "MIIF2DCCA8CgAwIBAgIQTKr5yttjb+Af907YWwOGnTANBgkqhkiG9w0BAQwFADCB\n"
            + "hTELMAkGA1UEBhMCR0IxGzAZBgNVBAgTEkdyZWF0ZXIgTWFuY2hlc3RlcjEQMA4G\n"
            + "A1UEBxMHU2FsZm9yZDEaMBgGA1UEChMRQ09NT0RPIENBIExpbWl0ZWQxKzApBgNV\n"
            + "BAMTIkNPTU9ETyBSU0EgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkwHhcNMTAwMTE5\n"
            + "MDAwMDAwWhcNMzgwMTE4MjM1OTU5WjCBhTELMAkGA1UEBhMCR0IxGzAZBgNVBAgT\n"
            + "EkdyZWF0ZXIgTWFuY2hlc3RlcjEQMA4GA1UEBxMHU2FsZm9yZDEaMBgGA1UEChMR\n"
            + "Q09NT0RPIENBIExpbWl0ZWQxKzApBgNVBAMTIkNPTU9ETyBSU0EgQ2VydGlmaWNh\n"
            + "dGlvbiBBdXRob3JpdHkwggIiMA0GCSqGSIb3DQEBAQUAA4ICDwAwggIKAoICAQCR\n"
            + "6FSS0gpWsawNJN3Fz0RndJkrN6N9I3AAcbxT38T6KhKPS38QVr2fcHK3YX/JSw8X\n"
            + "pz3jsARh7v8Rl8f0hj4K+j5c+ZPmNHrZFGvnnLOFoIJ6dq9xkNfs/Q36nGz637CC\n"
            + "9BR++b7Epi9Pf5l/tfxnQ3K9DADWietrLNPtj5gcFKt+5eNu/Nio5JIk2kNrYrhV\n"
            + "/erBvGy2i/MOjZrkm2xpmfh4SDBF1a3hDTxFYPwyllEnvGfDyi62a+pGx8cgoLEf\n"
            + "Zd5ICLqkTqnyg0Y3hOvozIFIQ2dOciqbXL1MGyiKXCJ7tKuY2e7gUYPDCUZObT6Z\n"
            + "+pUX2nwzV0E8jVHtC7ZcryxjGt9XyD+86V3Em69FmeKjWiS0uqlWPc9vqv9JWL7w\n"
            + "qP/0uK3pN/u6uPQLOvnoQ0IeidiEyxPx2bvhiWC4jChWrBQdnArncevPDt09qZah\n"
            + "SL0896+1DSJMwBGB7FY79tOi4lu3sgQiUpWAk2nojkxl8ZEDLXB0AuqLZxUpaVIC\n"
            + "u9ffUGpVRr+goyhhf3DQw6KqLCGqR84onAZFdr+CGCe01a60y1Dma/RMhnEw6abf\n"
            + "Fobg2P9A3fvQQoh/ozM6LlweQRGBY84YcWsr7KaKtzFcOmpH4MN5WdYgGq/yapiq\n"
            + "crxXStJLnbsQ/LBMQeXtHT1eKJ2czL+zUdqnR+WEUwIDAQABo0IwQDAdBgNVHQ4E\n"
            + "FgQUu69+Aj36pvE8hI6t7jiY7NkyMtQwDgYDVR0PAQH/BAQDAgEGMA8GA1UdEwEB\n"
            + "/wQFMAMBAf8wDQYJKoZIhvcNAQEMBQADggIBAArx1UaEt65Ru2yyTUEUAJNMnMvl\n"
            + "wFTPoCWOAvn9sKIN9SCYPBMtrFaisNZ+EZLpLrqeLppysb0ZRGxhNaKatBYSaVqM\n"
            + "4dc+pBroLwP0rmEdEBsqpIt6xf4FpuHA1sj+nq6PK7o9mfjYcwlYRm6mnPTXJ9OV\n"
            + "2jeDchzTc+CiR5kDOF3VSXkAKRzH7JsgHAckaVd4sjn8OoSgtZx8jb8uk2Intzna\n"
            + "FxiuvTwJaP+EmzzV1gsD41eeFPfR60/IvYcjt7ZJQ3mFXLrrkguhxuhoqEwWsRqZ\n"
            + "CuhTLJK7oQkYdQxlqHvLI7cawiiFwxv/0Cti76R7CZGYZ4wUAc1oBmpjIXUDgIiK\n"
            + "boHGhfKppC3n9KUkEEeDys30jXlYsQab5xoq2Z0B15R97QNKyvDb6KkBPvVWmcke\n"
            + "jkk9u+UJueBPSZI9FoJAzMxZxuY67RIuaTxslbH9qh17f4a+Hg4yRvv7E491f0yL\n"
            + "S0Zj/gA0QHDBw7mh3aZw4gSzQbzpgJHqZJx64SIDqZxubw5lT2yHh17zbqD5daWb\n"
            + "QOhTsiedSrnAdyGN/4fy3ryM7xfft0kL0fJuMAsaDk527RH89elWsn2/x20Kk4yl\n"
            + "0MC2Hb46TpSi125sC8KKfPog88Tk5c0NqMuRkrF8hey1FGlmDoLnzc7ILaZRfyHB\n"
            + "NVOFBkpdn627G190\n"
            + "-----END CERTIFICATE-----\n";
        String entrustRootCertificateAuthority = ""
            + "-----BEGIN CERTIFICATE-----\n"
            + "MIIEkTCCA3mgAwIBAgIERWtQVDANBgkqhkiG9w0BAQUFADCBsDELMAkGA1UEBhMC\n"
            + "VVMxFjAUBgNVBAoTDUVudHJ1c3QsIEluYy4xOTA3BgNVBAsTMHd3dy5lbnRydXN0\n"
            + "Lm5ldC9DUFMgaXMgaW5jb3Jwb3JhdGVkIGJ5IHJlZmVyZW5jZTEfMB0GA1UECxMW\n"
            + "KGMpIDIwMDYgRW50cnVzdCwgSW5jLjEtMCsGA1UEAxMkRW50cnVzdCBSb290IENl\n"
            + "cnRpZmljYXRpb24gQXV0aG9yaXR5MB4XDTA2MTEyNzIwMjM0MloXDTI2MTEyNzIw\n"
            + "NTM0MlowgbAxCzAJBgNVBAYTAlVTMRYwFAYDVQQKEw1FbnRydXN0LCBJbmMuMTkw\n"
            + "NwYDVQQLEzB3d3cuZW50cnVzdC5uZXQvQ1BTIGlzIGluY29ycG9yYXRlZCBieSBy\n"
            + "ZWZlcmVuY2UxHzAdBgNVBAsTFihjKSAyMDA2IEVudHJ1c3QsIEluYy4xLTArBgNV\n"
            + "BAMTJEVudHJ1c3QgUm9vdCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTCCASIwDQYJ\n"
            + "KoZIhvcNAQEBBQADggEPADCCAQoCggEBALaVtkNC+sZtKm9I35RMOVcF7sN5EUFo\n"
            + "Nu3s/poBj6E4KPz3EEZmLk0eGrEaTsbRwJWIsMn/MYszA9u3g3s+IIRe7bJWKKf4\n"
            + "4LlAcTfFy0cOlypowCKVYhXbR9n10Cv/gkvJrT7eTNuQgFA/CYqEAOwwCj0Yzfv9\n"
            + "KlmaI5UXLEWeH25DeW0MXJj+SKfFI0dcXv1u5x609mhF0YaDW6KKjbHjKYD+JXGI\n"
            + "rb68j6xSlkuqUY3kEzEZ6E5Nn9uss2rVvDlUccp6en+Q3X0dgNmBu1kmwhH+5pPi\n"
            + "94DkZfs0Nw4pgHBNrziGLp5/V6+eF67rHMsoIV+2HNjnogQi+dPa2MsCAwEAAaOB\n"
            + "sDCBrTAOBgNVHQ8BAf8EBAMCAQYwDwYDVR0TAQH/BAUwAwEB/zArBgNVHRAEJDAi\n"
            + "gA8yMDA2MTEyNzIwMjM0MlqBDzIwMjYxMTI3MjA1MzQyWjAfBgNVHSMEGDAWgBRo\n"
            + "kORnpKZTgMeGZqTx90tD+4S9bTAdBgNVHQ4EFgQUaJDkZ6SmU4DHhmak8fdLQ/uE\n"
            + "vW0wHQYJKoZIhvZ9B0EABBAwDhsIVjcuMTo0LjADAgSQMA0GCSqGSIb3DQEBBQUA\n"
            + "A4IBAQCT1DCw1wMgKtD5Y+iRDAUgqV8ZyntyTtSx29CW+1RaGSwMCPeyvIWonX9t\n"
            + "O1KzKtvn1ISMY/YPyyYBkVBs9F8U4pN0wBOeMDpQ47RgxRzwIkSNcUesyBrJ6Zua\n"
            + "AGAT/3B+XxFNSRuzFVJ7yVTav52Vr2ua2J7p8eRDjeIRRDq/r72DQnNSi6q7pynP\n"
            + "9WQcCk3RvKqsnyrQ/39/2n3qse0wJcGE2jTSW3iDVuycNsMm4hH2Z0kdkquM++v/\n"
            + "eu6FSqdQgPCnXEqULl8FmTxSQeDNtGPPAUO6nIPcj2A781q0tHuu2guQOHXvgR1m\n"
            + "0vdXcDazv/wor3ElhVsT/h5/WrQ8\n"
            + "-----END CERTIFICATE-----\n";
        return new Buffer()
            .writeUtf8(comodoRsaCertificationAuthority)
            .writeUtf8(entrustRootCertificateAuthority)
            .inputStream();
    }

    /**
     * Returns a trust manager that trusts {@code certificates} and none other. HTTPS services whose
     * certificates have not been signed by these certificates will fail with a {@code
     * SSLHandshakeException}.
     *
     * <p>This can be used to replace the host platform's built-in trusted certificates with a custom
     * set. This is useful in development where certificate authority-trusted certificates aren't
     * available. Or in production, to avoid reliance on third-party certificate authorities.
     *
     * <p>See also {@link CertificatePinner}, which can limit trusted certificates while still using
     * the host platform's built-in trust store.
     *
     * <h3>Warning: Customizing Trusted Certificates is Dangerous!</h3>
     *
     * <p>Relying on your own trusted certificates limits your server team's ability to update their
     * TLS certificates. By installing a specific set of trusted certificates, you take on additional
     * operational complexity and limit your ability to migrate between certificate authorities. Do
     * not use custom trusted certificates in production without the blessing of your server's TLS
     * administrator.
     */
    private X509TrustManager trustManagerForCertificates(InputStream in)
        throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Event的使用
     */
    private void useSimpleEvent() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder
            .eventListener(new PrintingEventListener())
            .build();
        Request request = new Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "REQUEST 1 (new connection)"  );
                try (Response response = client.newCall(request).execute()) {
                    // Consume and discard the response body.
                    response.body().source().readByteString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e(TAG, "REQUEST 2 (pooled connection)"  );
                try (Response response = client.newCall(request).execute()) {
                    // Consume and discard the response body.
                    response.body().source().readByteString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * EventFactory
     */
    private void userEventFactory() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder
            .eventListenerFactory(PrintingEventListener.FACTORY)//重点就这一句
            .build();
        Request request = new Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build();

        Log.e(TAG, "REQUEST 1 (new connection)"  );
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: "+e.toString() );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e(TAG, "onResponse: new connection " + response.body().source().readByteString() );

            }
        });

        Log.e(TAG, "REQUEST 2 (pooled connection)"  );
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: "+e.toString() );
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e(TAG, "onResponse pool: " + response.body().source().readByteString() );
            }
        });
    }


}


