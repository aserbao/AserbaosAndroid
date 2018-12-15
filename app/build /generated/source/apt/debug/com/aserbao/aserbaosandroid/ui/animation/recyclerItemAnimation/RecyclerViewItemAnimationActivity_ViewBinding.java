// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecyclerViewItemAnimationActivity_ViewBinding<T extends RecyclerViewItemAnimationActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RecyclerViewItemAnimationActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.animation_recycler_view, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mRecyclerView = null;

    this.target = null;
  }
}
