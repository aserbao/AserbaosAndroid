// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.iamgePhotoshop.compound;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CompoundActivity_ViewBinding<T extends CompoundActivity> implements Unbinder {
  protected T target;

  private View view2131165322;

  @UiThread
  public CompoundActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mCompoundOneIv = Utils.findRequiredViewAsType(source, R.id.compound_one_iv, "field 'mCompoundOneIv'", ImageView.class);
    target.mCompoundTwoIv = Utils.findRequiredViewAsType(source, R.id.compound_two_iv, "field 'mCompoundTwoIv'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.compound_btn, "field 'mCompoundBtn' and method 'onViewClicked'");
    target.mCompoundBtn = Utils.castView(view, R.id.compound_btn, "field 'mCompoundBtn'", Button.class);
    view2131165322 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.mCompoundResultIv = Utils.findRequiredViewAsType(source, R.id.compound_result_iv, "field 'mCompoundResultIv'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mCompoundOneIv = null;
    target.mCompoundTwoIv = null;
    target.mCompoundBtn = null;
    target.mCompoundResultIv = null;

    view2131165322.setOnClickListener(null);
    view2131165322 = null;

    this.target = null;
  }
}
