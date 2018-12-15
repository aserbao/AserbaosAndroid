// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MoveToDeleteAda$MoveChildViewHolder_ViewBinding<T extends MoveToDeleteAda.MoveChildViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MoveToDeleteAda$MoveChildViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.normalItem = Utils.findRequiredViewAsType(source, R.id.normal_item, "field 'normalItem'", TextView.class);
    target.tvGroupDel = Utils.findRequiredViewAsType(source, R.id.tv_group_del, "field 'tvGroupDel'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.normalItem = null;
    target.tvGroupDel = null;

    this.target = null;
  }
}
