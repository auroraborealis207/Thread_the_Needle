package wave.project.threadtheneedle

import android.content.Context
import android.view.View
import android.app.Activity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.graphics.RectF
import android.graphics.Region
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.util.DisplayMetrics


class Canvas_View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    val paint1 = Paint()

    var Shapes = mutableListOf(mutableListOf("Circle",250,250,50))//, mutableListOf("Rectangle",600,600,500,300))

    fun add_obstacles(){
        shapes.add
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            for (obj in Shapes){
                if (obj.elementAt(0)=="Circle"){
                    var x = obj.elementAt(1).toString().toFloat()
                    var y = obj.elementAt(2).toString().toFloat()
                    var r = obj.elementAt(3).toString().toFloat()
                    canvas.drawCircle(x,y,r,paint1)
                }
                if (obj.elementAt(0)=="Rectangle"){
                    var x1 = obj.elementAt(1).toString().toFloat()
                    var y1 = obj.elementAt(2).toString().toFloat()
                    var x2 = x1+obj.elementAt(3).toString().toFloat()
                    var y2 = y1+obj.elementAt(4).toString().toFloat()

                    canvas.drawRect(x1,y1,x2,y2,paint1)

                }
            }
            canvas.drawCircle(250.toFloat(),250.toFloat(),50.toFloat(),paint1)
        }
    }


}