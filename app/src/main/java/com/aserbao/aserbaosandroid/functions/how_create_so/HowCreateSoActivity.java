package com.aserbao.aserbaosandroid.functions.how_create_so;

import android.os.Bundle;
import android.view.View;

import com.aserbao.aserbaosandroid.comon.base.BaseRecyclerViewActivity;
import com.aserbao.aserbaosandroid.functions.how_create_so.useCmake.UseCMakeBuildSoActivity;
import com.aserbao.aserbaosandroid.functions.how_create_so.useNdkBuild.UseNdkBuildActivity;
import com.aserbao.aserbaosandroid.comon.base.beans.BaseRecyclerBean;

public class HowCreateSoActivity extends BaseRecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initGetData() {
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过ndk-build的方式编译so文件", UseNdkBuildActivity.class));
        mBaseRecyclerBean.add(new BaseRecyclerBean("通过CMake的方式编译so文件", UseCMakeBuildSoActivity.class));
    }

    @Override
    public void itemClickBack(View view, int position, boolean isLongClick, int comeFrom) {

    }
}
