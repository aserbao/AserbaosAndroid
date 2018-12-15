// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.blur;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageBlurActivity_ViewBinding<T extends ImageBlurActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ImageBlurActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mBlurIamgeView = Utils.findRequiredViewAsType(source, R.id.blur_iamge_view, "field 'mBlurIamgeView'", ImageView.class);
    target.mStartBlurBtn = Utils.findRequiredViewAsType(source, R.id.start_blur_btn, "field 'mStartBlurBtn'", Button.class);
    target.mBlurScaleTv = Utils.findRequiredViewAsType(source, R.id.blur_scale_tv, "field 'mBlurScaleTv'", TextView.class);
    target.mBlurScaleSeekBar = Utils.findRequiredViewAsType(source, R.id.blur_scale_seek_bar, "field 'mBlurScaleSeekBar'", SeekBar.class);
    target.mBlurRadiusTv = Utils.findRequiredViewAsType(source, R.id.blur_radius_tv, "field 'mBlurRadiusTv'", TextView.class);
    target.mBlurRadiusSeekBar = Utils.findRequiredViewAsType(source, R.id.blur_radius_seek_bar, "field 'mBlurRadiusSeekBar'", SeekBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mBlurIamgeView = null;
    target.mStartBlurBtn = null;
    target.mBlurScaleTv = null;
    target.mBlurScaleSeekBar = null;
    target.mBlurRadiusTv = null;
    target.mBlurRadiusSeekBar = null;

    this.target = null;
  }
}
