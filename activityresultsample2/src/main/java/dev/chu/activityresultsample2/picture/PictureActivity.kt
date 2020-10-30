package dev.chu.activityresultsample2.picture

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dev.chu.activityresultsample2.R

class PictureActivity : AppCompatActivity() {
    lateinit var image: ImageView

    private val launcher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        Glide.with(image).load(uri).into(image)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        image = findViewById(R.id.image)
        findViewById<Button>(R.id.choose).setOnClickListener {
            launcher.launch("image/*")
        }
    }
}