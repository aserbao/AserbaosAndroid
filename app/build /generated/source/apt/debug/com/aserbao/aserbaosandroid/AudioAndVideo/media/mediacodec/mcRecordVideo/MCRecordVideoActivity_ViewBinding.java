// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AudioAndVideo.media.mediacodec.mcRecordVideo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.TextureView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MCRecordVideoActivity_ViewBinding<T extends MCRecordVideoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MCRecordVideoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mMcRecordTexureView = Utils.findRequiredViewAsType(source, R.id.mc_record_texure_view, "field 'mMcRecordTexureView'", TextureView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mMcRecordTexureView = null;

    this.target = null;
  }
}
