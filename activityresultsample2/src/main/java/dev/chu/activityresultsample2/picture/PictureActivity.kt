package dev.chu.activityresultsample2.picture

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
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

    private val launcher2: ActivityResultLauncher<Unit> =
        registerForActivityResult(PictureContract()) { result ->
            Glide.with(image).load(result).into(image)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        image = findViewById(R.id.image)
        findViewById<Button>(R.id.imageGallery).setOnClickListener {
            launcher.launch("image/*")
        }
        findViewById<Button>(R.id.mediaGallery).setOnClickListener {
            launcher2.launch(Unit)
        }
    }
}