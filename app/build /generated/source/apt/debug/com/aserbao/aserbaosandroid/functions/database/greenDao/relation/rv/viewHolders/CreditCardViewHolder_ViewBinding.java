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

public class CreditCardViewHolder_ViewBinding<T extends CreditCardViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public CreditCardViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mGreenDaoCreditCardNameTv = Utils.findRequiredViewAsType(source, R.id.green_dao_credit_card_name_tv, "field 'mGreenDaoCreditCardNameTv'", TextView.class);
    target.mGreenDaoCreditCardIdTv = Utils.findRequiredViewAsType(source, R.id.green_dao_credit_card_id_tv, "field 'mGreenDaoCreditCardIdTv'", TextView.class);
    target.mGreenDaoCreditCardWhichBankTv = Utils.findRequiredViewAsType(source, R.id.green_dao_credit_card_which_bank_tv, "field 'mGreenDaoCreditCardWhichBankTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mGreenDaoCreditCardNameTv = null;
    target.mGreenDaoCreditCardIdTv = null;
    target.mGreenDaoCreditCardWhichBankTv = null;

    this.target = null;
  }
}
