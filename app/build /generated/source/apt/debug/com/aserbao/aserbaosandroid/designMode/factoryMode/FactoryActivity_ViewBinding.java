// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.designMode.factoryMode;

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

public class FactoryActivity_ViewBinding<T extends FactoryActivity> implements Unbinder {
  protected T target;

  private View view2131165291;

  private View view2131165272;

  private View view2131165282;

  @UiThread
  public FactoryActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mFactoryTv = Utils.findRequiredViewAsType(source, R.id.factory_tv, "field 'mFactoryTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_sichuang, "method 'onViewClicked'");
    view2131165291 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_chongqing, "method 'onViewClicked'");
    view2131165272 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_hunan, "method 'onViewClicked'");
    view2131165282 = view;
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

    target.mFactoryTv = null;

    view2131165291.setOnClickListener(null);
    view2131165291 = null;
    view2131165272.setOnClickListener(null);
    view2131165272 = null;
    view2131165282.setOnClickListener(null);
    view2131165282 = null;

    this.target = null;
  }
}
