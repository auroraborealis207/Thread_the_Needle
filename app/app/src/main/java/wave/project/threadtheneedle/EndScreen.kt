package wave.project.threadtheneedle

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EndScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_end)
        val points = intent.getIntExtra("Points",0)
        val points_veiw = findViewById<TextView>(R.id.textView2)
        points_veiw.text = "$points\nPoints"

        val new_game = findViewById<Button>(R.id.button17)
        new_game.setOnClickListener{new_Game()}
    }

    fun new_Game(){

        finish()
    }
}