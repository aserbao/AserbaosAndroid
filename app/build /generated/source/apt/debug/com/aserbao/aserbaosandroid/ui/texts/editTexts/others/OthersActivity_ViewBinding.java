// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.editTexts.others;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OthersActivity_ViewBinding<T extends OthersActivity> implements Unbinder {
  protected T target;

  @UiThread
  public OthersActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mOtherEdit = Utils.findRequiredViewAsType(source, R.id.other_edit, "field 'mOtherEdit'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mOtherEdit = null;

    this.target = null;
  }
}
