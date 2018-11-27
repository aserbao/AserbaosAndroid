// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.opengl.openGlCamera.recordCamera.ui.AspectTextureView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecordCameraActivity_ViewBinding<T extends RecordCameraActivity> implements Unbinder {
  protected T target;

  private View view2131165293;

  private View view2131165275;

  private View view2131165298;

  private View view2131165278;

  @UiThread
  public RecordCameraActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mTextureView = Utils.findRequiredViewAsType(source, R.id.preview_textureview, "field 'mTextureView'", AspectTextureView.class);
    view = Utils.findRequiredView(source, R.id.btn_start_record, "field 'mBtnStartRecord' and method 'onViewClicked'");
    target.mBtnStartRecord = Utils.castView(view, R.id.btn_start_record, "field 'mBtnStartRecord'", Button.class);
    view2131165293 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_delete_video, "method 'onViewClicked'");
    view2131165275 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_swap_camera, "method 'onViewClicked'");
    view2131165298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_flash_light, "method 'onViewClicked'");
    view2131165278 = view;
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

    target.mTextureView = null;
    target.mBtnStartRecord = null;

    view2131165293.setOnClickListener(null);
    view2131165293 = null;
    view2131165275.setOnClickListener(null);
    view2131165275 = null;
    view2131165298.setOnClickListener(null);
    view2131165298 = null;
    view2131165278.setOnClickListener(null);
    view2131165278 = null;

    this.target = null;
  }
}
