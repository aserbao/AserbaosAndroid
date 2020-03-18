package com.aserbao.aserbaosandroid.aaThird.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyObserver implements LifecycleObserver {
        private static final String TAG = "MyObserver";

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void connectListener() {
            Log.d(TAG, "connectListener() called");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void disconnectListener() {
            Log.d(TAG, "disconnectListener() called");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            Log.d(TAG, "onStart() called");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop() {
            Log.d(TAG, "onStop() called");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestory() {
            Log.d(TAG, "onDestory() called");
        }
    }