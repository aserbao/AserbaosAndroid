// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding<T extends HomeActivity> implements Unbinder {
  protected T target;

  @UiThread
  public HomeActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mHomeRecyclerView = Utils.findRequiredViewAsType(source, R.id.home_recycler_view, "field 'mHomeRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mHomeRecyclerView = null;

    this.target = null;
  }
}
