package com.aserbao.aserbaosandroid.aaSource.android.app.Fragment;

import com.aserbao.aserbaosandroid.aaSource.android.app.Fragment.ShareElementFragment.ShareElementActivity;
import com.aserbao.aserbaosandroid.comon.base.BaseActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.ClassBean;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019-08-16 11:08
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaSource.android.app.Fragment
 */
public class FragmentActivity extends BaseActivity {
    @Override
    public void initGetData() {
        mClassBeen.add(new ClassBean("Fragment共享元素", ShareElementActivity.class));
    }
}
