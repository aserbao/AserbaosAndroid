// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.designMode.strategyMode;

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

public class StrategyActivity_ViewBinding<T extends StrategyActivity> implements Unbinder {
  protected T target;

  private View view2131165271;

  private View view2131165270;

  private View view2131165269;

  @UiThread
  public StrategyActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mZombieTv = Utils.findRequiredViewAsType(source, R.id.zombie_tv, "field 'mZombieTv'", TextView.class);
    target.mSpeedTv = Utils.findRequiredViewAsType(source, R.id.speed_tv, "field 'mSpeedTv'", TextView.class);
    target.mAttackTv = Utils.findRequiredViewAsType(source, R.id.attack_tv, "field 'mAttackTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_change_zombie, "method 'onViewClicked'");
    view2131165271 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_change_speed, "method 'onViewClicked'");
    view2131165270 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_change_attack, "method 'onViewClicked'");
    view2131165269 = view;
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

    target.mZombieTv = null;
    target.mSpeedTv = null;
    target.mAttackTv = null;

    view2131165271.setOnClickListener(null);
    view2131165271 = null;
    view2131165270.setOnClickListener(null);
    view2131165270 = null;
    view2131165269.setOnClickListener(null);
    view2131165269 = null;

    this.target = null;
  }
}
