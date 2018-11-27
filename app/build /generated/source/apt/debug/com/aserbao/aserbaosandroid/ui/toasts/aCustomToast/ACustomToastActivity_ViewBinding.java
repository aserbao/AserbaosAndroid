// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.toasts.aCustomToast;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ACustomToastActivity_ViewBinding<T extends ACustomToastActivity> implements Unbinder {
  protected T target;

  private View view2131165449;

  private View view2131165576;

  @UiThread
  public ACustomToastActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iamge_btn, "method 'onViewClicked'");
    view2131165449 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.second_btn, "method 'onViewClicked'");
    view2131165576 = view;
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
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131165449.setOnClickListener(null);
    view2131165449 = null;
    view2131165576.setOnClickListener(null);
    view2131165576 = null;

    this.target = null;
  }
}
