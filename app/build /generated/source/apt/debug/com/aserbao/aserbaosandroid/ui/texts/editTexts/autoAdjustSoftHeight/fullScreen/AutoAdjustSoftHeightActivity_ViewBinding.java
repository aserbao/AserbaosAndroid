// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight.fullScreen;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AutoAdjustSoftHeightActivity_ViewBinding<T extends AutoAdjustSoftHeightActivity> implements Unbinder {
  protected T target;

  private View view2131165290;

  private View view2131165277;

  private View view2131165276;

  @UiThread
  public AutoAdjustSoftHeightActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mImageView = Utils.findRequiredViewAsType(source, R.id.imageView, "field 'mImageView'", ImageView.class);
    target.mLinearLayout = Utils.findRequiredViewAsType(source, R.id.linearLayout, "field 'mLinearLayout'", LinearLayout.class);
    target.mEditText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'mEditText'", EditText.class);
    target.mViewEdit = Utils.findRequiredViewAsType(source, R.id.viewEdit, "field 'mViewEdit'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_show, "field 'mBtnShow' and method 'onViewClicked'");
    target.mBtnShow = Utils.castView(view, R.id.btn_show, "field 'mBtnShow'", Button.class);
    view2131165290 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mResultTv = Utils.findRequiredViewAsType(source, R.id.result_tv, "field 'mResultTv'", TextView.class);
    target.mAutoRl = Utils.findRequiredViewAsType(source, R.id.auto_rl, "field 'mAutoRl'", RelativeLayout.class);
    target.mTextView = Utils.findRequiredViewAsType(source, R.id.text_view, "field 'mTextView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_do_something, "method 'onViewClicked'");
    view2131165277 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_do_second, "method 'onViewClicked'");
    view2131165276 = view;
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

    target.mImageView = null;
    target.mLinearLayout = null;
    target.mEditText = null;
    target.mViewEdit = null;
    target.mBtnShow = null;
    target.mResultTv = null;
    target.mAutoRl = null;
    target.mTextView = null;

    view2131165290.setOnClickListener(null);
    view2131165290 = null;
    view2131165277.setOnClickListener(null);
    view2131165277 = null;
    view2131165276.setOnClickListener(null);
    view2131165276 = null;

    this.target = null;
  }
}
