package dev.chu.activityresultsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import dev.chu.activityresultsample.new_result.NewActivity
import dev.chu.activityresultsample.old_result.OldActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btNew).setOnClickListener {
            startActivity(Intent(this, NewActivity::class.java))
        }

        findViewById<Button>(R.id.btOld).setOnClickListener {
            startActivity(Intent(this, OldActivity::class.java))
        }
    }
}