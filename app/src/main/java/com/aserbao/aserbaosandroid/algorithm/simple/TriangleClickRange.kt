package com.getremark.camerademo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.Toast
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt
import kotlin.math.sqrt
/**
 * @author: aserbao
 * @date:2020/9/1 4:52 PM
 * @package:com.getremark.camerademo
 * @describle: 点击区域示范在凸多边形范围内
 */
class TriangleClickRange : FrameLayout {
    constructor(context: Context) : super(context) {}
    constructor(context: Context,
                attrs: AttributeSet?) : super(context, attrs) {

    }


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    val point1 = PointF(100f, 800f)
    val point2 = PointF(200f, 1000f)
    val point3 = PointF(600f, 1200f)
    val point4 = PointF(300f, 800f)

//    val point1 = PointF(0f, 0f)
//    val point2 = PointF(0f, 100f)
//    val point3 = PointF(100f, 100f)
//    val point4 = PointF(100f, 0f)

    val pointList:List<PointF> = listOf(point1, point2, point3,point4)

    val path = Path()

    init {
        paint.color = Color.parseColor("#ff0000")
        paint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,2f,resources.displayMetrics)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        path.moveTo(point1.x,point1.y)
        path.lineTo(point2.x,point2.y)
        path.lineTo(point3.x, point3.y)
        path.lineTo(point4.x,point4.y)

        canvas.drawPath(path,paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (clickedRect(event)) {
                Toast.makeText(context, "点击了四边形区域", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onTouchEvent(event)
    }


    /**
     * 点击了多边形区域算法
     *
     * @param event MotionEvent
     * @return Boolean
     */
    private fun clickedRect(event: MotionEvent):Boolean{
        val x = event.x
        val y = event.y
        Log.d("debug_log"," --> x = $x  y = $y")
        val clickPointF = PointF(x, y)

        // 现在只适用于凸多边形。  凹多边形 判断任意一点是否在多边形内还没有找到算法
        // 多边型组成的所有三角形
        val triangles = ArrayList<Triangle>()

        for ( i in pointList.indices){
            // 构建三角形  n边形就可以组成n个三角形
            triangles.add(Triangle(pointList[i%4], pointList[(i+1)%4],clickPointF))
        }
        var triangleArea = 0f
        for (i in triangles.indices) {
            triangleArea += triangles[i].area
        }
        Log.d("debug_log", " 所有三角形的面积 triangleArea = $triangleArea  " +
                " 四舍五入  ${triangleArea.roundToInt()} ")

        // 计算四边形的面积  pointList
//        就计算两个三角形的面积吧

        val  rectArea = Triangle(pointList[0],pointList[1],pointList[2]).area + Triangle(pointList[0],pointList[2],pointList[3]).area
        Log.d("debug_log","  四边形的面积  rectArea =  $rectArea  四舍五入  ${rectArea.roundToInt()}")


        if (triangleArea.roundToInt() <= rectArea.roundToInt()) {
            return true
        }

        return false
    }
}

/**
 * 三角形
 */
class Triangle(
        // 三角形的三个点
        point1:PointF,
        point2: PointF,
        point3:PointF
){
    var a = 0f
    var b = 0f
    var c = 0f

    // 三角形的面积
    var area = 0f

    init {
        a = sqrt(abs(point1.x - point2.x) * abs(point1.x - point2.x) + abs(point1.y - point2.y)*abs(point1.y - point2.y))
        b = sqrt(abs(point1.x - point3.x) * abs(point1.x - point3.x) + abs(point1.y - point3.y)*abs(point1.y - point3.y))
        c = sqrt(abs(point2.x - point3.x) * abs(point2.x - point3.x) + abs(point2.y - point3.y)*abs(point2.y - point3.y))

        // 计算三角形的面积   使用海伦公式
        val p = (a+b+c)/2
        area = sqrt(p * (p - a) * (p - b) * (p - c))

        Log.d("debug_log","  这个三角形的三条边长 a = $a b = $b  c = $c  这一个三角形面积 area = $area")
    }

}