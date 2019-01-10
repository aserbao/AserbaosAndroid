package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids;

import android.util.Log;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids_offical.Hex;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/10 11:10 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids
 * @Copyright: 个人版权所有
 */
public class HexPoint {
    int x;
    int y;
    int radius;
    int type;
    HexPoint hexPoint;
    boolean isCenterPoint =false;
    private List<float[]> mHexList = new ArrayList<>();
    private int cuurMaxLayer = 1;//当前计算的最大圈数，默认先计算一圈
    private int maxWidth,maxHeight;

    public HexPoint(int x, int y,int radius, boolean isCenterPoint) {
        this.x = x;
        this.y = y;
        maxWidth = AserbaoApplication.screenWidth;
        maxHeight = AserbaoApplication.screenHeight;
        this.radius = radius;
        this.isCenterPoint = isCenterPoint;
        if (isCenterPoint) {
            getChildScreen(cuurMaxLayer);
        }
    }

    public HexPoint getHexPoint() {
        return hexPoint;
    }

    public void setHexPoint(int type , HexPoint hexPoint) {
        this.type = type;
        this.hexPoint = hexPoint;
    }

    public void getChildScreen(int cuurLayer){
        mHexList.clear();
        Hex hex = new Hex(0, 0, 0);
        List<Hex> hexes = cubeSpiral(hex, cuurLayer);
        for (Hex hex1 : hexes) {
            int xC = hex1.q;
            int yC = hex1.s;
            int zC = hex1.r;
            if (Math.abs(xC) <= cuurLayer && Math.abs(yC) <= cuurLayer && Math.abs(zC) <= cuurLayer) {
                double screenX = xC * (float)radius * Math.sqrt(3) - yC * (float) radius * Math.sqrt(3) + x;
                double screenY = zC * (float)radius * 2 - xC * (float) radius - yC * (float)radius + y;
                if (screenX > radius && screenX < maxWidth -radius && screenY > radius && screenY < maxHeight - radius){
                    float[] floats = new float[3];
                    floats[0] = (float) screenX;
                    floats[1] = (float) screenY;
                    floats[2] = radius;
                    mHexList.add(floats);
                }
            }
        }

    }

    public float[] getChildPoint(int position) {
        if (!isCenterPoint){
            try {
                throw  new Exception("不是中心点");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (position >= mHexList.size()){
            cuurMaxLayer++;
            getChildScreen(cuurMaxLayer);
        }
        return mHexList.get(position);
    }


    public List<Hex> cubeSpiral(Hex hex,int radius){
        List<Hex> result = new ArrayList<>();
        result.add(hex);
        for (int i = 1; i <= radius; i++) {
            result = cubeRing(result,hex,radius);
        }
        return result;
    }
    public List<Hex> cubeRing(List<Hex> result ,Hex hex,int radius){
        hex = hex.add(hex.direction(4).scale(radius));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < radius; j++) {
                result.add(hex);
                hex = hex.neighbor(i);
            }
        }
        return result;
    }
}
