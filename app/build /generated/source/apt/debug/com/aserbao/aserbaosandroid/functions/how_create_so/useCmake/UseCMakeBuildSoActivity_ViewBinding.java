// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.how_create_so.useCmake;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UseCMakeBuildSoActivity_ViewBinding<T extends UseCMakeBuildSoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public UseCMakeBuildSoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mUseCmakeTv = Utils.findRequiredViewAsType(source, R.id.use_cmake_tv, "field 'mUseCmakeTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mUseCmakeTv = null;

    this.target = null;
  }
}
