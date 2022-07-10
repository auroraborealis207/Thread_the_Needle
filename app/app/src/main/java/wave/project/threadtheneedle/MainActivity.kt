package wave.project.threadtheneedle

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import wave.project.threadtheneedle.databinding.ActivityMainBinding
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 200

//    fun update_points(){
//        points_text.text = "Points:${binding.CanvasView.points}"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //binding.textView4.text = "Points:" + binding.CanvasView.points.toString()

//        val center_layout = findViewById<LinearLayout>(R.id.vertical_layout)
//        val Canvas_view = findViewById<Canvas_View>(R.id.CanvasView)
//
//        var canvas = Canvas()
//        var paint = Paint()
//        canvas.drawCircle(250.toFloat(),250.toFloat(),50.toFloat(),paint)

    }

//
    override fun onResume() {
        handler.postDelayed(Runnable {
            handler.postDelayed(runnable!!, delay.toLong())
            //Toast.makeText(this@MainActivity, "This method will run every 10 seconds", Toast.LENGTH_SHORT).show()
            val points_text= findViewById<TextView>(R.id.textView4)
            points_text.text = "Points:${binding.CanvasView.points}"
                            }.also { runnable = it }, delay.toLong())

    super.onResume()
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable!!)
    }





}