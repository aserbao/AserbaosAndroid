// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.events.scrollEvent;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScrollEventActivity_ViewBinding<T extends ScrollEventActivity> implements Unbinder {
  protected T target;

  @UiThread
  public ScrollEventActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mPopScrollView = Utils.findRequiredViewAsType(source, R.id.pop_scroll_view, "field 'mPopScrollView'", Button.class);
    target.mScrollViewIv = Utils.findRequiredViewAsType(source, R.id.scroll_view_iv, "field 'mScrollViewIv'", FrameLayout.class);
    target.mJzVideoPlayer = Utils.findRequiredViewAsType(source, R.id.jz_video_player, "field 'mJzVideoPlayer'", MyJZVideoPlayer.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mPopScrollView = null;
    target.mScrollViewIv = null;
    target.mJzVideoPlayer = null;

    this.target = null;
  }
}
