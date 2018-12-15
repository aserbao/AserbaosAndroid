// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.sensors;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SharkActivity_ViewBinding<T extends SharkActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SharkActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mMainShakeTop = Utils.findRequiredViewAsType(source, R.id.main_shake_top, "field 'mMainShakeTop'", ImageView.class);
    target.mMainShakeTopLine = Utils.findRequiredViewAsType(source, R.id.main_shake_top_line, "field 'mMainShakeTopLine'", ImageView.class);
    target.mMainLinearTop = Utils.findRequiredViewAsType(source, R.id.main_linear_top, "field 'mMainLinearTop'", LinearLayout.class);
    target.mMainShakeBottomLine = Utils.findRequiredViewAsType(source, R.id.main_shake_bottom_line, "field 'mMainShakeBottomLine'", ImageView.class);
    target.mMainShakeBottom = Utils.findRequiredViewAsType(source, R.id.main_shake_bottom, "field 'mMainShakeBottom'", ImageView.class);
    target.mMainLinearBottom = Utils.findRequiredViewAsType(source, R.id.main_linear_bottom, "field 'mMainLinearBottom'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mMainShakeTop = null;
    target.mMainShakeTopLine = null;
    target.mMainLinearTop = null;
    target.mMainShakeBottomLine = null;
    target.mMainShakeBottom = null;
    target.mMainLinearBottom = null;

    this.target = null;
  }
}
