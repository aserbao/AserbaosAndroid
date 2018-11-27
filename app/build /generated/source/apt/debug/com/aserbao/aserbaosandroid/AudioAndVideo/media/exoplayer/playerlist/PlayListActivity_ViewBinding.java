// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AudioAndVideo.media.exoplayer.playerlist;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import com.google.android.exoplayer2.ui.PlayerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PlayListActivity_ViewBinding<T extends PlayListActivity> implements Unbinder {
  protected T target;

  private View view2131165285;

  private View view2131165267;

  private View view2131165292;

  private View view2131165286;

  private View view2131165265;

  @UiThread
  public PlayListActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mPlayerListPlayerView = Utils.findRequiredViewAsType(source, R.id.player_list_player_view, "field 'mPlayerListPlayerView'", PlayerView.class);
    target.mFrameLayout = Utils.findRequiredViewAsType(source, R.id.frame_layout, "field 'mFrameLayout'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_next, "method 'onViewClicked'");
    view2131165285 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_before, "method 'onViewClicked'");
    view2131165267 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_start, "method 'onViewClicked'");
    view2131165292 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_pause, "method 'onViewClicked'");
    view2131165286 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_add, "method 'onViewClicked'");
    view2131165265 = view;
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

    target.mPlayerListPlayerView = null;
    target.mFrameLayout = null;

    view2131165285.setOnClickListener(null);
    view2131165285 = null;
    view2131165267.setOnClickListener(null);
    view2131165267 = null;
    view2131165292.setOnClickListener(null);
    view2131165292 = null;
    view2131165286.setOnClickListener(null);
    view2131165286 = null;
    view2131165265.setOnClickListener(null);
    view2131165265 = null;

    this.target = null;
  }
}
