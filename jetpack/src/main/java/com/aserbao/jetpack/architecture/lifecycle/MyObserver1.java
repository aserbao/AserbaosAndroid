package com.aserbao.jetpack.architecture.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyObserver1 implements LifecycleObserver {
        private static final String TAG = "MyObserver1";

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            Log.d(TAG, "onResume() called");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
            Log.d(TAG, "onPause() called");
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