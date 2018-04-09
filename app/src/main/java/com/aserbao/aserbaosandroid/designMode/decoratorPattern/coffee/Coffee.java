package com.aserbao.aserbaosandroid.designMode.decoratorPattern.coffee;

/**
 * Created by aserbao on 2018 2018/4/4.23:42
 * Email:aserbao@163.com
 * weixin: aserbao
 */

public class Coffee extends Material{
    private final static int CAPPUCCIO = 0;
    private final static int LATTE = 1;
    private final static int DARK = 2;
    private final static int HOUSE = 3;

    private Material mMaterial;
    private int type = 0 ;

    public Coffee(Material material, int type) {
        mMaterial = material;
        this.type = type;
    }
    @Override
    public int cost() {
        int cuurCost = 0;
        switch (type){
            case CAPPUCCIO:
                cuurCost = 22;
                break;
            case LATTE:
                cuurCost = 25;
                break;
            case DARK:
                cuurCost = 30;
                break;
            case HOUSE:
                cuurCost = 35;
                break;
        }
        if (mMaterial != null) {
            return mMaterial.cost() + cuurCost;
        }else{
            return cuurCost;
        }
    }

    @Override
    public String description() {
        String  cuurDescription = "";
        switch (type){
            case CAPPUCCIO:
                cuurDescription = "卡布奇洛";
                break;
            case LATTE:
                cuurDescription = "拿铁";
                break;
            case DARK:
                cuurDescription = "烘焙";
                break;
            case HOUSE:
                cuurDescription = "House";
                break;
        }
        if (mMaterial != null) {
            return  mMaterial.description() + cuurDescription ;
        }else{
            return cuurDescription;
        }
    }
}
