// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.database.base.rv.viewHolder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TextViewHolder_ViewBinding<T extends TextViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TextViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mTextViewHolderContentTv = Utils.findRequiredViewAsType(source, R.id.text_view_holder_content_tv, "field 'mTextViewHolderContentTv'", TextView.class);
    target.mTextViewHolderTimeTv = Utils.findRequiredViewAsType(source, R.id.text_view_holder_time_tv, "field 'mTextViewHolderTimeTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTextViewHolderContentTv = null;
    target.mTextViewHolderTimeTv = null;

    this.target = null;
  }
}
