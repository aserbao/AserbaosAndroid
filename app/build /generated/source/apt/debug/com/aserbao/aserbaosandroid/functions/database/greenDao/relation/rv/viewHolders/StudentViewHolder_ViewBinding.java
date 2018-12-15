// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.database.greenDao.relation.rv.viewHolders;

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

public class StudentViewHolder_ViewBinding<T extends StudentViewHolder> implements Unbinder {
  protected T target;

  private View view2131165428;

  private View view2131165430;

  @UiThread
  public StudentViewHolder_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.green_dao_student_name_tv, "field 'mGreenDaoStudentNameTv' and method 'onViewClicked'");
    target.mGreenDaoStudentNameTv = Utils.castView(view, R.id.green_dao_student_name_tv, "field 'mGreenDaoStudentNameTv'", TextView.class);
    view2131165428 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mGreenDaoStudentIdTv = Utils.findRequiredViewAsType(source, R.id.green_dao_student_id_tv, "field 'mGreenDaoStudentIdTv'", TextView.class);
    target.mGreenDaoStudentNoTv = Utils.findRequiredViewAsType(source, R.id.green_dao_student_no_tv, "field 'mGreenDaoStudentNoTv'", TextView.class);
    target.mGreenDaoStudentAgeTv = Utils.findRequiredViewAsType(source, R.id.green_dao_student_age_tv, "field 'mGreenDaoStudentAgeTv'", TextView.class);
    target.mGreenDaoStudentSexTv = Utils.findRequiredViewAsType(source, R.id.green_dao_student_sex_tv, "field 'mGreenDaoStudentSexTv'", TextView.class);
    target.mGreenDaoStudentTelTv = Utils.findRequiredViewAsType(source, R.id.green_dao_student_tel_tv, "field 'mGreenDaoStudentTelTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.green_dao_student_school_name_tv, "field 'mGreenDaoStudentSchoolNameTv' and method 'onViewClicked'");
    target.mGreenDaoStudentSchoolNameTv = Utils.castView(view, R.id.green_dao_student_school_name_tv, "field 'mGreenDaoStudentSchoolNameTv'", TextView.class);
    view2131165430 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mGreenDaoStudentGradeTv = Utils.findRequiredViewAsType(source, R.id.green_dao_student_grade_tv, "field 'mGreenDaoStudentGradeTv'", TextView.class);
    target.mGreenDaoStudentAddressTv = Utils.findRequiredViewAsType(source, R.id.green_dao_student_address_tv, "field 'mGreenDaoStudentAddressTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mGreenDaoStudentNameTv = null;
    target.mGreenDaoStudentIdTv = null;
    target.mGreenDaoStudentNoTv = null;
    target.mGreenDaoStudentAgeTv = null;
    target.mGreenDaoStudentSexTv = null;
    target.mGreenDaoStudentTelTv = null;
    target.mGreenDaoStudentSchoolNameTv = null;
    target.mGreenDaoStudentGradeTv = null;
    target.mGreenDaoStudentAddressTv = null;

    view2131165428.setOnClickListener(null);
    view2131165428 = null;
    view2131165430.setOnClickListener(null);
    view2131165430 = null;

    this.target = null;
  }
}
