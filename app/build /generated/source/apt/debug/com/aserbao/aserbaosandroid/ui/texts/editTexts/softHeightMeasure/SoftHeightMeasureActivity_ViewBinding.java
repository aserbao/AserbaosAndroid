// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.editTexts.softHeightMeasure;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.AUtils.AUI.layout.FlowLayout;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SoftHeightMeasureActivity_ViewBinding<T extends SoftHeightMeasureActivity> implements Unbinder {
  protected T target;

  private View view2131165262;

  private View view2131165263;

  private View view2131165264;

  private View view2131165266;

  private View view2131165296;

  private View view2131165294;

  private View view2131165297;

  private View view2131165295;

  @UiThread
  public SoftHeightMeasureActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.softEt = Utils.findRequiredViewAsType(source, R.id.soft_et, "field 'softEt'", EditText.class);
    target.showSoftHeightTv = Utils.findRequiredViewAsType(source, R.id.show_soft_height_tv, "field 'showSoftHeightTv'", TextView.class);
    target.softHeightRl = Utils.findRequiredViewAsType(source, R.id.soft_height_rl, "field 'softHeightRl'", RelativeLayout.class);
    target.mImageViewBg = Utils.findRequiredViewAsType(source, R.id.image_view_bg, "field 'mImageViewBg'", ImageView.class);
    target.mSoftHeightFl = Utils.findRequiredViewAsType(source, R.id.soft_height_fl, "field 'mSoftHeightFl'", FlowLayout.class);
    view = Utils.findRequiredView(source, R.id.bt_adjustPan, "method 'onViewClicked'");
    view2131165262 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_adjustResize, "method 'onViewClicked'");
    view2131165263 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.bt_adjustUnspecified, "method 'onViewClicked'");
    view2131165264 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_adjustNothing, "method 'onViewClicked'");
    view2131165266 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_stateHidden, "method 'onViewClicked'");
    view2131165296 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_stateAlwaysHidden, "method 'onViewClicked'");
    view2131165294 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_stateVisible, "method 'onViewClicked'");
    view2131165297 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_stateAlwaysVisible, "method 'onViewClicked'");
    view2131165295 = view;
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

    target.softEt = null;
    target.showSoftHeightTv = null;
    target.softHeightRl = null;
    target.mImageViewBg = null;
    target.mSoftHeightFl = null;

    view2131165262.setOnClickListener(null);
    view2131165262 = null;
    view2131165263.setOnClickListener(null);
    view2131165263 = null;
    view2131165264.setOnClickListener(null);
    view2131165264 = null;
    view2131165266.setOnClickListener(null);
    view2131165266 = null;
    view2131165296.setOnClickListener(null);
    view2131165296 = null;
    view2131165294.setOnClickListener(null);
    view2131165294 = null;
    view2131165297.setOnClickListener(null);
    view2131165297 = null;
    view2131165295.setOnClickListener(null);
    view2131165295 = null;

    this.target = null;
  }
}
