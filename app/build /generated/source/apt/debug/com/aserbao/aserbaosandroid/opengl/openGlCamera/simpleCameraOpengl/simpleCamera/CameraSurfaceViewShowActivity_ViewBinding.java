// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.SurfaceView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CameraSurfaceViewShowActivity_ViewBinding<T extends CameraSurfaceViewShowActivity> implements Unbinder {
  protected T target;

  private View view2131165268;

  @UiThread
  public CameraSurfaceViewShowActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mSurfaceView = Utils.findRequiredViewAsType(source, R.id.mSurface, "field 'mSurfaceView'", SurfaceView.class);
    view = Utils.findRequiredView(source, R.id.btn_change, "method 'onViewClicked'");
    view2131165268 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mSurfaceView = null;

    view2131165268.setOnClickListener(null);
    view2131165268 = null;

    this.target = null;
  }
}
