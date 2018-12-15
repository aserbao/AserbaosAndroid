// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.ui.texts.editTexts.autoAdjustSoftHeight;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.AUtils.AUI.progress.ACustomRecordProgress;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FullDragDemoActivity_ViewBinding<T extends FullDragDemoActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FullDragDemoActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mNewStoryEt = Utils.findRequiredViewAsType(source, R.id.new_story_et, "field 'mNewStoryEt'", EditText.class);
    target.mPhotoSelectionIv = Utils.findRequiredViewAsType(source, R.id.photo_selection_iv, "field 'mPhotoSelectionIv'", ImageView.class);
    target.mSwitchCameraV = Utils.findRequiredViewAsType(source, R.id.switch_camera_v, "field 'mSwitchCameraV'", ImageView.class);
    target.mSwitchFlashV = Utils.findRequiredViewAsType(source, R.id.switch_flash_v, "field 'mSwitchFlashV'", ImageView.class);
    target.mLlBottom = Utils.findRequiredViewAsType(source, R.id.ll_bottom, "field 'mLlBottom'", LinearLayout.class);
    target.mPlaceholderTv = Utils.findRequiredViewAsType(source, R.id.placeholder_tv, "field 'mPlaceholderTv'", TextView.class);
    target.mIamgeView = Utils.findRequiredViewAsType(source, R.id.iamge_view, "field 'mIamgeView'", ImageView.class);
    target.mDragIv = Utils.findRequiredViewAsType(source, R.id.drag_iv, "field 'mDragIv'", ACustomRecordProgress.class);
    target.mMoveViewContainerRl = Utils.findRequiredViewAsType(source, R.id.move_view_container_rl, "field 'mMoveViewContainerRl'", RelativeLayout.class);
    target.mIncludeRl = Utils.findRequiredViewAsType(source, R.id.include_rl, "field 'mIncludeRl'", RelativeLayout.class);
    target.mDefaultRecordIv = Utils.findRequiredView(source, R.id.default_record_iv, "field 'mDefaultRecordIv'");
    target.mBottomInputLl = Utils.findRequiredViewAsType(source, R.id.bottom_input_ll, "field 'mBottomInputLl'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mNewStoryEt = null;
    target.mPhotoSelectionIv = null;
    target.mSwitchCameraV = null;
    target.mSwitchFlashV = null;
    target.mLlBottom = null;
    target.mPlaceholderTv = null;
    target.mIamgeView = null;
    target.mDragIv = null;
    target.mMoveViewContainerRl = null;
    target.mIncludeRl = null;
    target.mDefaultRecordIv = null;
    target.mBottomInputLl = null;

    this.target = null;
  }
}
