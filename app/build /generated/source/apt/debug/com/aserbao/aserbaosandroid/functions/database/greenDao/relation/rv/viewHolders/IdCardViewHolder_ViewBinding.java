// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IdCardViewHolder_ViewBinding<T extends IdCardViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public IdCardViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mGreenDaoIdCardNameTv = Utils.findRequiredViewAsType(source, R.id.green_dao_id_card_name_tv, "field 'mGreenDaoIdCardNameTv'", TextView.class);
    target.mGreenDaoIdCardNoTv = Utils.findRequiredViewAsType(source, R.id.green_dao_id_card_no_tv, "field 'mGreenDaoIdCardNoTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mGreenDaoIdCardNameTv = null;
    target.mGreenDaoIdCardNoTv = null;

    this.target = null;
  }
}
