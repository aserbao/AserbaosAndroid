// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AllDataViewHolder_ViewBinding<T extends AllDataViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public AllDataViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mGreenDaoAllDataNameTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_name_tv, "field 'mGreenDaoAllDataNameTv'", TextView.class);
    target.mGreenDaoAllDataIdTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_id_tv, "field 'mGreenDaoAllDataIdTv'", TextView.class);
    target.mGreenDaoAllDataNoTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_no_tv, "field 'mGreenDaoAllDataNoTv'", TextView.class);
    target.mGreenDaoAllDataAgeTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_age_tv, "field 'mGreenDaoAllDataAgeTv'", TextView.class);
    target.mGreenDaoAllDataSexTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_sex_tv, "field 'mGreenDaoAllDataSexTv'", TextView.class);
    target.mGreenDaoAllDataTelTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_tel_tv, "field 'mGreenDaoAllDataTelTv'", TextView.class);
    target.mGreenDaoAllDataSchoolNameTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_school_name_tv, "field 'mGreenDaoAllDataSchoolNameTv'", TextView.class);
    target.mGreenDaoAllDataGradeTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_grade_tv, "field 'mGreenDaoAllDataGradeTv'", TextView.class);
    target.mGreenDaoAllDataAddressTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_address_tv, "field 'mGreenDaoAllDataAddressTv'", TextView.class);
    target.mGreenDaoAllDataIdCardTv = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_id_card_tv, "field 'mGreenDaoAllDataIdCardTv'", TextView.class);
    target.mGreenDaoAllDataTeachersLl = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_teachers_ll, "field 'mGreenDaoAllDataTeachersLl'", LinearLayout.class);
    target.mGreenDaoAllDataCreditCardLl = Utils.findRequiredViewAsType(source, R.id.green_dao_all_data_credit_card_ll, "field 'mGreenDaoAllDataCreditCardLl'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mGreenDaoAllDataNameTv = null;
    target.mGreenDaoAllDataIdTv = null;
    target.mGreenDaoAllDataNoTv = null;
    target.mGreenDaoAllDataAgeTv = null;
    target.mGreenDaoAllDataSexTv = null;
    target.mGreenDaoAllDataTelTv = null;
    target.mGreenDaoAllDataSchoolNameTv = null;
    target.mGreenDaoAllDataGradeTv = null;
    target.mGreenDaoAllDataAddressTv = null;
    target.mGreenDaoAllDataIdCardTv = null;
    target.mGreenDaoAllDataTeachersLl = null;
    target.mGreenDaoAllDataCreditCardLl = null;

    this.target = null;
  }
}
