// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.rv.moveToDeleteRecyclerView;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MoveToDeleteActivity_ViewBinding<T extends MoveToDeleteActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MoveToDeleteActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mMoveToDeleteRv = Utils.findRequiredViewAsType(source, R.id.move_to_delete_rv, "field 'mMoveToDeleteRv'", RecyclerView.class);
    target.moveToBtn = Utils.findRequiredViewAsType(source, R.id.move_to_btn, "field 'moveToBtn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mMoveToDeleteRv = null;
    target.moveToBtn = null;

    this.target = null;
  }
}
