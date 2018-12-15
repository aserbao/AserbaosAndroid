// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.database.greenDao.relation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GreenDaoRelationActivity_ViewBinding<T extends GreenDaoRelationActivity> implements Unbinder {
  protected T target;

  private View view2131165669;

  private View view2131165670;

  private View view2131165595;

  private View view2131165590;

  private View view2131165591;

  private View view2131165596;

  private View view2131165589;

  @UiThread
  public GreenDaoRelationActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mToOneOneReyclerView = Utils.findRequiredViewAsType(source, R.id.to_one_one_reycler_view, "field 'mToOneOneReyclerView'", RecyclerView.class);
    target.mToOneTwoReyclerView = Utils.findRequiredViewAsType(source, R.id.to_one_two_reycler_view, "field 'mToOneTwoReyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.to_one_add_data_btn, "method 'onViewClicked'");
    view2131165669 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.to_one_add_teacher_btn, "method 'onViewClicked'");
    view2131165670 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.show_student_btn, "method 'onViewClicked'");
    view2131165595 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.show_credit_card_btn, "method 'onViewClicked'");
    view2131165590 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.show_id_card_btn, "method 'onViewClicked'");
    view2131165591 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.show_teacher_btn, "method 'onViewClicked'");
    view2131165596 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.show_all_data_btn, "method 'onViewClicked'");
    view2131165589 = view;
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

    target.mToOneOneReyclerView = null;
    target.mToOneTwoReyclerView = null;

    view2131165669.setOnClickListener(null);
    view2131165669 = null;
    view2131165670.setOnClickListener(null);
    view2131165670 = null;
    view2131165595.setOnClickListener(null);
    view2131165595 = null;
    view2131165590.setOnClickListener(null);
    view2131165590 = null;
    view2131165591.setOnClickListener(null);
    view2131165591 = null;
    view2131165596.setOnClickListener(null);
    view2131165596 = null;
    view2131165589.setOnClickListener(null);
    view2131165589 = null;

    this.target = null;
  }
}
