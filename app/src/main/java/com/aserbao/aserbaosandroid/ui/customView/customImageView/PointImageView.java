package com.aserbao.aserbaosandroid.ui.customView.customImageView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.aserbao.aserbaosandroid.AUtils.utils.screen.DisplayUtil;
import com.aserbao.aserbaosandroid.R;

/**
 * 实现在图片右上角显示消息条数
 */
public class PointImageView extends ImageView {

    /**
     * 默认模式
     */
    private int pointMode = NO_POINT;

    // 1.不显示红点
    public static final int NO_POINT = 1;
    // 2.只显示一个红点,表示有新消息
    public static final int ONLY_POINT = 2;
    // 3.显示一个红点,红点中间还有消息的数量
    public static final int NUMBER_POINT = 3;

    //消息的数量
    private  int  number = 0;

    //记录当前是否有新消息
    public boolean  isHaveMesage = false;

    /**
     * 画圆
     */
    private Paint paint;

    /**
     * 画消息条数,有未画先知的功能,可以提前知道需要画的文字的长宽
     */
    private TextPaint paintText;
    private float pointPaddingRight,pointPaddingTop;
    private int pointTextSize = 18,pointTextColor = Color.WHITE;

    public PointImageView(Context context) {
        this(context,null);
    }

    public PointImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PointImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGetData(context,attrs,defStyleAttr);
        init();
    }

    private void initGetData(Context context, AttributeSet attrs,int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PointImageView, defStyleAttr, 0);
        pointPaddingRight = a.getDimension(R.styleable.PointImageView_point_padding_right, DisplayUtil.dip2px(10));
        pointPaddingTop = a.getDimension(R.styleable.PointImageView_point_padding_top, DisplayUtil.dip2px(10));
        pointTextColor = a.getColor(R.styleable.PointImageView_point_text_color,Color.WHITE);
        pointTextSize = a.getInt(R.styleable.PointImageView_point_text_size,18);
    }

    /**
     * 初始化数据
      */
    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);//实心
        paint.setColor(0xffff0000);//红色
        paint.setAntiAlias(true);//抗锯齿

        paintText= new TextPaint();
        paintText.setColor(pointTextColor);//白色
        paintText.setTextSize(pointTextSize);//设置显示条数的文本大小
        paintText.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);//实心
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isHaveMesage){
            return;
        }
        switch (pointMode){
            case NO_POINT://不显示红点
                break;
            case ONLY_POINT://只显示红点
                canvas.drawCircle(getWidth()-pointPaddingRight,pointPaddingTop,pointPaddingRight,paint);
                break;
            case NUMBER_POINT://显示红点且带消息条数
                canvas.drawCircle(getWidth()-pointPaddingRight,pointPaddingTop,pointPaddingRight,paint);
                //文字显示是要有基准线的
                String showText="";
                if(number>0 && number<100){
                    showText = number+"";
                }else if(number >= 100){
                    showText = "+99";
                }
                float textWidth = paintText.measureText(showText);//测量出文本的宽度
                //图片右顶点减去文本的一半,使文本中心与图片右顶点重合
                float x = getWidth() - pointPaddingRight - textWidth / 2;
                //y抽坐标,文字的基准线为图片右顶点下面点
                float y = (float) (pointPaddingTop + paintText.getFontMetrics().bottom*1.5);
                canvas.drawText(showText,x ,y,paintText);
                break;
        }
    }

    /**
     * 设置消息条数
     */
    public void setMessageNum(int number){
        this.number=number;
    }

    /**
     * 是否有新消息
     * @param isHaveMesage true代表有
     */
    public void setHaveMesage(boolean isHaveMesage){
        this.isHaveMesage=isHaveMesage;
        invalidate();//如果不能即时刷新,使用postInvalidate()就好了
    }

    /**
     * 设置显示模式
     * @param mode
     */
    public void setPointMode(int mode){
        if(mode>0 && mode<=3){
            pointMode = mode;
        }else {
            throw new RuntimeException("设置的模式有误");
        }
    }

}