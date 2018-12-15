// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.component.activitys.lifeCycle;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LifeCycle3Activity_ViewBinding<T extends LifeCycle3Activity> implements Unbinder {
  protected T target;

  private View view2131165473;

  private View view2131165471;

  private View view2131165472;

  @UiThread
  public LifeCycle3Activity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mLifeCycleIv = Utils.findRequiredViewAsType(source, R.id.life_cycle_iv, "field 'mLifeCycleIv'", ImageView.class);
    target.mLifeCycleTv = Utils.findRequiredViewAsType(source, R.id.life_cycle_tv, "field 'mLifeCycleTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.jump_life_cycle_activity, "method 'onViewClicked'");
    view2131165473 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.jump_life_cycle2_activity, "method 'onViewClicked'");
    view2131165471 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.jump_life_cycle3_activity, "method 'onViewClicked'");
    view2131165472 = view;
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

    target.mLifeCycleIv = null;
    target.mLifeCycleTv = null;

    view2131165473.setOnClickListener(null);
    view2131165473 = null;
    view2131165471.setOnClickListener(null);
    view2131165471 = null;
    view2131165472.setOnClickListener(null);
    view2131165472 = null;

    this.target = null;
  }
}
