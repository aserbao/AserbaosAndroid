// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.colorPicker;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.LinearColorPicker;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.ColorPicker;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.OpacityBar;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.SVBar;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.SaturationBar;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.holoColorPicker.ValueBar;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ColorPickerActivity_ViewBinding<T extends ColorPickerActivity> implements Unbinder {
  protected T target;

  private View view2131165380;

  private View view2131165576;

  private View view2131165662;

  @UiThread
  public ColorPickerActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.linearColorPicker = Utils.findRequiredViewAsType(source, R.id.linear_color_picker, "field 'linearColorPicker'", LinearColorPicker.class);
    target.colorIv = Utils.findRequiredViewAsType(source, R.id.color_iv, "field 'colorIv'", ImageView.class);
    target.colorCardView = Utils.findRequiredViewAsType(source, R.id.color_card_view, "field 'colorCardView'", CardView.class);
    target.holoColorPicker = Utils.findRequiredViewAsType(source, R.id.holo_color_picker, "field 'holoColorPicker'", ColorPicker.class);
    target.opacityBar = Utils.findRequiredViewAsType(source, R.id.opacity_bar, "field 'opacityBar'", OpacityBar.class);
    target.saturationBar = Utils.findRequiredViewAsType(source, R.id.saturation_bar, "field 'saturationBar'", SaturationBar.class);
    target.valueBar = Utils.findRequiredViewAsType(source, R.id.value_bar, "field 'valueBar'", ValueBar.class);
    target.svBar = Utils.findRequiredViewAsType(source, R.id.sv_bar, "field 'svBar'", SVBar.class);
    target.holoColorLl = Utils.findRequiredViewAsType(source, R.id.holo_color_ll, "field 'holoColorLl'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.first_btn, "field 'firstBtn' and method 'onViewClicked'");
    target.firstBtn = Utils.castView(view, R.id.first_btn, "field 'firstBtn'", Button.class);
    view2131165380 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.second_btn, "field 'secondBtn' and method 'onViewClicked'");
    target.secondBtn = Utils.castView(view, R.id.second_btn, "field 'secondBtn'", Button.class);
    view2131165576 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.three_btn, "field 'threeBtn' and method 'onViewClicked'");
    target.threeBtn = Utils.castView(view, R.id.three_btn, "field 'threeBtn'", Button.class);
    view2131165662 = view;
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

    target.linearColorPicker = null;
    target.colorIv = null;
    target.colorCardView = null;
    target.holoColorPicker = null;
    target.opacityBar = null;
    target.saturationBar = null;
    target.valueBar = null;
    target.svBar = null;
    target.holoColorLl = null;
    target.firstBtn = null;
    target.secondBtn = null;
    target.threeBtn = null;

    view2131165380.setOnClickListener(null);
    view2131165380 = null;
    view2131165576.setOnClickListener(null);
    view2131165576 = null;
    view2131165662.setOnClickListener(null);
    view2131165662 = null;

    this.target = null;
  }
}
