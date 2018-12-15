// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.AudioAndVideo.detail_Info.iamgeInfo;

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

public class ImageInfoActivity_ViewBinding<T extends ImageInfoActivity> implements Unbinder {
  protected T target;

  private View view2131165450;

  @UiThread
  public ImageInfoActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mShowInfoTv = Utils.findRequiredViewAsType(source, R.id.show_info_tv, "field 'mShowInfoTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iamge_detail_info_btn, "method 'onViewClicked'");
    view2131165450 = view;
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

    target.mShowInfoTv = null;

    view2131165450.setOnClickListener(null);
    view2131165450 = null;

    this.target = null;
  }
}
