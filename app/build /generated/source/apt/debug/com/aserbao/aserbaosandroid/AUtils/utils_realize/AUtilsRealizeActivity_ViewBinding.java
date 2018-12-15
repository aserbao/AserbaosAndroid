// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AUtils.utils_realize;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AUtilsRealizeActivity_ViewBinding<T extends AUtilsRealizeActivity> implements Unbinder {
  protected T target;

  private View view2131165279;

  @UiThread
  public AUtilsRealizeActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mUtilsResultTv = Utils.findRequiredViewAsType(source, R.id.utils_result_tv, "field 'mUtilsResultTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_get_phone_info, "method 'onViewClicked'");
    view2131165279 = view;
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

    target.mUtilsResultTv = null;

    view2131165279.setOnClickListener(null);
    view2131165279 = null;

    this.target = null;
  }
}
