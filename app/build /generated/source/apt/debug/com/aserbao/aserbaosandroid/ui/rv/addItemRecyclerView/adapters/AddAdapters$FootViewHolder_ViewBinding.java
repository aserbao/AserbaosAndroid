// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.rv.addItemRecyclerView.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddAdapters$FootViewHolder_ViewBinding<T extends AddAdapters.FootViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public AddAdapters$FootViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mFootItemTv = Utils.findRequiredViewAsType(source, R.id.foot_item_tv, "field 'mFootItemTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mFootItemTv = null;

    this.target = null;
  }
}
