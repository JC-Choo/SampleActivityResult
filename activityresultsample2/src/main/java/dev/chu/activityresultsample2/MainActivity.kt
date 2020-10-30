package dev.chu.activityresultsample2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dev.chu.activityresultsample2.month.NumberActivity
import dev.chu.activityresultsample2.picture.PictureActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.month).setOnClickListener {
            startActivity(Intent(this, NumberActivity::class.java))
        }

        findViewById<Button>(R.id.picture).setOnClickListener {
            startActivity(Intent(this, PictureActivity::class.java))
        }
    }
}