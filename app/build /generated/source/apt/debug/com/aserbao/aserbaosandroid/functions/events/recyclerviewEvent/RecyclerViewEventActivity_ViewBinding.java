// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.functions.events.recyclerviewEvent.recyclers.AEventRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RecyclerViewEventActivity_ViewBinding<T extends RecyclerViewEventActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RecyclerViewEventActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mRecyclerEventRv = Utils.findRequiredViewAsType(source, R.id.recycler_event_rv, "field 'mRecyclerEventRv'", AEventRecyclerView.class);
    target.mTopVideoParentRl = Utils.findRequiredViewAsType(source, R.id.recycler_event_rl, "field 'mTopVideoParentRl'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mRecyclerEventRv = null;
    target.mTopVideoParentRl = null;

    this.target = null;
  }
}
