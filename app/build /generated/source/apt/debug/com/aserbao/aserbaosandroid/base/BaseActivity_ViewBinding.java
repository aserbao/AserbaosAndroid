// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseActivity_ViewBinding<T extends BaseActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BaseActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mOpenglRecyclerView = Utils.findRequiredViewAsType(source, R.id.opengl_recycler_view, "field 'mOpenglRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mOpenglRecyclerView = null;

    this.target = null;
  }
}
