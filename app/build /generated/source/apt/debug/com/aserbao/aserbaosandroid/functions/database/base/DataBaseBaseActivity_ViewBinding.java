// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.database.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DataBaseBaseActivity_ViewBinding<T extends DataBaseBaseActivity> implements Unbinder {
  protected T target;

  private View view2131165196;

  private View view2131165194;

  private View view2131165193;

  private View view2131165195;

  @UiThread
  public DataBaseBaseActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mADataBaseSubmitEt = Utils.findRequiredViewAsType(source, R.id.a_database_submit_et, "field 'mADataBaseSubmitEt'", EditText.class);
    target.mADataBaseSearchEt = Utils.findRequiredViewAsType(source, R.id.a_green_dao_search_et, "field 'mADataBaseSearchEt'", EditText.class);
    target.mADataBaseRecyclerView = Utils.findRequiredViewAsType(source, R.id.a_data_base_recycler_view, "field 'mADataBaseRecyclerView'", RecyclerView.class);
    target.mADatabaseWhichMethodBtn = Utils.findRequiredViewAsType(source, R.id.a_database_which_method_btn, "field 'mADatabaseWhichMethodBtn'", Button.class);
    target.mADatabaseTimeShowTv = Utils.findRequiredViewAsType(source, R.id.a_database_time_show_tv, "field 'mADatabaseTimeShowTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.a_database_submit_btn, "method 'onViewClicked'");
    view2131165196 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.a_database_search_btn, "method 'onViewClicked'");
    view2131165194 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.a_database_delete_all, "method 'onViewClicked'");
    view2131165193 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.a_database_show_all_data_tv, "method 'onViewClicked'");
    view2131165195 = view;
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

    target.mADataBaseSubmitEt = null;
    target.mADataBaseSearchEt = null;
    target.mADataBaseRecyclerView = null;
    target.mADatabaseWhichMethodBtn = null;
    target.mADatabaseTimeShowTv = null;

    view2131165196.setOnClickListener(null);
    view2131165196 = null;
    view2131165194.setOnClickListener(null);
    view2131165194 = null;
    view2131165193.setOnClickListener(null);
    view2131165193 = null;
    view2131165195.setOnClickListener(null);
    view2131165195 = null;

    this.target = null;
  }
}
