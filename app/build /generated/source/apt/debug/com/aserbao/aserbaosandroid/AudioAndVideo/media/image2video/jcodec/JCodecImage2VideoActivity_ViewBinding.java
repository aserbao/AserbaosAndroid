// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AudioAndVideo.media.image2video.jcodec;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import cn.jzvd.JZVideoPlayerStandard;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JCodecImage2VideoActivity_ViewBinding<T extends JCodecImage2VideoActivity> implements Unbinder {
  protected T target;

  private View view2131165469;

  @UiThread
  public JCodecImage2VideoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mJcodecJzVideoPlay = Utils.findRequiredViewAsType(source, R.id.jcodec_jz_video_play, "field 'mJcodecJzVideoPlay'", JZVideoPlayerStandard.class);
    view = Utils.findRequiredView(source, R.id.jcodec_start, "method 'onViewClicked'");
    view2131165469 = view;
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
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mJcodecJzVideoPlay = null;

    view2131165469.setOnClickListener(null);
    view2131165469 = null;

    this.target = null;
  }
}
