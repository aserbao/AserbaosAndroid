// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.opengl.GLSurfaceView;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CameraGlSurfaceShowActivity_ViewBinding<T extends CameraGlSurfaceShowActivity> implements Unbinder {
  protected T target;

  private View view2131165280;

  private View view2131165281;

  @UiThread
  public CameraGlSurfaceShowActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mCameraGlsurfaceView = Utils.findRequiredViewAsType(source, R.id.camera_glsurface_view, "field 'mCameraGlsurfaceView'", GLSurfaceView.class);
    view = Utils.findRequiredView(source, R.id.btn_gl_surface_view_animator, "method 'onViewClicked'");
    view2131165280 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_gl_surface_view_switch, "method 'onViewClicked'");
    view2131165281 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mCameraGlsurfaceView = null;

    view2131165280.setOnClickListener(null);
    view2131165280 = null;
    view2131165281.setOnClickListener(null);
    view2131165281 = null;

    this.target = null;
  }
}
