// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.textViews.simple;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.texts.textViews.customTextView.EditTestMultiLine;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SimpleTextViewActivity_ViewBinding<T extends SimpleTextViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SimpleTextViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mMultiLineEditText = Utils.findRequiredViewAsType(source, R.id.multi_line_edit_text, "field 'mMultiLineEditText'", EditTestMultiLine.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mMultiLineEditText = null;

    this.target = null;
  }
}
