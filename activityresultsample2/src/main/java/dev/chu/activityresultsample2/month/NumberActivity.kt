package dev.chu.activityresultsample2.month

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import dev.chu.activityresultsample2.R

class NumberActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var input: EditText

    private val launcher: ActivityResultLauncher<Int> =
        registerForActivityResult(MonthActivityContract()) { result: String? ->
            result?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)
        btn = findViewById(R.id.btn)
        input = findViewById(R.id.input)

        btn.setOnClickListener {
            val number: Int
            try {
                number = input.text.toString().toInt()
                launcher.launch(number)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter a number between 1 and 12", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}