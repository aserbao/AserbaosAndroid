package com.aserbao.aserbaosandroid.ui.customView.selector.data;

import com.aserbao.aserbaosandroid.R;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.RoleConfig;
import com.aserbao.aserbaosandroid.ui.customView.selector.beans.SelColorBean;

import java.util.ArrayList;

/**
 * @Created time:2021/6/2 6:09 PM
 * @author: aserbao
 * @description: 数据提供类
 **/
public class DataProviderFactory {
    public static int[] SEL_FEATURE = {
        R.drawable.feature_sel_custom,
        R.drawable.place_holder,
        R.drawable.mm_1,
        R.drawable.mm_2,
        R.drawable.mm_3,
        R.drawable.mm_4,
        R.drawable.mm_5,
        R.drawable.mm_6,
        R.drawable.mm_7
    };


    public enum ROLE_FEATURE{
        FACE(0),
        HAIR(1),
        EYE(2),
        BROW(3),
        MOUTH(4),
        NOSE(5);

        private int id;

        ROLE_FEATURE(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    /**
     * 角色特征项数据
     */
    public static ArrayList<RoleConfig> mRoleList = new ArrayList<>();
    static {
        mRoleList.add(new RoleConfig(ROLE_FEATURE.FACE, "脸","",""));
        mRoleList.add(new RoleConfig(ROLE_FEATURE.HAIR, "头发","",""));
        mRoleList.add(new RoleConfig(ROLE_FEATURE.EYE, "眼睛","",""));
        mRoleList.add(new RoleConfig(ROLE_FEATURE.BROW, "眉毛","",""));
        mRoleList.add(new RoleConfig(ROLE_FEATURE.MOUTH,"嘴巴","",""));
        mRoleList.add(new RoleConfig(ROLE_FEATURE.NOSE, "鼻子","",""));
    }


    static String[] SEL_COLOR = {
        "#FFFFFF","#FFFFF0","#FFFFE0","#FFFF00",
        "#FFFAFA","#FFFAF0","#FFFACD","#FFF8DC",
        "#FFF68F","#FFF5EE","#FFF0F5","#FFEFDB",
        "#FFEFD5","#FFEC8B","#FFEBCD","#FFE7BA",
        "#FFE4E1","#FFE4C4","#FFE4B5","#FFE1FF",
        "#FFDEAD","#FFDAB9","#FFD700","#FFD39B",
        "#FFC1C1","#FFC125","#FFC0CB","#FFBBFF",
        "#FFB90F","#FFB6C1","#FFB5C5","#FFAEB9",
        "#FFA54F","#FFA500","#FFA07A","#FF8C69"};
    /**
     * 颜色选择器颜色
     */
    public static ArrayList<SelColorBean> SELCOLORBEANS = new ArrayList<>();
    static {
        for (String s : SEL_COLOR) {
            SELCOLORBEANS.add(new SelColorBean(s));
        }
    }


    //---------------------------- RV type  ----------------------------
    //头部布局
    public static final int HEAD_TYPE = 0;
    //尾部布局
    public static final int FOOT_TYPE = 1;
    //内容
    public static final int CONTENT_TYPE = 2;
    //纯文本
    public static final int ONLY_TITLE = 0;




}
