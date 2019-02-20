package com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.customTransition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.transition.PathMotion;
import android.transition.Transition;
import android.transition.TransitionPropagation;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/2/20 8:04 PM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.customTransition
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyTransition extends Transition {
    @Override
    public void captureStartValues(TransitionValues transitionValues) {

    }

    public MyTransition() {
        super();
    }

    public MyTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Transition setDuration(long duration) {
        return super.setDuration(duration);
    }

    @Override
    public long getDuration() {
        return super.getDuration();
    }

    @Override
    public Transition setStartDelay(long startDelay) {
        return super.setStartDelay(startDelay);
    }

    @Override
    public long getStartDelay() {
        return super.getStartDelay();
    }

    @Override
    public Transition setInterpolator(TimeInterpolator interpolator) {
        return super.setInterpolator(interpolator);
    }

    @Override
    public TimeInterpolator getInterpolator() {
        return super.getInterpolator();
    }

    @Override
    public String[] getTransitionProperties() {
        return super.getTransitionProperties();
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return super.createAnimator(sceneRoot, startValues, endValues);
    }

    @Override
    public void setMatchOrder(int... matches) {
        super.setMatchOrder(matches);
    }

    @Override
    public Transition addTarget(int targetId) {
        return super.addTarget(targetId);
    }

    @Override
    public Transition addTarget(String targetName) {
        return super.addTarget(targetName);
    }

    @Override
    public Transition addTarget(Class targetType) {
        return super.addTarget(targetType);
    }

    @Override
    public Transition removeTarget(int targetId) {
        return super.removeTarget(targetId);
    }

    @Override
    public Transition removeTarget(String targetName) {
        return super.removeTarget(targetName);
    }

    @Override
    public Transition excludeTarget(int targetId, boolean exclude) {
        return super.excludeTarget(targetId, exclude);
    }

    @Override
    public Transition excludeTarget(String targetName, boolean exclude) {
        return super.excludeTarget(targetName, exclude);
    }

    @Override
    public Transition excludeChildren(int targetId, boolean exclude) {
        return super.excludeChildren(targetId, exclude);
    }

    @Override
    public Transition excludeTarget(View target, boolean exclude) {
        return super.excludeTarget(target, exclude);
    }

    @Override
    public Transition excludeChildren(View target, boolean exclude) {
        return super.excludeChildren(target, exclude);
    }

    @Override
    public Transition excludeTarget(Class type, boolean exclude) {
        return super.excludeTarget(type, exclude);
    }

    @Override
    public Transition excludeChildren(Class type, boolean exclude) {
        return super.excludeChildren(type, exclude);
    }

    @Override
    public Transition addTarget(View target) {
        return super.addTarget(target);
    }

    @Override
    public Transition removeTarget(View target) {
        return super.removeTarget(target);
    }

    @Override
    public Transition removeTarget(Class target) {
        return super.removeTarget(target);
    }

    @Override
    public List<Integer> getTargetIds() {
        return super.getTargetIds();
    }

    @Override
    public List<View> getTargets() {
        return super.getTargets();
    }

    @Override
    public List<String> getTargetNames() {
        return super.getTargetNames();
    }

    @Override
    public List<Class> getTargetTypes() {
        return super.getTargetTypes();
    }

    @Override
    public TransitionValues getTransitionValues(View view, boolean start) {
        return super.getTransitionValues(view, start);
    }

    @Override
    public boolean isTransitionRequired(@androidx.annotation.Nullable @Nullable TransitionValues startValues, @androidx.annotation.Nullable @Nullable TransitionValues endValues) {
        return super.isTransitionRequired(startValues, endValues);
    }

    @Override
    public Transition addListener(TransitionListener listener) {
        return super.addListener(listener);
    }

    @Override
    public Transition removeListener(TransitionListener listener) {
        return super.removeListener(listener);
    }

    @Override
    public void setEpicenterCallback(EpicenterCallback epicenterCallback) {
        super.setEpicenterCallback(epicenterCallback);
    }

    @Override
    public EpicenterCallback getEpicenterCallback() {
        return super.getEpicenterCallback();
    }

    @Override
    public Rect getEpicenter() {
        return super.getEpicenter();
    }

    @Override
    public void setPathMotion(PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
    }

    @Override
    public PathMotion getPathMotion() {
        return super.getPathMotion();
    }

    @Override
    public void setPropagation(TransitionPropagation transitionPropagation) {
        super.setPropagation(transitionPropagation);
    }

    @Override
    public TransitionPropagation getPropagation() {
        return super.getPropagation();
    }

    @Override
    public boolean canRemoveViews() {
        return super.canRemoveViews();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Transition clone() {
        return super.clone();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {

    }
}
