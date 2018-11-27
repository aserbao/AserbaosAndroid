// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AudioAndVideo.media.mediaplayer;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.SurfaceView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MediaPlayerActivity_ViewBinding<T extends MediaPlayerActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MediaPlayerActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mMediaSurfaceView = Utils.findRequiredViewAsType(source, R.id.media_surface_view, "field 'mMediaSurfaceView'", SurfaceView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mMediaSurfaceView = null;

    this.target = null;
  }
}
