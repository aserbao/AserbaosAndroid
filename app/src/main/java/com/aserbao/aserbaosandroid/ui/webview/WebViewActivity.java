package com.aserbao.aserbaosandroid.ui.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.base.base.BaseRecyclerViewActivity;
import com.example.base.base.beans.BaseRecyclerBean;
import com.example.base.utils.screen.DisplayUtil;

public class WebViewActivity extends BaseRecyclerViewActivity {
    WebView webView;
    @Override
    public void initGetData() {
        initWeb();
//        mBaseRecyclerBean.add(new BaseRecyclerBean("JS的使用",WebViewActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("调用js方法",0));
    }


    public void initWeb(){
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie("https://ctf.aserbao.net/", "flag=123");
//        setContentView(webView);

        String url = "";
//        url = "file:///android_asset/simplejs.html";
        url = "https://cloudapi.bytedance.net/faas/services/ttjnfc/invoke/easyandroid";
        webView = new WebView(getApplicationContext());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        int dip300 = DisplayUtil.dip2px(300);
        addViewHToFl(webView,false,false,dip300,dip300,true);
    }


    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {
        switch (position){
            case 0:
                testJs();
                break;
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void testJs(){
        webView.loadUrl("javascript:test()");
    }

    @JavascriptInterface
    public void hello(String msg){
        Toast.makeText(this, "憨批", Toast.LENGTH_SHORT).show();
    }
}