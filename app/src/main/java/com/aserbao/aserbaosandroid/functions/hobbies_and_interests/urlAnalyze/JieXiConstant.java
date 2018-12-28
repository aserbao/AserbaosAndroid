package com.aserbao.aserbaosandroid.functions.hobbies_and_interests.urlAnalyze;

import android.content.Context;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static java.net.Proxy.Type.HTTP;

/**
 * 功能:
 * author aserbao
 * date : On 2018/12/26
 * email: 1142803753@qq.com
 */
public class JieXiConstant {
    private static String URL_HEAD;
    private static String APP_KEY = "";

    public static void getVideoJiexiUrl(Context context, String Url, int Type) throws Exception {
        switch (Type){
            case 0:
                URL_HEAD = "https://api.lylares.com/video/douying/?";
                break;
            case 1:
                URL_HEAD = "https://api.lylares.com/video/kuaishou/?";
                break;
            case 2:
                URL_HEAD = "https://api.lylares.com/video/weishi/?";
                break;
        }
        String KUAISHOU_URL = URL_HEAD + "AppKey=" + APP_KEY + "&url=" + Url;
        try {
            HttpGet request = new HttpGet(KUAISHOU_URL);
            HttpClient httpClient = getNewHttpClient();
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                Gson gson = new Gson();
                /*if (Type == weishi) {
                    weiShiDetail = gson.fromJson(result, WeiShiDetail.class);
                    EventBus.getDefault().postSticky(new VideoUrlMessageEvent(weiShiDetail, 2));
                } else if (Type == kuaishou) {
                    kuaiShouDetail = gson.fromJson(result, KuaiShouDetail.class);
                    EventBus.getDefault().postSticky(new VideoUrlMessageEvent(kuaiShouDetail, 1));
                } else if (Type == douyin) {
                    douYinDetail = gson.fromJson(result, DouYinDetail.class);
                    EventBus.getDefault().postSticky(new VideoUrlMessageEvent(douYinDetail, 0));
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
            SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, StandardCharsets.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
    static class SSLSocketFactoryEx extends SSLSocketFactory {

        SSLContext sslContext = SSLContext.getInstance("TLS");

        public SSLSocketFactoryEx(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain, String authType)
                        throws java.security.cert.CertificateException {

                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain, String authType)
                        throws java.security.cert.CertificateException {

                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port,
                                   boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port,
                    autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }
}
