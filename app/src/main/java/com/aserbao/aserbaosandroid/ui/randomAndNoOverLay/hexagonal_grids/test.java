package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/10 7:33 PM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids
 * @Copyright: 个人版权所有
 */


public class test {

    @SerializedName("data")
    private List<DataBean> data;

    
    public static class DataBean {
        @SerializedName("girl")
        private GirlBean girl;
        @SerializedName("boy")
        private BoyBean boy;
        @SerializedName("name")
        private String name;
        @SerializedName("type")
        private TypeBean type;
        @SerializedName("colorBeans")
        private List<ColorBeansBean> colorBeans;
        @SerializedName("featureBeans")
        private List<FeatureBeansBean> featureBeans;
        @SerializedName("isSel")
        private Boolean isSel;
        @SerializedName("isSupportColor")
        private Boolean isSupportColor;

        
        public static class GirlBean {
            @SerializedName("colorBeans")
            private List<ColorBeansBean> colorBeans;
            @SerializedName("featureBeans")
            private List<FeatureBeansBean> featureBeans;
        }

        
        public static class BoyBean {
            @SerializedName("colorBeans")
            private List<ColorBeansBean> colorBeans;
            @SerializedName("featureBeans")
            private List<FeatureBeansBean> featureBeans;

        }

        
        public static class TypeBean {
            @SerializedName("feature_id")
            private Integer featureId;
            @SerializedName("change_color_id")
            private Integer changeColorId;
            @SerializedName("isSupportCustomize")
            private Boolean isSupportCustomize;
        }

        
        public static class ColorBeansBean {
            @SerializedName("a")
            private Integer a;
            @SerializedName("b")
            private Double b;
            @SerializedName("g")
            private Double g;
            @SerializedName("r")
            private Double r;
        }

        public static class FeatureBeansBean {
            @Nullable
            @SerializedName("key")
            private String key;
            @SerializedName("path")
            private String path;
            @SerializedName("icon")
            private String icon;
        }

    }
}
