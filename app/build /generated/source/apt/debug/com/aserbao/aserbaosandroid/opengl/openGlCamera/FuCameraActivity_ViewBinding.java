// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.opengl.openGlCamera;

import android.opengl.GLSurfaceView;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FuCameraActivity_ViewBinding<T extends FuCameraActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FuCameraActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mGlSurfaceView = Utils.findRequiredViewAsType(source, R.id.gl_surface_view, "field 'mGlSurfaceView'", GLSurfaceView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mGlSurfaceView = null;

    this.target = null;
  }
}
