package dev.chu.activityresultsample.old_result

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dev.chu.activityresultsample.R

class OldFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_fragment)

        supportFragmentManager.commit(true) {
            add(findViewById<FrameLayout>(R.id.container).id, OldFragment())
        }
    }
}