package com.aserbao.aserbaosandroid.ui.customView.selector.vm;

import androidx.lifecycle.MutableLiveData;
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
    public static final int FEATURE_DEFAULT_STATUS = 0;
    public static final int FEATURE_MORE_COLOR_STATUS = 1;

    public  MutableLiveData<Integer> featureStatus = new MutableLiveData<>(FEATURE_DEFAULT_STATUS);

    /**
     * 修改状态
     * @param status
     * @see FEATURE_DEFAULT_STATUS
     * @see FEATURE_MORE_COLOR_STATUS
     */
    public void changeFeatureStatus(int status){
        featureStatus.setValue(status);
    }

    public ArrayList<RoleConfig> roleConfigure = mRoleList;


    public RoleConfig getRoleConfig(int position){
        if (position < roleConfigure.size()){
            return roleConfigure.get(position);
        }
        return null;
    }
}
