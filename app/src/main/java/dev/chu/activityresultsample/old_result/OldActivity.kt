package dev.chu.activityresultsample.old_result

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dev.chu.activityresultsample.*

class OldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old)

        findViewById<Button>(R.id.btShowSecond).setOnClickListener {
            startSecondView()
        }
        findViewById<Button>(R.id.btRequestLocationPermission).setOnClickListener {
            requestLocation(request_location_code)
        }
        findViewById<Button>(R.id.btShowFragmentSample).setOnClickListener {
            startOldStyleFragmentView()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == request_second_code) {
            Log.i(TAG, "onActivityResult requestCode = $requestCode, resultCode = $resultCode")
            if (resultCode == Activity.RESULT_OK && data != null) {
                toast(data.extras?.toString.orEmpty())
            } else {
                toast("RESULT_CANCELED")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == request_location_code) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                toast("Permission granted")
            } else {
                toast("Permission denied or canceled")
            }
        }
    }

    private fun startSecondView() {
        startActivityForResult(Intent(this, ResultActivity::class.java), request_second_code)
    }

    private fun startOldStyleFragmentView() {
        startActivity(Intent(this, OldFragmentActivity::class.java))
    }

    companion object {
        private const val request_second_code = 100
        private const val request_location_code = 101
    }
}