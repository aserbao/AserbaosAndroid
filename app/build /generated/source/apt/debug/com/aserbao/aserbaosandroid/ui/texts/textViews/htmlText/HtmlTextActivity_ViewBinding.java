// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.textViews.htmlText;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HtmlTextActivity_ViewBinding<T extends HtmlTextActivity> implements Unbinder {
  protected T target;

  @UiThread
  public HtmlTextActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mHtmlTv = Utils.findRequiredViewAsType(source, R.id.html_tv, "field 'mHtmlTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mHtmlTv = null;

    this.target = null;
  }
}
