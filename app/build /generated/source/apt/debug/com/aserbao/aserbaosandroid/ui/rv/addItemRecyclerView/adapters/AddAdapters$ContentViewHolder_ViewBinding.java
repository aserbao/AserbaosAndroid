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

public class AddAdapters$ContentViewHolder_ViewBinding<T extends AddAdapters.ContentViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public AddAdapters$ContentViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mAddContentItemTv = Utils.findRequiredViewAsType(source, R.id.add_content_item_tv, "field 'mAddContentItemTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mAddContentItemTv = null;

    this.target = null;
  }
}
