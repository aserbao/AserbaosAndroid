// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommonAdapter$OpenGlViewHolder_ViewBinding<T extends CommonAdapter.OpenGlViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public CommonAdapter$OpenGlViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mItemTv = Utils.findRequiredViewAsType(source, R.id.item_tv, "field 'mItemTv'", TextView.class);
    target.mItemCardView = Utils.findRequiredViewAsType(source, R.id.item_card_view, "field 'mItemCardView'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mItemTv = null;
    target.mItemCardView = null;

    this.target = null;
  }
}
