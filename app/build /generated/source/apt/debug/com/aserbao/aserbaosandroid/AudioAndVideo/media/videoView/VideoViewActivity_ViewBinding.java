// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AudioAndVideo.media.videoView;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.SeekBar;
import android.widget.VideoView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoViewActivity_ViewBinding<T extends VideoViewActivity> implements Unbinder {
  protected T target;

  @UiThread
  public VideoViewActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mVideoView = Utils.findRequiredViewAsType(source, R.id.video_view, "field 'mVideoView'", VideoView.class);
    target.mVideoSeekBar = Utils.findRequiredViewAsType(source, R.id.video_seek_bar, "field 'mVideoSeekBar'", SeekBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mVideoView = null;
    target.mVideoSeekBar = null;

    this.target = null;
  }
}
