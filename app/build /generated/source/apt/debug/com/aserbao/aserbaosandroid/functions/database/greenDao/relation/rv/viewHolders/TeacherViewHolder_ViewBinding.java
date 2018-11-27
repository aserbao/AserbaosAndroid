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

public class TeacherViewHolder_ViewBinding<T extends TeacherViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TeacherViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mGreenDaoTeacherNameTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_name_tv, "field 'mGreenDaoTeacherNameTv'", TextView.class);
    target.mGreenDaoTeacherIdTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_id_tv, "field 'mGreenDaoTeacherIdTv'", TextView.class);
    target.mGreenDaoTeacherNoTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_no_tv, "field 'mGreenDaoTeacherNoTv'", TextView.class);
    target.mGreenDaoTeacherAgeTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_age_tv, "field 'mGreenDaoTeacherAgeTv'", TextView.class);
    target.mGreenDaoTeacherSexTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_sex_tv, "field 'mGreenDaoTeacherSexTv'", TextView.class);
    target.mGreenDaoTeacherTelTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_tel_tv, "field 'mGreenDaoTeacherTelTv'", TextView.class);
    target.mGreenDaoTeacherSchoolNameTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_school_name_tv, "field 'mGreenDaoTeacherSchoolNameTv'", TextView.class);
    target.mGreenDaoTeacherSubjectTv = Utils.findRequiredViewAsType(source, R.id.green_dao_teacher_subject_tv, "field 'mGreenDaoTeacherSubjectTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mGreenDaoTeacherNameTv = null;
    target.mGreenDaoTeacherIdTv = null;
    target.mGreenDaoTeacherNoTv = null;
    target.mGreenDaoTeacherAgeTv = null;
    target.mGreenDaoTeacherSexTv = null;
    target.mGreenDaoTeacherTelTv = null;
    target.mGreenDaoTeacherSchoolNameTv = null;
    target.mGreenDaoTeacherSubjectTv = null;

    this.target = null;
  }
}
