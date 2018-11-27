// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.animation.recyclerItemAnimation.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnimationAdapter$MyViewHolder_ViewBinding<T extends AnimationAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public AnimationAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mItemIv = Utils.findRequiredViewAsType(source, R.id.item_iv, "field 'mItemIv'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mItemIv = null;

    this.target = null;
  }
}
