// Generated code from Butter Knife. Do not modify!
package com.aserbao.aserbaosandroid.functions.sensors.testSensorData;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.aserbao.aserbaosandroid.AUtils.AUI.layout.FlowLayout;
import com.aserbao.aserbaosandroid.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestSensorDataActivity_ViewBinding<T extends TestSensorDataActivity> implements Unbinder {
  protected T target;

  private View view2131165686;

  private View view2131165691;

  private View view2131165692;

  private View view2131165688;

  private View view2131165689;

  private View view2131165693;

  private View view2131165695;

  private View view2131165694;

  private View view2131165687;

  private View view2131165690;

  @UiThread
  public TestSensorDataActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mSensorFlowLayout = Utils.findRequiredViewAsType(source, R.id.sensor_flow_layout, "field 'mSensorFlowLayout'", FlowLayout.class);
    target.mSensorTypeTv = Utils.findRequiredViewAsType(source, R.id.sensor_type_tv, "field 'mSensorTypeTv'", TextView.class);
    target.mSensorInfoTv = Utils.findRequiredViewAsType(source, R.id.sensor_info_tv, "field 'mSensorInfoTv'", TextView.class);
    target.mSensorRealTimeInfoTv = Utils.findRequiredViewAsType(source, R.id.sensor_real_time_info_tv, "field 'mSensorRealTimeInfoTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.type_accelerometer, "method 'onViewClicked'");
    view2131165686 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_magnetic_field, "method 'onViewClicked'");
    view2131165691 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_orientation, "method 'onViewClicked'");
    view2131165692 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_gyroscope, "method 'onViewClicked'");
    view2131165688 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_light, "method 'onViewClicked'");
    view2131165689 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_pressure, "method 'onViewClicked'");
    view2131165693 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_temperature, "method 'onViewClicked'");
    view2131165695 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_proximity, "method 'onViewClicked'");
    view2131165694 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_gravity, "method 'onViewClicked'");
    view2131165687 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.type_linear_acceleration, "method 'onViewClicked'");
    view2131165690 = view;
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

    target.mSensorFlowLayout = null;
    target.mSensorTypeTv = null;
    target.mSensorInfoTv = null;
    target.mSensorRealTimeInfoTv = null;

    view2131165686.setOnClickListener(null);
    view2131165686 = null;
    view2131165691.setOnClickListener(null);
    view2131165691 = null;
    view2131165692.setOnClickListener(null);
    view2131165692 = null;
    view2131165688.setOnClickListener(null);
    view2131165688 = null;
    view2131165689.setOnClickListener(null);
    view2131165689 = null;
    view2131165693.setOnClickListener(null);
    view2131165693 = null;
    view2131165695.setOnClickListener(null);
    view2131165695 = null;
    view2131165694.setOnClickListener(null);
    view2131165694 = null;
    view2131165687.setOnClickListener(null);
    view2131165687 = null;
    view2131165690.setOnClickListener(null);
    view2131165690 = null;

    this.target = null;
  }
}
