// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.designMode.observerMode;

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

public class ObserverActivity_ViewBinding<T extends ObserverActivity> implements Unbinder {
  protected T target;

  private View view2131165289;

  private View view2131165381;

  private View view2131165579;

  @UiThread
  public ObserverActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_send_broadcast, "field 'mBtnSendBroadcast' and method 'onViewClicked'");
    target.mBtnSendBroadcast = Utils.castView(view, R.id.btn_send_broadcast, "field 'mBtnSendBroadcast'", Button.class);
    view2131165289 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mFirstObserverTv = Utils.findRequiredViewAsType(source, R.id.first_observer_tv, "field 'mFirstObserverTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.first_observer_btn, "field 'mFirstObserverBtn' and method 'onViewClicked'");
    target.mFirstObserverBtn = Utils.castView(view, R.id.first_observer_btn, "field 'mFirstObserverBtn'", Button.class);
    view2131165381 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mSecondObserverTv = Utils.findRequiredViewAsType(source, R.id.second_observer_tv, "field 'mSecondObserverTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.sencond_observer_btn, "field 'mSencondObserverBtn' and method 'onViewClicked'");
    target.mSencondObserverBtn = Utils.castView(view, R.id.sencond_observer_btn, "field 'mSencondObserverBtn'", Button.class);
    view2131165579 = view;
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

    target.mBtnSendBroadcast = null;
    target.mFirstObserverTv = null;
    target.mFirstObserverBtn = null;
    target.mSecondObserverTv = null;
    target.mSencondObserverBtn = null;

    view2131165289.setOnClickListener(null);
    view2131165289 = null;
    view2131165381.setOnClickListener(null);
    view2131165381 = null;
    view2131165579.setOnClickListener(null);
    view2131165579 = null;

    this.target = null;
  }
}
