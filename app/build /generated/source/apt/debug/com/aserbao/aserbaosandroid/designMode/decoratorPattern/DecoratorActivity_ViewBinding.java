// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.designMode.decoratorPattern;

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

public class DecoratorActivity_ViewBinding<T extends DecoratorActivity> implements Unbinder {
  protected T target;

  private View view2131165479;

  private View view2131165306;

  private View view2131165602;

  private View view2131165328;

  private View view2131165622;

  @UiThread
  public DecoratorActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mResult = Utils.findRequiredViewAsType(source, R.id.result, "field 'mResult'", TextView.class);
    view = Utils.findRequiredView(source, R.id.latte, "method 'onViewClicked'");
    view2131165479 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cappuccio, "method 'onViewClicked'");
    view2131165306 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.soul, "method 'onViewClicked'");
    view2131165602 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cream, "method 'onViewClicked'");
    view2131165328 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.summary, "method 'onViewClicked'");
    view2131165622 = view;
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

    target.mResult = null;

    view2131165479.setOnClickListener(null);
    view2131165479 = null;
    view2131165306.setOnClickListener(null);
    view2131165306 = null;
    view2131165602.setOnClickListener(null);
    view2131165602 = null;
    view2131165328.setOnClickListener(null);
    view2131165328 = null;
    view2131165622.setOnClickListener(null);
    view2131165622 = null;

    this.target = null;
  }
}
