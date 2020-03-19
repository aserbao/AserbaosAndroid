package com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight;

import android.app.assist.AssistContent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;

import java.util.List;

/**
 * 主要功能:
 * author aserbao
 * date : On 2018/9/12
 * email: this is empty email
 */
public class CustomActivity extends AppCompatActivity {
    private static final String TAG = "CustomActivity";
    String result = "";

    @Override
    public void onContentChanged() {
        result = result + "\n Call method onContentChanged";
        Log.d(TAG, "onContentChanged() called");
        super.onContentChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        result = result + "\n Call method onSaveInstanceState";
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        result = result + "\n Call method dispatchKeyEvent";
        Log.d(TAG, "dispatchKeyEvent() called with: event = [" + event + "]");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        result = result + "\n Call method onKeyDown";
        Log.d(TAG, "onKeyDown() called with: keyCode = [" + keyCode + "], event = [" + event + "]");
        return super.onKeyDown(keyCode, event);
    }

    //FragmentActivity
    @Override
    public void supportFinishAfterTransition() {
        result = result + "\n Call method supportFinishAfterTransition";
        Log.d(TAG, "supportFinishAfterTransition() called");
        super.supportFinishAfterTransition();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        result = result + "\n Call method onMultiWindowModeChanged";
        Log.d(TAG, "onMultiWindowModeChanged() called with: isInMultiWindowMode = [" + isInMultiWindowMode + "]");
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        result = result + "\n Call method onPictureInPictureModeChanged";
        Log.d(TAG, "onPictureInPictureModeChanged() called with: isInPictureInPictureMode = [" + isInPictureInPictureMode + "]");
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        result = result + "\n Call method onNewIntent";
        Log.d(TAG, "onNewIntent() called with: intent = [" + intent + "]");
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        result = result + "\n Call method onResume";
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    //Activity

    @Override
    public void onLocalVoiceInteractionStarted() {
        Log.d(TAG, "onLocalVoiceInteractionStarted() called");
        super.onLocalVoiceInteractionStarted();
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        Log.d(TAG, "onProvideAssistData() called with: data = [" + data + "]");
        super.onProvideAssistData(data);
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        Log.d(TAG, "onProvideAssistContent() called with: outContent = [" + outContent + "]");
        super.onProvideAssistContent(outContent);
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        Log.d(TAG, "onProvideKeyboardShortcuts() called with: data = [" + data + "], menu = [" + menu + "], deviceId = [" + deviceId + "]");
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent() called with: ev = [" + ev + "]");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTrackballEvent() called with: ev = [" + ev + "]");
        return super.dispatchTrackballEvent(ev);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchGenericMotionEvent() called with: ev = [" + ev + "]");
        return super.dispatchGenericMotionEvent(ev);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "dispatchPopulateAccessibilityEvent() called with: event = [" + event + "]");
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    @Override
    public void startIntentSender(IntentSender intent, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        Log.d(TAG, "startIntentSender() called with: intent = [" + intent + "], fillInIntent = [" + fillInIntent + "], flagsMask = [" + flagsMask + "], flagsValues = [" + flagsValues + "], extraFlags = [" + extraFlags + "]");
        super.startIntentSender(intent, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        Log.d(TAG, "overridePendingTransition() called with: enterAnim = [" + enterAnim + "], exitAnim = [" + exitAnim + "]");
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        Log.d(TAG, "onActivityReenter() called with: resultCode = [" + resultCode + "], data = [" + data + "]");
        super.onActivityReenter(resultCode, data);
    }


    @Nullable
    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        Log.d(TAG, "startActionMode() called with: callback = [" + callback + "]");
        return super.startActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode startActionMode(ActionMode.Callback callback, int type) {
        Log.d(TAG, "startActionMode() called with: callback = [" + callback + "], type = [" + type + "]");
        return super.startActionMode(callback, type);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        Log.d(TAG, "onWindowStartingActionMode() called with: callback = [" + callback + "]");
        return super.onWindowStartingActionMode(callback);
    }

    // ==========================ContextThemeWrapper
}
