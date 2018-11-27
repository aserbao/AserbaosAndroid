// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.jumpSystemSetting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JumpSystemSettingActivity_ViewBinding<T extends JumpSystemSettingActivity> implements Unbinder {
  protected T target;

  private View view2131165474;

  private View view2131165475;

  private View view2131165470;

  @UiThread
  public JumpSystemSettingActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.jump_notification, "method 'onViewClicked'");
    view2131165474 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.jump_setting, "method 'onViewClicked'");
    view2131165475 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.jump_app_activity, "method 'onViewClicked'");
    view2131165470 = view;
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

    target.mRecyclerView = null;

    view2131165474.setOnClickListener(null);
    view2131165474 = null;
    view2131165475.setOnClickListener(null);
    view2131165475 = null;
    view2131165470.setOnClickListener(null);
    view2131165470 = null;

    this.target = null;
  }
}
