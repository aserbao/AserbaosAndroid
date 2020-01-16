package com.aserbao.aserbaosandroid.aaSource.android.app.Fragment;

import android.view.View;

import com.aserbao.aserbaosandroid.aaSource.android.app.Activity.animation.activityOptions.share_module.AShareModuleActivity;
import com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment.ShareElementActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-16 11:08
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Fragment
 */
public class FragmentActivity extends BaseRecyclerViewActivity {
    public static int currentPosition;
    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("Fragment共享元素", ShareElementActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("Activity共享元素", AShareModuleActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
