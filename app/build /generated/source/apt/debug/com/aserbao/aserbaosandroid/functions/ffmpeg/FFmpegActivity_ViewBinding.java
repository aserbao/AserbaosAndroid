// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.ffmpeg;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FFmpegActivity_ViewBinding<T extends FFmpegActivity> implements Unbinder {
  protected T target;

  private View view2131165525;

  private View view2131165685;

  private View view2131165662;

  private View view2131165389;

  @UiThread
  public FFmpegActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mFfmpegShowTv = Utils.findRequiredViewAsType(source, R.id.ffmpeg_show_tv, "field 'mFfmpegShowTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.one_btn, "method 'onViewClicked'");
    view2131165525 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two_btn, "method 'onViewClicked'");
    view2131165685 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.three_btn, "method 'onViewClicked'");
    view2131165662 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.four_btn, "method 'onViewClicked'");
    view2131165389 = view;
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

    target.mFfmpegShowTv = null;

    view2131165525.setOnClickListener(null);
    view2131165525 = null;
    view2131165685.setOnClickListener(null);
    view2131165685 = null;
    view2131165662.setOnClickListener(null);
    view2131165662 = null;
    view2131165389.setOnClickListener(null);
    view2131165389 = null;

    this.target = null;
  }
}
