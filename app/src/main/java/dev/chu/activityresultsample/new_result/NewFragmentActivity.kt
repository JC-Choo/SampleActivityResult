package dev.chu.activityresultsample.new_result

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dev.chu.activityresultsample.R

class NewFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_fragment)

        supportFragmentManager.commit(true) {
            add(findViewById<FrameLayout>(R.id.container).id, NewFragment())
        }
    }
}