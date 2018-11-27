// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.fullScreen;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OneEditTextFullScreenActivity_ViewBinding<T extends OneEditTextFullScreenActivity> implements Unbinder {
  protected T target;

  private View view2131165277;

  private View view2131165276;

  @UiThread
  public OneEditTextFullScreenActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mImageView = Utils.findRequiredViewAsType(source, R.id.imageView, "field 'mImageView'", ImageView.class);
    target.mBtnShow = Utils.findRequiredViewAsType(source, R.id.btn_show, "field 'mBtnShow'", Button.class);
    target.mAutoRl = Utils.findRequiredViewAsType(source, R.id.auto_rl, "field 'mAutoRl'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_do_something, "method 'onViewClicked'");
    view2131165277 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_do_second, "method 'onViewClicked'");
    view2131165276 = view;
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

    target.mImageView = null;
    target.mBtnShow = null;
    target.mAutoRl = null;

    view2131165277.setOnClickListener(null);
    view2131165277 = null;
    view2131165276.setOnClickListener(null);
    view2131165276 = null;

    this.target = null;
  }
}
