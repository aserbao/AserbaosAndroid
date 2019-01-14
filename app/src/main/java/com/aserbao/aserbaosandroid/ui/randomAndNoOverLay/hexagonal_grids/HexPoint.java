package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids;

import android.util.Log;

import com.aserbao.aserbaosandroid.AserbaoApplication;
import com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids_offical.Hex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private int cuurWhichLayer = 1;//当前中心点已经算到第几圈了，默认先计算一圈
    private int maxWidth,maxHeight;

    public HexPoint(int x, int y,int radius, boolean isCenterPoint) {
        this.x = x;
        this.y = y;
        maxWidth = AserbaoApplication.screenWidth;
        maxHeight = AserbaoApplication.screenHeight;
        this.radius = radius;
        this.isCenterPoint = isCenterPoint;
        mCorrespondenceMap.put(0,0);
    }

    public HexPoint getHexPoint() {
        return hexPoint;
    }

    public void setHexPoint(int type , HexPoint hexPoint) {
        this.type = type;
        this.hexPoint = hexPoint;
    }

    private static final String TAG = "HexPoint";
    private int cycleTime = 0; //循环次数，理论不超过1;
    public float[] getChildScreen(int cuurLayer,int lastPosition,int position,Map<Integer,LinkedList<HexPoint>> cuurMap){
        Hex hex = new Hex(0, 0, 0);
        List<Hex> hexes = cubeSpiral(hex, cuurLayer);
            for (int i = 0; i < hexes.size(); i++) {
                if (i <= lastPosition) {
                    continue;
                }
                Hex hex1 = hexes.get(i);
                int xC = hex1.q;
                int yC = hex1.s;
                int zC = hex1.r;
                if (Math.abs(xC) <= cuurLayer && Math.abs(yC) <= cuurLayer && Math.abs(zC) <= cuurLayer) {
                    double screenX = xC * (float) radius * Math.sqrt(3) - yC * (float) radius * Math.sqrt(3) + x;
                    double screenY = zC * (float) radius * 2 - xC * (float) radius - yC * (float) radius + y;
                    if (screenX > 1070){
                        Log.e(TAG, "getChildScreen: " + screenX +" screenY =" + screenY );
                    }
                    if (screenX > radius && screenX < maxWidth - radius && screenY > radius && screenY < maxHeight - radius) {
                        float[] floats = new float[3];
                        floats[0] = (float) screenX;
                        floats[1] = (float) screenY;
                        floats[2] = radius;
                        if (!calIsOverLayout(floats, cuurMap)) {
                            Log.e(TAG, "getChildScreen2: " + screenX +" screenY =" + screenY );
                            cuurWhichLayer = cuurLayer;
                            mCorrespondenceMap.put(position, i);
                            return floats;
                        }
                    }
                    lastPosition++; // 用来记录不用计算的点
                }
            }
        cuurWhichLayer++;
        cycleTime++;
        Log.e(TAG, "getChildScreen: " + "当前点，即：position =  " + position  +" 一共计算了 =" + cycleTime + " 次");
        return getChildScreen(cuurWhichLayer,lastPosition,position,cuurMap);
    }

    /**
     * @param floats    输入屏幕坐标点
     * @param listMap   屏幕上已存点
     * @return  是否和屏幕点有重合  true 表示有重合，该点不能用， false 表示没有任何重合点，该点可用
     */
    public boolean calIsOverLayout(float[] floats,Map<Integer,LinkedList<HexPoint>> listMap){
        Iterator<Map.Entry<Integer, LinkedList<HexPoint>>> iterator = listMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, LinkedList<HexPoint>> next = iterator.next();
            if (next.getKey() != type) {
                LinkedList<HexPoint> value = next.getValue();
                for (HexPoint hexPoint : value) {
                    double v = Math.pow((hexPoint.x - floats[0]), 2) + Math.pow((hexPoint.y - floats[1]), 2);
                    if(floats[2] + hexPoint.radius > Math.sqrt(v)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public Map<Integer,Integer> mCorrespondenceMap = new HashMap<>(); // 第一个参数为linkedList中的位置，第二个参数为六边形中有效位置
    public float[] getChildPoint(int position, Map<Integer,LinkedList<HexPoint>> cuurMap) {
        if (!isCenterPoint){
            try {
                throw  new Exception("不是中心点");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int lastPosition = position - 1;
        int lastHexPosition = (int)mCorrespondenceMap.get(lastPosition);// 上个hex grid 中对应的位置
        return getChildScreen(cuurWhichLayer,lastHexPosition,position,cuurMap);
    }


    public List<Hex> cubeSpiral(Hex hex,int radius){
        List<Hex> result = new ArrayList<>();
        result.add(hex);
        for (int i = 0; i < radius; i++) {
            result = cubeRing(result,hex,i);
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
