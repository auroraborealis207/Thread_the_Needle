package wave.project.threadtheneedle

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.shapes.Shape
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt


class Canvas_View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint1 = Paint()
    var Timer_Count = 0
    var Seconds =0
    var New_Object_Spawn_Time = 20
    var refresh_rate = 100
    var screen_lenght = this.width
    var screen_height = this.height
    var speed = 0
    var space_bar_pressed = false
    var Screen_speed = 10
    var test_dot= mutableListOf("","")
    var end_screen = true

    var gamelost = false
    var points = 0
    var Constant_upward_speed = 15
    var gravity = 2

    var Spawn_Range = mutableListOf<Int>(25,700)

    var main_circle = mutableListOf("Circle","250","250","50")

    var first_rectangle = mutableListOf("Rectangle","900","600","500","300")

    var Shapes = mutableListOf(main_circle,first_rectangle)
    var points_text= super.findViewById<TextView>(R.id.textView4)

    fun restart(){
        Timer_Count = 0
        Seconds =0
        New_Object_Spawn_Time = 20
        refresh_rate = 100
        screen_lenght = this.width
        screen_height = this.height
        speed = 10
        space_bar_pressed = false
        Screen_speed = 7
        test_dot= mutableListOf("","")

        gamelost = false
        points = 0

        Spawn_Range = mutableListOf<Int>(25,700)

        main_circle = mutableListOf("Circle","250","250","50")

        first_rectangle = mutableListOf("Rectangle","900","600","500","300")

        Shapes = mutableListOf(main_circle,first_rectangle)
        end_screen = true
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        return when (event.getAction()) {

            // Display a Toast whenever a movement is captured on the screen
            MotionEvent.ACTION_DOWN -> {
                //Toast.makeText(super.getContext(), "Action was DOWN", Toast.LENGTH_SHORT).show()
                space_bar_pressed = true
                true
            }MotionEvent.ACTION_UP -> {
                //Toast.makeText(super.getContext(), "Action was UP", Toast.LENGTH_SHORT).show()
                space_bar_pressed = false
                true
            }
            MotionEvent.ACTION_MOVE -> {
//                Toast.makeText(super.getContext(), "Action was MOVE", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    fun add_obstacles(){
        Timer_Count +=1

        if (Timer_Count == refresh_rate){
            Timer_Count = 0
            Seconds +=1


        }
        //if (((Seconds*refresh_rate)+Timer_Count) / New_Object_Spawn_Time  == 0) {
        var check =true
        for (i in 1..Shapes.size-1){
            var shape = Shapes[0]
            var boundary = Boundary_box(Shapes[i])
            for(test_dot in boundary){
                if (this.width - test_dot[0].toInt()<100) {
                    check = false
                    break
                }
            }
            if(!check){
                break
            }
        }
        if (check){
            var x = this.width.toString()
            var y = (Spawn_Range[0]..this.height - 100).random().toString()
            Shapes.add(
                Shapes.size,
                mutableListOf("Rectangle", x, y, 250.toString(), 200.toString())
            )
        }

        if (Seconds % 2 == 0 && Timer_Count ==0){
            Screen_speed += 2
            if (Seconds%10==0) {
                gravity += 1
                Constant_upward_speed+=1
            }
//            if (New_Object_Spawn_Time!=0){
//                New_Object_Spawn_Time -= 5
//            }else {
//                New_Object_Spawn_Time=1
            //}
        }

    }

    fun UpdateBlocks(){
        //var Ignore_First = true
        var circle = Shapes[0]
        if (space_bar_pressed) {
            speed = Constant_upward_speed
            circle[2] = (circle[2].toInt() - speed).toString()

        }else {
            circle[2] = (circle[2].toInt() - speed).toString()
            speed = speed - gravity
            }

        var collided = collision_check()

        if (((circle[2].toInt() + circle[3].toInt()) > this.height) || ((circle[2].toInt() - circle[3].toInt()) < 0) || (collided)){
            game_lost()
        }

        for (i in 1..Shapes.size-1){

            var shape1 = Shapes[i]
            var position1 = shape1[1].toInt()

            shape1[1] = (position1-Screen_speed).toString()

//            if (shape1[1].toInt() == 0){
//                points +=1
//
//            }
            if (shape1[0] == "Rectangle") {
                if (shape1[1].toInt() + shape1[3].toInt() < 0){
                    Shapes[i] = mutableListOf("")
                    points +=1

                    //super.getContext().
                }
            }
        }

        Shapes.removeAll(mutableListOf(mutableListOf("")))
    }

    fun game_lost (){
        if (end_screen) {
            end_screen =false
            //Toast.makeText(super.getContext(), "Game Lost", Toast.LENGTH_SHORT).show()
            gamelost = true

            val goToNextActivity = Intent(super.getContext(), EndScreen::class.java).apply {
                putExtra("Points", points)
            }
            super.getContext().startActivity(goToNextActivity)

            restart()
        }
    }

    fun collision_check(): Boolean {

        for (i in 1..Shapes.size-1){
            var shape = Shapes[0]
            var boundary = Boundary_box(Shapes[i])
            for (test_dot in boundary){
                if (sqrt((shape[1].toDouble()-test_dot[0].toDouble()).pow(2) +
                            (shape[2].toDouble()-test_dot[1].toDouble()).pow(2)).toDouble() < shape[3].toDouble()){
                    return true
                }
            }

        }
        return false
    }

    fun Boundary_box(shape1 :MutableList<String>) : MutableList<MutableList<String>>{
        var bounding = mutableListOf<MutableList<String>>()
        if (shape1[0] == "Rectangle"){
            var x = shape1[1].toInt()
            var y = shape1[2].toInt()
            var lenght = shape1[3].toInt()
            var width = shape1[4].toInt()

            for (i in 1..lenght){
                bounding.add(mutableListOf(x.toString(),(y+i).toString()))
                bounding.add(mutableListOf((x+width).toString(),(y+i).toString()))
            }

            for (i in 1..width){
                bounding.add(mutableListOf((x+i).toString(),y.toString()))
                bounding.add(mutableListOf((x+i).toString(),(y+lenght).toString()))
            }

        }

        return bounding
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas != null) {

            if (!gamelost){
                add_obstacles()
                UpdateBlocks()
            }

            //Toast.makeText(super.getContext(),"Updating",Toast.LENGTH_SHORT).show()
            //Toast.makeText(super.getContext(), "Action was MOVE", Toast.LENGTH_SHORT).show()
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
            //canvas.drawCircle(250.toFloat(),250.toFloat(),350.toFloat(),paint1)


            var i = Handler(Looper.getMainLooper()).postDelayed({
                this.invalidate()
            }, floor((1000 / refresh_rate).toDouble()).toLong())

            }
        }
    }








