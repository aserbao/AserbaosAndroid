// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.textViews.shadowText;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.AUtils.AUI.colorPicker.LinearColorPicker;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TextShadowAdapter$TextShadowViewHolder_ViewBinding<T extends TextShadowAdapter.TextShadowViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TextShadowAdapter$TextShadowViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mTbiTvColor = Utils.findRequiredViewAsType(source, R.id.tbi_tv_color, "field 'mTbiTvColor'", TextView.class);
    target.mLinearColorPicker = Utils.findRequiredViewAsType(source, R.id.linear_color_picker, "field 'mLinearColorPicker'", LinearColorPicker.class);
    target.mLinearColorTextColorPicker = Utils.findRequiredViewAsType(source, R.id.linear_color_text_color_picker, "field 'mLinearColorTextColorPicker'", LinearColorPicker.class);
    target.mTbiTvRadius = Utils.findRequiredViewAsType(source, R.id.tbi_tv_radius, "field 'mTbiTvRadius'", TextView.class);
    target.mTextBarItemSeekBarRadius = Utils.findRequiredViewAsType(source, R.id.text_bar_item_seek_bar_radius, "field 'mTextBarItemSeekBarRadius'", SeekBar.class);
    target.mTbiTvDx = Utils.findRequiredViewAsType(source, R.id.tbi_tv_dx, "field 'mTbiTvDx'", TextView.class);
    target.mTextBarItemSeekBarDx = Utils.findRequiredViewAsType(source, R.id.text_bar_item_seek_bar_dx, "field 'mTextBarItemSeekBarDx'", SeekBar.class);
    target.mTbiTvDy = Utils.findRequiredViewAsType(source, R.id.tbi_tv_dy, "field 'mTbiTvDy'", TextView.class);
    target.mTextBarItemSeekBarDy = Utils.findRequiredViewAsType(source, R.id.text_bar_item_seek_bar_dy, "field 'mTextBarItemSeekBarDy'", SeekBar.class);
    target.mTbiTvAlpha = Utils.findRequiredViewAsType(source, R.id.tbi_tv_alpha, "field 'mTbiTvAlpha'", TextView.class);
    target.mTextBarItemSeekBarAlpha = Utils.findRequiredViewAsType(source, R.id.text_bar_item_seek_bar_alpha, "field 'mTextBarItemSeekBarAlpha'", SeekBar.class);
    target.mTbiTvElevation = Utils.findRequiredViewAsType(source, R.id.tbi_tv_elevation, "field 'mTbiTvElevation'", TextView.class);
    target.mTextBarItemSeekBarElevation = Utils.findRequiredViewAsType(source, R.id.text_bar_item_seek_bar_elevation, "field 'mTextBarItemSeekBarElevation'", SeekBar.class);
    target.mTbiTvRotate = Utils.findRequiredViewAsType(source, R.id.tbi_tv_rotate, "field 'mTbiTvRotate'", TextView.class);
    target.mTextBarItemSeekBarRotateX = Utils.findRequiredViewAsType(source, R.id.text_bar_item_seek_bar_rotatex, "field 'mTextBarItemSeekBarRotateX'", SeekBar.class);
    target.mTextBarItemSeekBarRotateY = Utils.findRequiredViewAsType(source, R.id.text_bar_item_seek_bar_rotatey, "field 'mTextBarItemSeekBarRotateY'", SeekBar.class);
    target.mTvDiaplay = Utils.findRequiredViewAsType(source, R.id.tv_diaplay, "field 'mTvDiaplay'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTbiTvColor = null;
    target.mLinearColorPicker = null;
    target.mLinearColorTextColorPicker = null;
    target.mTbiTvRadius = null;
    target.mTextBarItemSeekBarRadius = null;
    target.mTbiTvDx = null;
    target.mTextBarItemSeekBarDx = null;
    target.mTbiTvDy = null;
    target.mTextBarItemSeekBarDy = null;
    target.mTbiTvAlpha = null;
    target.mTextBarItemSeekBarAlpha = null;
    target.mTbiTvElevation = null;
    target.mTextBarItemSeekBarElevation = null;
    target.mTbiTvRotate = null;
    target.mTextBarItemSeekBarRotateX = null;
    target.mTextBarItemSeekBarRotateY = null;
    target.mTvDiaplay = null;

    this.target = null;
  }
}
