// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.customView;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.countdownView.CountDownView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomViewActivity_ViewBinding<T extends CustomViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public CustomViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mCustomTainer = Utils.findRequiredViewAsType(source, R.id.custom_tainer, "field 'mCustomTainer'", FrameLayout.class);
    target.mCountDownView = Utils.findRequiredViewAsType(source, R.id.custom_count_down_view, "field 'mCountDownView'", CountDownView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mCustomTainer = null;
    target.mCountDownView = null;

    this.target = null;
  }
}
