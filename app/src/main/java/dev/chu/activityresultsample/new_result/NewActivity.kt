package dev.chu.activityresultsample.new_result

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import dev.chu.activityresultsample.*

class NewActivity : AppCompatActivity() {

    private val requestActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            toast(it.toString)
        }

    private val requestSecondVanilla =
        registerForActivityResult(object: ActivityResultContract<Unit, ActivityResult>() {
            override fun createIntent(context: Context, input: Unit?): Intent {
                return Intent(context, ResultActivity::class.java)
            }

            override fun parseResult(resultCode: Int, intent: Intent?): ActivityResult {
                return ActivityResult(resultCode, intent)
            }
        }) {
            toast(it.toString)
        }

    private val secondCustom =
        registerForActivityResult(object : ActivityResultContract<Unit, SecondResult?>() {
            override fun createIntent(context: Context, input: Unit?): Intent {
                return Intent(context, ResultActivity::class.java)
            }

            override fun parseResult(resultCode: Int, intent: Intent?): SecondResult? {
                return if(resultCode == Activity.RESULT_OK && intent != null) {
                    SecondResult(
                        typeString = intent.getStringExtra(ResultActivity.key_string_case).toString(),
                        typeInt = intent.getIntExtra(ResultActivity.key_int_case, 0)
                    )
                } else {
                    null
                }
            }
        }) {
            if(it != null) {
                toast(it.toString())
            }
        }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            toast("Location granted = $isGranted")
        }

    private val requestLocation =
        registerForActivityResult(ActivityResultContracts.RequestPermission(), Manifest.permission.ACCESS_FINE_LOCATION) { isGranted ->
            toast("Location granted = $isGranted")
        }

    private val takePicturePreview = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        toast("Got picture: $bitmap")
    }

    val getContent = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        toast("Got image: $uri")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        findViewById<Button>(R.id.bt00).setOnClickListener {
            requestActivity.launch(Intent(this, ResultActivity::class.java))
        }
        findViewById<Button>(R.id.bt01).setOnClickListener {
            requestSecondVanilla.launch()
        }
        findViewById<Button>(R.id.bt02).setOnClickListener {
            secondCustom.launch()
        }
        findViewById<Button>(R.id.bt03).setOnClickListener {
            requestLocation.launch()
        }
        findViewById<Button>(R.id.bt04).setOnClickListener {
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        findViewById<Button>(R.id.bt05).setOnClickListener {
            takePicturePreview.launch()
        }
        findViewById<Button>(R.id.bt06).setOnClickListener {
            getContent.launch("image/*")
        }
        findViewById<Button>(R.id.bt07).setOnClickListener {
            requestActivity.launch(Intent(this, NewFragmentActivity::class.java))
        }
        findViewById<Button>(R.id.bt08).setOnClickListener {
            requestActivity.launch(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            })
        }
    }
}