// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.opengl.openGlCamera.simpleCameraOpengl.simpleCamera;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.TextureView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CameraTextureViewShowActivity_ViewBinding<T extends CameraTextureViewShowActivity> implements Unbinder {
  protected T target;

  private View view2131165300;

  @UiThread
  public CameraTextureViewShowActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mCameraTextureView = Utils.findRequiredViewAsType(source, R.id.camera_texture_view, "field 'mCameraTextureView'", TextureView.class);
    view = Utils.findRequiredView(source, R.id.btn_texture_anim, "method 'onViewClicked'");
    view2131165300 = view;
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

    target.mCameraTextureView = null;

    view2131165300.setOnClickListener(null);
    view2131165300 = null;

    this.target = null;
  }
}
