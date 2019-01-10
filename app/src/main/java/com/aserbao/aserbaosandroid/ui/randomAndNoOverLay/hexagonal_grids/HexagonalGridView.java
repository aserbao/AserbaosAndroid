package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aserbao.aserbaosandroid.AserbaoApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/10 10:50 AM
 * @email: 1142803753@qq.com
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.ui.randomAndNoOverLay.hexagonal_grids
 * @Copyright: 个人版权所有
 */
public class HexagonalGridView extends View {

    private Random random;
    private Paint paint;

    public HexagonalGridView(Context context) {
        this(context,null);
    }

    public HexagonalGridView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HexagonalGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    private Map<Integer,LinkedList<HexPoint>> cuurScreenMap = new LinkedHashMap<>();


    public void start(){
        init();
    }
    private void init() {
        cuurScreenMap.clear();
        random = new Random();
        int screenWidth = AserbaoApplication.screenWidth;
        int screenHeight = AserbaoApplication.screenHeight;
        for (int i = 0; i < 1000; i++) {
            float[] aFloat = new float[3];
            aFloat[0] = screenWidth/4;
            aFloat[1] = screenHeight/4;
            aFloat[2] = 10;
            cal(aFloat,screenWidth,screenHeight);
        }
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        invalidate();
    }

    private void cal(float[] aFloat,int maxWidth,int maxHeight) {
        float x = aFloat[0];
        float y = aFloat[1];
        float radius = aFloat[2];

        Iterator<Map.Entry<Integer, LinkedList<HexPoint>>> entryIterator = cuurScreenMap.entrySet().iterator();
        while(entryIterator.hasNext()){
            Map.Entry<Integer, LinkedList<HexPoint>> linkedListEntry = entryIterator.next();
            LinkedList<HexPoint> hexPointLinkedList = linkedListEntry.getValue();
            for (HexPoint hexPoint : hexPointLinkedList) {
               if(radius + hexPoint.radius > Math.sqrt(Math.pow((hexPoint.x  - x),2) +  Math.pow((hexPoint.y  - y),2))){
                   addScreenHexPoint(hexPoint);
                   return;
               };
            }
        }

        HexPoint hexPoint = new HexPoint((int) x, (int) y, (int) radius,true);
        hexPoint.type = cuurScreenMap.size();
        hexPoint.hexPoint = hexPoint;
        addScreenHexPoint(hexPoint);
    }

    public void addScreenHexPoint(HexPoint hexPoint){
        int type = hexPoint.type;
        LinkedList<HexPoint> linkedList = new LinkedList<>();
        if (cuurScreenMap.containsKey(type)) {
            linkedList = cuurScreenMap.get(type);
            linkedList = calcNextScreenLocation(linkedList);
        }else{
            linkedList.add(hexPoint);
        }
        cuurScreenMap.put(type,linkedList);
        Log.e(TAG, "addScreenHexPoint: " + cuurScreenMap.size() );
    }

    private static final String TAG = "HexagonalGridView";

    public LinkedList<HexPoint> calcNextScreenLocation(LinkedList<HexPoint> hexPoints){
        HexPoint centerFisrt = hexPoints.getFirst().getHexPoint();
        int size = hexPoints.size();
        float[] childPoint = centerFisrt.getChildPoint(size);
        HexPoint hexPoint = new HexPoint((int)childPoint[0], (int)childPoint[1], (int)childPoint[2], false);
        hexPoint.type = centerFisrt.type;
        hexPoints.add(hexPoint);
        return hexPoints;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator<Map.Entry<Integer, LinkedList<HexPoint>>> entryIterator = cuurScreenMap.entrySet().iterator();
        int num = 0;
        if (entryIterator.hasNext()) {
            Map.Entry<Integer, LinkedList<HexPoint>> next = entryIterator.next();
            LinkedList<HexPoint> value = next.getValue();
            for (HexPoint hexPoint : value) {
                int x = hexPoint.x;
                int y = hexPoint.y;
                paint.setColor(Color.RED);
                canvas.drawCircle(x, y,hexPoint.radius * 2,paint);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setColor(Color.WHITE);
                paint.setTextSize(12);
                canvas.drawText(String.valueOf(num),x,y,paint);
                num++;
            }
        }
    }
}
