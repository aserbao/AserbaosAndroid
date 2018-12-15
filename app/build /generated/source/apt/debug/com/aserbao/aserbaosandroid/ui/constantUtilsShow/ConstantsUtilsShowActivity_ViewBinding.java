// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.constantUtilsShow;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConstantsUtilsShowActivity_ViewBinding<T extends ConstantsUtilsShowActivity> implements Unbinder {
  protected T target;

  private View view2131165288;

  private View view2131165274;

  @UiThread
  public ConstantsUtilsShowActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_screen_show, "field 'mBtnScreenShow' and method 'onViewClicked'");
    target.mBtnScreenShow = Utils.castView(view, R.id.btn_screen_show, "field 'mBtnScreenShow'", Button.class);
    view2131165288 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_data_format, "field 'mBtnDataFormat' and method 'onViewClicked'");
    target.mBtnDataFormat = Utils.castView(view, R.id.btn_data_format, "field 'mBtnDataFormat'", Button.class);
    view2131165274 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mShowResult = Utils.findRequiredViewAsType(source, R.id.show_result, "field 'mShowResult'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mBtnScreenShow = null;
    target.mBtnDataFormat = null;
    target.mShowResult = null;

    view2131165288.setOnClickListener(null);
    view2131165288 = null;
    view2131165274.setOnClickListener(null);
    view2131165274 = null;

    this.target = null;
  }
}
