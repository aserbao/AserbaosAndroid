// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.recyclerView.commonRecyclerView.baseAdapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseAdapterActivity_ViewBinding<T extends BaseAdapterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BaseAdapterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.baseAdapterRv = Utils.findRequiredViewAsType(source, R.id.base_adapter_rv, "field 'baseAdapterRv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.baseAdapterRv = null;

    this.target = null;
  }
}
