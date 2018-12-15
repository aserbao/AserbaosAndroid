// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.rv.addItemRecyclerView;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddRecyclerViewActivity_ViewBinding<T extends AddRecyclerViewActivity> implements Unbinder {
  protected T target;

  private View view2131165225;

  private View view2131165226;

  @UiThread
  public AddRecyclerViewActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mAddRecyclerView = Utils.findRequiredViewAsType(source, R.id.add_recycler_view, "field 'mAddRecyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.add_btn, "field 'mAddBtn' and method 'onViewClicked'");
    target.mAddBtn = Utils.castView(view, R.id.add_btn, "field 'mAddBtn'", Button.class);
    view2131165225 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_btn_first, "method 'onViewClicked'");
    view2131165226 = view;
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

    target.mAddRecyclerView = null;
    target.mAddBtn = null;

    view2131165225.setOnClickListener(null);
    view2131165225 = null;
    view2131165226.setOnClickListener(null);
    view2131165226 = null;

    this.target = null;
  }
}
