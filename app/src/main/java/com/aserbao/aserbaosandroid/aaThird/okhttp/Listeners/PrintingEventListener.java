package com.aserbao.aserbaosandroid.aaThird.okhttp.Listeners;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class PrintingEventListener extends EventListener {
    private static final String TAG = "PrintingEventListener";
    long callId= 0;
    private long callStartNanos;

    public static final Factory FACTORY = new Factory() {
        final AtomicLong nextCallId = new AtomicLong(1L);

        @Override public EventListener create(Call call) {
            long callId = nextCallId.getAndIncrement();
            System.out.printf("%04d %s%n", callId, call.request().url());
            return new PrintingEventListener(callId, System.nanoTime());
        }
    };

    public PrintingEventListener() {
        this.callId = 0;
    }

    public PrintingEventListener(long callId, long callStartNanos) {
        this.callId = callId;//使用的时候将callId设置为final
        this.callStartNanos = callStartNanos;
    }

    private void printEvent(String name) {
        long nowNanos = System.nanoTime();
        if (name.equals("callStart")) {
            callStartNanos = nowNanos;
        }
        long elapsedNanos = nowNanos - callStartNanos;
        Log.e(TAG, name + "\t\t\t\tprintEvent：\t\t" + callId + "耗时:" + elapsedNanos / 1000000000d );
    }

    @Override
    public void callEnd(@NotNull Call call) {
        printEvent("callEnd");
    }

    @Override
    public void callFailed(@NotNull Call call, @NotNull IOException ioe) {
        printEvent("callFailed");
    }

    @Override
    public void callStart(@NotNull Call call) {
        printEvent("callStart");
    }

    @Override
    public void connectEnd(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol) {
        printEvent("connectEnd");
    }

    @Override
    public void connectFailed(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, @NotNull IOException ioe) {
        printEvent("connectFailed");
    }

    @Override
    public void connectStart(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy) {
        printEvent("connectStart");
    }

    @Override
    public void connectionAcquired(@NotNull Call call, @NotNull Connection connection) {
        printEvent("connectAcquired");
    }

    @Override
    public void connectionReleased(@NotNull Call call, @NotNull Connection connection) {
        printEvent("connectReleased");
    }

    @Override
    public void dnsEnd(@NotNull Call call, @NotNull String domainName, @NotNull List<InetAddress> inetAddressList) {
        printEvent("dnsEnd");
    }

    @Override
    public void dnsStart(@NotNull Call call, @NotNull String domainName) {
        printEvent("dnsStart");
    }

    @Override
    public void requestBodyEnd(@NotNull Call call, long byteCount) {
        printEvent("requestBodyEnd");
    }

    @Override
    public void requestBodyStart(@NotNull Call call) {
        printEvent("requestBodyStart");
    }

    @Override
    public void requestFailed(@NotNull Call call, @NotNull IOException ioe) {
        printEvent("requestFailed");
    }

    @Override
    public void requestHeadersEnd(@NotNull Call call, @NotNull Request request) {
        printEvent("requestHeadersEnd");
    }

    @Override
    public void requestHeadersStart(@NotNull Call call) {
        printEvent("requestHeadersStart");
    }

    @Override
    public void responseBodyEnd(@NotNull Call call, long byteCount) {
        printEvent("responseBodyEnd");
    }

    @Override
    public void responseBodyStart(@NotNull Call call) {
        printEvent("responseBodyStart");
    }

    @Override
    public void responseFailed(@NotNull Call call, @NotNull IOException ioe) {
        printEvent("responseFailed");
    }

    @Override
    public void responseHeadersEnd(@NotNull Call call, @NotNull Response response) {
        printEvent("responseHeadersEnd");
    }

    @Override
    public void responseHeadersStart(@NotNull Call call) {
        printEvent("responseHeadersStart");
    }

    @Override
    public void secureConnectEnd(@NotNull Call call, @Nullable Handshake handshake) {
        printEvent("secureConnectEnd");
    }

    @Override
    public void secureConnectStart(@NotNull Call call) {
        printEvent("secureConnectStart");
    }
}
