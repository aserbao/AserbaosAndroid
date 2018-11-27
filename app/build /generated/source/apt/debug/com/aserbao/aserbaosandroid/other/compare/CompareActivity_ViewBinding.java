// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.other.compare;

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

public class CompareActivity_ViewBinding<T extends CompareActivity> implements Unbinder {
  protected T target;

  private View view2131165600;

  private View view2131165601;

  @UiThread
  public CompareActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mOriginalTv = Utils.findRequiredViewAsType(source, R.id.original_tv, "field 'mOriginalTv'", TextView.class);
    target.mFinalTv = Utils.findRequiredViewAsType(source, R.id.final_tv, "field 'mFinalTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.sort_btn, "method 'onViewClicked'");
    view2131165600 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sort_second_btn, "method 'onViewClicked'");
    view2131165601 = view;
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

    target.mOriginalTv = null;
    target.mFinalTv = null;

    view2131165600.setOnClickListener(null);
    view2131165600 = null;
    view2131165601.setOnClickListener(null);
    view2131165601 = null;

    this.target = null;
  }
}
