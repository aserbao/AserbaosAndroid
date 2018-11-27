// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.editTexts.customEdittext;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomEditTextActivity_ViewBinding<T extends CustomEditTextActivity> implements Unbinder {
  protected T target;

  private View view2131165348;

  private View view2131165320;

  @UiThread
  public CustomEditTextActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mTestEmojiEditText = Utils.findRequiredViewAsType(source, R.id.test_emoji_edit_text, "field 'mTestEmojiEditText'", EmojiCustomEditText.class);
    view = Utils.findRequiredView(source, R.id.emoji_custom_btn, "method 'onViewClicked'");
    view2131165348 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.common_btn, "method 'onViewClicked'");
    view2131165320 = view;
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

    target.mTestEmojiEditText = null;

    view2131165348.setOnClickListener(null);
    view2131165348 = null;
    view2131165320.setOnClickListener(null);
    view2131165320 = null;

    this.target = null;
  }
}
