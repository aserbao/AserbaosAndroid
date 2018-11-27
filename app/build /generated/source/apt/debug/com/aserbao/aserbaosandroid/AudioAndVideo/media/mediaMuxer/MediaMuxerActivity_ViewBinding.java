// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AudioAndVideo.media.mediaMuxer;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MediaMuxerActivity_ViewBinding<T extends MediaMuxerActivity> implements Unbinder {
  protected T target;

  private View view2131165283;

  @UiThread
  public MediaMuxerActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_media_muxer, "method 'onViewClicked'");
    view2131165283 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (this.target == null) throw new IllegalStateException("Bindings already cleared.");

    view2131165283.setOnClickListener(null);
    view2131165283 = null;

    this.target = null;
  }
}
