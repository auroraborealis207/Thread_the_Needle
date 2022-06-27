package wave.project.threadtheneedle

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import wave.project.threadtheneedle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val center_layout = findViewById<LinearLayout>(R.id.vertical_layout)
//        val Canvas_view = findViewById<Canvas_View>(R.id.CanvasView)
//
//        var canvas = Canvas()
//        var paint = Paint()
//        canvas.drawCircle(250.toFloat(),250.toFloat(),50.toFloat(),paint)

    }
}