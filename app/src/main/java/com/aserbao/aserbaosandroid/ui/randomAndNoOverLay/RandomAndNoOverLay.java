package com.aserbao.aserbaosandroid.ui.randomAndNoOverLay;

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
import java.util.List;
import java.util.Random;

import static java.lang.Math.PI;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/1/9 11:13 AM
 * @email: this is empty email
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.functions.randomAndNoOverLay
 * @Copyright: 个人版权所有
 */
public class RandomAndNoOverLay extends View{

    private Paint paint;

    public RandomAndNoOverLay(Context context) {
        this(context,null);
    }

    public RandomAndNoOverLay(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RandomAndNoOverLay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private List<RandomPoint> randomPointsList = new ArrayList<>();
//    private List<RandomPoint> finalDrawPointsList = new ArrayList<>();

    private void init() {

    }

    private static final String TAG = "RandomAndNoOverLay";

    private List<float[]> mDrawFlat = new ArrayList<>();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (RandomPoint randomPoint : randomPointsList) {
            paint.setColor(Color.RED);
            int x = randomPoint.x;
            int y = randomPoint.y;
            canvas.drawCircle(x, y,randomPoint.radius,paint);
            String value = String.valueOf(randomPoint.color);
            paint.setColor(Color.WHITE);
            paint.setTextSize(15);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(String.valueOf(value),x,y,paint);
        }
    }
    private  int calTime = 0;
    public float[] cal(int x, int y,int radius,int maxWidth,int maxHeight){
        float[] minFloat = new float[3];
        minFloat[0] = x;
        minFloat[1] = y;
        minFloat[2] = radius;
        double minSqrt =-1;
        for (float[] floats : mDrawFlat) {
            double sqrt = Math.sqrt(Math.pow(Math.abs(floats[0] - x), 2) + Math.pow(Math.abs(floats[1] - y), 2));
            if (sqrt < 2 * radius){
                minSqrt = sqrt;
                minFloat = floats;
            }
        }
        if (minSqrt != -1){
            calTime ++ ;
            float minX = minFloat[0];
            float minY = minFloat[1];
            double addX = 0,addY = 0;
            if (minSqrt != 0){
                addX = -(x - minX) * ((float) (2 * radius) / minSqrt);
                addY = (y - minY) * ((float) (2 * radius) / minSqrt);
            }else{
               addY = 2 * radius;
            }
            x = (int) (minX + addX);
            y = (int) (minY + addY);
            if (y > maxHeight - radius || y < radius||x > maxWidth - radius || x < radius) {
                x = new Random().nextInt(maxWidth - 2 * radius) + radius;
                y = new Random().nextInt(maxHeight - 2 * radius) + radius;
            }
            Log.e(TAG, "cal: " );
            return cal(x,y,radius,maxWidth,maxHeight);
        }else{
            return minFloat;
        }
    }

    public void start() {
        Random random = new Random();
        for (int i = 0; i <3; i++) {
            int x = random.nextInt(AserbaoApplication.screenWidth / 2);
            int y = random.nextInt(AserbaoApplication.screenHeight / 2);
//            int radius = random.nextInt(30 )+ 20;
           /* x =  100+ i * 13;
            y =  100 + i  *13 ;*/
            int radius = 20;
            calTime = 0;
            float[] floats = cal(x, y, radius, AserbaoApplication.screenWidth - 100, AserbaoApplication.screenHeight -100);
            Log.e(TAG, "start: 第" +i+ "个数据 x = " + x + " y = " + y+ "计算了 "+ calTime+ "次" );
            mDrawFlat.add(floats);
            Log.e(TAG, "init: " + floats.toString() );
            randomPointsList.add(new RandomPoint((int)floats[0],(int)floats[1],radius,i));
//            randomPointsList.add(new RandomPoint(x,y,radius,Color.BLACK));
        }
        Log.e(TAG, "cal start: " + mDrawFlat.size());
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        invalidate();
    }
}
