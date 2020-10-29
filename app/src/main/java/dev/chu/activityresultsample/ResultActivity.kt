package dev.chu.activityresultsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        findViewById<Button>(R.id.btCall).setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtras(bundleOf(key_string_case to "ABCD", key_int_case to 1))
            })
            finish()
        }
    }

    companion object {
        const val key_string_case = "typeString"
        const val key_int_case = "typeInt"
    }
}