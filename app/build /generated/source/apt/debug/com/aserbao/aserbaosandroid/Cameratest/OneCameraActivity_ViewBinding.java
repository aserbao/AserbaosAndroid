// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.Cameratest;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OneCameraActivity_ViewBinding<T extends OneCameraActivity> implements Unbinder {
  protected T target;

  @UiThread
  public OneCameraActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mAutoFitTextureView = Utils.findRequiredViewAsType(source, R.id.auto_fit_texture_view, "field 'mAutoFitTextureView'", AutoFitTextureView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mAutoFitTextureView = null;

    this.target = null;
  }
}
