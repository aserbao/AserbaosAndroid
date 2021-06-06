package com.aserbao.aserbaosandroid.ui.customView.selector.vm;

import androidx.lifecycle.ViewModel;

import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;

import java.util.ArrayList;

import static com.aserbao.aserbaosandroid.ui.customView.selector.data.DataProviderFactory.mRoleList;

/**
 * @Created time:2021/6/6 5:18 PM
 * @author: aserbao
 * @description:
 **/
public class FeatureConfigureVM extends ViewModel {
    public ArrayList<RoleConfig> roleConfigure = mRoleList;


    public RoleConfig getRoleConfig(int position){
        if (position < roleConfigure.size()){
            return roleConfigure.get(position);
        }
        return null;
    }
}
