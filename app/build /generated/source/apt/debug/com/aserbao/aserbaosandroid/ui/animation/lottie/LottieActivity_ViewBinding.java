// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.animation.lottie;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LottieActivity_ViewBinding<T extends LottieActivity> implements Unbinder {
  protected T target;

  @UiThread
  public LottieActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mAnimationView = Utils.findRequiredViewAsType(source, R.id.animation_view, "field 'mAnimationView'", LottieAnimationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mAnimationView = null;

    this.target = null;
  }
}
